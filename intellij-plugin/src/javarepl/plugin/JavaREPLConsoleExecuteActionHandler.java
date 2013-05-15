package javarepl.plugin;

import com.intellij.execution.process.ConsoleHistoryModel;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.FileASTNode;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.impl.source.codeStyle.IndentHelper;
import com.intellij.psi.impl.source.codeStyle.IndentHelperImpl;

import java.io.IOException;
import java.io.OutputStream;

public class JavaREPLConsoleExecuteActionHandler {

    private final ProcessHandler processHandler;
    private ConsoleHistoryModel consoleHistoryModel;
    private final Project project;
    private final IndentHelper indentHelper;
    private boolean preserveMarkup;


    public JavaREPLConsoleExecuteActionHandler(ProcessHandler processHandler,
                                               ConsoleHistoryModel consoleHistoryModel,
                                               Project project,
                                               boolean preserveMarkup) {
        this.processHandler = processHandler;
        this.consoleHistoryModel = consoleHistoryModel;
        this.project = project;
        this.preserveMarkup = preserveMarkup;
        this.indentHelper = IndentHelper.getInstance();
    }

    public void processLine(String line) {
        final OutputStream outputStream = processHandler.getProcessInput();
        try {
            byte[] bytes = (line + "\n").getBytes("UTF-8");
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            // ignore
        }
    }

    public void runExecuteAction(final JavaREPLLanguageConsole console,
                                 boolean executeImmediately) {

        if (executeImmediately) {
            execute(console, consoleHistoryModel);
            return;
        }

        // Process input and add to history
        final Editor editor = console.getCurrentEditor();
        final Document document = editor.getDocument();
        final CaretModel caretModel = editor.getCaretModel();
        final int offset = caretModel.getOffset();
        final String text = document.getText();

        if (!text.substring(offset).trim().isEmpty()) {
            final String before = text.substring(0, offset);
            final String after = text.substring(offset);
            final FileASTNode node = console.getFile().getNode();
            final Project project = editor.getProject();
            final int indent = indentHelper.getIndent(project, JavaFileType.INSTANCE, node);
            final String spaces = IndentHelperImpl.fillIndent(project, JavaFileType.INSTANCE, indent);
            final String newText = before + "\n" + spaces + after;

            new WriteCommandAction(JavaREPLConsoleExecuteActionHandler.this.project) {
                @Override
                protected void run(Result result) throws Throwable {
                    console.setInputText(newText);
                    caretModel.moveToOffset(offset + indent + 1);
                }
            }.execute();

            return;
        }

        execute(console, consoleHistoryModel);
    }

    private void execute(JavaREPLLanguageConsole languageConsole,
                         ConsoleHistoryModel consoleHistoryModel) {

        final Document document = languageConsole.getCurrentEditor().getDocument();
        final String text = document.getText();
        final TextRange range = new TextRange(0, document.getTextLength());

        languageConsole.getCurrentEditor().getSelectionModel().setSelection(range.getStartOffset(), range.getEndOffset());
        languageConsole.addCurrentToHistory(range, false, preserveMarkup);
        languageConsole.setInputText("");
        if (!StringUtil.isEmptyOrSpaces(text)) {
            consoleHistoryModel.addToHistory(text);
        }
        processLine(text);
    }


}
