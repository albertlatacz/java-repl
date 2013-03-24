package javarepl.plugin;

import com.intellij.codeInsight.completion.CompletionProcess;
import com.intellij.codeInsight.completion.CompletionService;
import com.intellij.codeInsight.lookup.Lookup;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.execution.console.LanguageConsoleImpl;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.EmptyAction;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;

public abstract class JavaREPLExecuteActionBase extends DumbAwareAction {
  public static final String ACTIONS_EXECUTE_ICON = "/actions/execute.png";

  protected final LanguageConsoleImpl languageConsole;
  protected final ProcessHandler myProcessHandler;

  public JavaREPLExecuteActionBase(LanguageConsoleImpl languageConsole,
                                   ProcessHandler processHandler,
                                   String actionId) {
    super(null, null, IconLoader.getIcon(ACTIONS_EXECUTE_ICON));
    this.languageConsole = languageConsole;
    myProcessHandler = processHandler;
    EmptyAction.setupAction(this, actionId, null);
  }

  public void update(final AnActionEvent e) {
    e.getPresentation().setEnabled(isActionEnabled());
  }

  private boolean isActionEnabled() {
    if (myProcessHandler.isProcessTerminated()) {
      return false;
    }

    final Lookup lookup = LookupManager.getActiveLookup(languageConsole.getConsoleEditor());
    if (lookup == null || !lookup.isCompletion()) {
      return true;
    }

    CompletionProcess completion = CompletionService.getCompletionService().getCurrentCompletion();
    if (completion != null && completion.isAutopopupCompletion() && !lookup.isSelectionTouched()) {
      return true;
    }
    return false;
  }

}
