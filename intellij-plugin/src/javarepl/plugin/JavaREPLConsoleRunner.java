package javarepl.plugin;

import com.intellij.execution.*;
import com.intellij.execution.configurations.CommandLineBuilder;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.console.ConsoleHistoryController;
import com.intellij.execution.console.LanguageConsoleImpl;
import com.intellij.execution.console.LanguageConsoleViewImpl;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.process.*;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.ui.actions.CloseAction;
import com.intellij.ide.CommonActionsManager;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.actions.ToggleUseSoftWrapsToolbarAction;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.impl.softwrap.SoftWrapAppliancePlaces;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ModuleSourceOrderEntry;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.PathUtil;
import javarepl.Main;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class JavaREPLConsoleRunner {
    public static final String REPL_TITLE = "Java REPL";
    public static final String REPL_MAIN_CLASS = "javarepl.Main";

    public static final String EXECUTE_ACTION_IMMEDIATELY_ID = "JavaREPL.Console.Execute.Immediately";
    public static final String EXECUTE_ACTION_ID = "JavaREPL.Console.Execute";


    private final Module module;
    private final Project project;
    private final String consoleTitle;
    private final String workingDir;

    private final ConsoleHistoryModel consoleHistoryModel;
    private LanguageConsoleViewImpl languageConsoleView;
    private LanguageConsoleImpl languageConsole;

    private ProcessHandler processHandler;
    private JavaREPLConsoleExecuteActionHandler consoleExecuteActionHandler;
    private AnAction runAction;


    public JavaREPLConsoleRunner(@NotNull Module module,
                                 @NotNull String consoleTitle,
                                 @Nullable String workingDir) {
        this.module = module;
        this.project = module.getProject();
        this.consoleTitle = consoleTitle;
        this.workingDir = workingDir;
        this.consoleHistoryModel = new ConsoleHistoryModel();
    }

    public static void run(@NotNull final Module module,
                           final String workingDir,
                           final String... statements) throws CantRunException {
        final Project project = module.getProject();
        final JavaREPLConsoleRunner runner = new JavaREPLConsoleRunner(module, REPL_TITLE, workingDir);

        try {
            runner.initAndRun(statements);
        } catch (ExecutionException e) {
            ExecutionHelper.showErrors(project, Arrays.<Exception>asList(e), REPL_TITLE, null);
        } catch (IOException e) {
            ExecutionHelper.showErrors(project, Arrays.<Exception>asList(e), REPL_TITLE, null);
        }
    }

    public void initAndRun(final String... statements) throws ExecutionException, IOException {

        languageConsole = new LanguageConsoleImpl(project, consoleTitle, JavaLanguage.INSTANCE);
        languageConsoleView = new LanguageConsoleViewImpl(languageConsole);

        GeneralCommandLine commandLine = createCommandLine(module, workingDir);
        processHandler = new ColoredProcessHandler(commandLine.createProcess(), commandLine.getCommandLineString()) {
            @Override
            protected void textAvailable(String text, Key attributes) {
                languageConsole.setPrompt("java> ");
                LanguageConsoleImpl.printToConsole(languageConsole, StringUtil.convertLineSeparators(text).replace("java> ", ""), ConsoleViewContentType.NORMAL_OUTPUT, null);
            }

        };

        consoleExecuteActionHandler = new JavaREPLConsoleExecuteActionHandler(processHandler, consoleHistoryModel, project, false);


        ProcessTerminatedListener.attach(processHandler);

        processHandler.addProcessListener(new ProcessAdapter() {
            @Override
            public void processTerminated(ProcessEvent event) {
                runAction.getTemplatePresentation().setEnabled(false);
                languageConsoleView.getConsole().setPrompt("");
                languageConsoleView.getConsole().getConsoleEditor().setRendererMode(true);
                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        languageConsoleView.getConsole().getConsoleEditor().getComponent().updateUI();
                    }
                });
            }
        });

        languageConsoleView.attachToProcess(processHandler);

        final Executor defaultExecutor = ExecutorRegistry.getInstance().getExecutorById(DefaultRunExecutor.EXECUTOR_ID);
        final DefaultActionGroup toolbarActions = new DefaultActionGroup();
        final ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, toolbarActions, false);

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(actionToolbar.getComponent(), BorderLayout.WEST);
        panel.add(languageConsoleView.getComponent(), BorderLayout.CENTER);

        final RunContentDescriptor myDescriptor =
                new RunContentDescriptor(languageConsoleView, processHandler, panel, consoleTitle);

        final AnAction[] actions = fillToolBarActions(toolbarActions, defaultExecutor, myDescriptor);
        registerActionShortcuts(actions, languageConsole.getConsoleEditor().getComponent());
        registerActionShortcuts(actions, panel);
        panel.updateUI();

        createAndRegisterEnterAction(panel);

        ExecutionManager.getInstance(project).getContentManager().showRunContent(defaultExecutor, myDescriptor);

        final ToolWindow window = ToolWindowManager.getInstance(project).getToolWindow(defaultExecutor.getId());
        window.activate(new Runnable() {
            public void run() {
                IdeFocusManager.getInstance(project).requestFocus(languageConsole.getCurrentEditor().getContentComponent(), true);
            }
        });

        processHandler.startNotify();

        final LanguageConsoleImpl console = languageConsoleView.getConsole();
        for (String statement : statements) {
            final String st = statement + "\n";

            final ConsoleViewContentType outputType = ConsoleViewContentType.NORMAL_OUTPUT;
            LanguageConsoleImpl.printToConsole(console, st, outputType, null);

            final JavaREPLConsoleExecuteActionHandler actionHandler = consoleExecuteActionHandler;
            actionHandler.processLine(st);
        }

    }

    private void createAndRegisterEnterAction(JPanel panel) {
        final AnAction enterAction = new JavaREPLExecuteActionBase(languageConsole, processHandler, JavaREPLConsoleRunner.EXECUTE_ACTION_ID) {
            public void actionPerformed(AnActionEvent anActionEvent) {
                consoleExecuteActionHandler.runExecuteAction(languageConsole, false);
            }
        };
        enterAction.registerCustomShortcutSet(enterAction.getShortcutSet(), languageConsole.getConsoleEditor().getComponent());
        enterAction.registerCustomShortcutSet(enterAction.getShortcutSet(), panel);
    }

    private static void registerActionShortcuts(final AnAction[] actions, final JComponent component) {
        for (AnAction action : actions) {
            if (action.getShortcutSet() != null) {
                action.registerCustomShortcutSet(action.getShortcutSet(), component);
            }
        }
    }


    protected AnAction[] fillToolBarActions(final DefaultActionGroup toolbarActions,
                                            final Executor defaultExecutor,
                                            final RunContentDescriptor myDescriptor) {

        ArrayList<AnAction> actionList = new ArrayList<AnAction>();

        final AnAction stopAction = createStopAction();
        actionList.add(stopAction);

        final AnAction closeAction = createCloseAction(defaultExecutor, myDescriptor);
        actionList.add(closeAction);

        ArrayList<AnAction> executionActions = createConsoleExecActions(languageConsole,
                processHandler, consoleHistoryModel);
        runAction = executionActions.get(0);
        actionList.addAll(executionActions);

        actionList.add(new ToggleUseSoftWrapsToolbarAction(SoftWrapAppliancePlaces.CONSOLE) {
            @Override
            public void setSelected(AnActionEvent e, boolean state) {
                EditorEx consoleEditor = languageConsole.getConsoleEditor();
                EditorEx historyViewer = languageConsole.getHistoryViewer();

                consoleEditor.getSettings().setUseSoftWraps(state);
                historyViewer.getSettings().setUseSoftWraps(state);

                consoleEditor.reinitSettings();
                historyViewer.reinitSettings();
            }
        });

        actionList.add(CommonActionsManager.getInstance().createHelpAction("interactive_console"));

        AnAction[] actions = actionList.toArray(new AnAction[actionList.size()]);
        toolbarActions.addAll(actions);
        return actions;
    }

    public ArrayList<AnAction> createConsoleExecActions(final LanguageConsoleImpl languageConsole,
                                                        final ProcessHandler processHandler,
                                                        final ConsoleHistoryModel historyModel) {

        final AnAction runImmediatelyAction = new JavaREPLExecuteActionBase(languageConsole, processHandler, JavaREPLConsoleRunner.EXECUTE_ACTION_IMMEDIATELY_ID) {
            public void actionPerformed(AnActionEvent anActionEvent) {
                consoleExecuteActionHandler.runExecuteAction(languageConsole, true);
            }
        };

        final ConsoleHistoryController historyController = new ConsoleHistoryController("JavaREPL", null, languageConsole, historyModel);
        historyController.install();

        final AnAction upAction = historyController.getHistoryPrev();
        final AnAction downAction = historyController.getHistoryNext();

        final ArrayList<AnAction> list = new ArrayList<AnAction>();
        list.add(runImmediatelyAction);
        list.add(downAction);
        list.add(upAction);
        return list;
    }


    protected AnAction createCloseAction(final Executor defaultExecutor, final RunContentDescriptor myDescriptor) {
        return new CloseAction(defaultExecutor, myDescriptor, project);
    }

    protected AnAction createStopAction() {
        return ActionManager.getInstance().getAction(IdeActions.ACTION_STOP_PROGRAM);
    }

    private GeneralCommandLine createCommandLine(Module module, String workingDir) throws CantRunException {
        final JavaParameters params = new JavaParameters();
        params.configureByModule(module, JavaParameters.JDK_AND_CLASSES_AND_TESTS);

        params.getClassPath().add(PathUtil.getJarPathForClass(Main.class));

        Set<VirtualFile> cpVFiles = new HashSet<VirtualFile>();
        ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
        OrderEntry[] entries = moduleRootManager.getOrderEntries();
        for (OrderEntry orderEntry : entries) {
            if (orderEntry instanceof ModuleSourceOrderEntry) {
                cpVFiles.addAll(Arrays.asList(orderEntry.getFiles(OrderRootType.SOURCES)));
            }
        }

        for (VirtualFile file : cpVFiles) {
            params.getClassPath().add(file.getPath());
        }

        params.setMainClass(REPL_MAIN_CLASS);
        params.setWorkingDirectory(new File(workingDir));

        final GeneralCommandLine line = CommandLineBuilder.createFromJavaParameters(params, project, true);

        assert params.getJdk() != null;

        Map<String, String> envParams = new HashMap<String, String>();
        envParams.putAll(System.getenv());
        line.setEnvParams(envParams);
        line.addParameter("--simpleConsole");

        return line;
    }
}
