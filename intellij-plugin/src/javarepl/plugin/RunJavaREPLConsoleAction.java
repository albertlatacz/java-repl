package javarepl.plugin;

import com.intellij.execution.CantRunException;
import com.intellij.execution.ExecutionHelper;
import com.intellij.facet.FacetManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.IconLoader;

import java.util.Arrays;

import static javarepl.plugin.JavaREPLConsoleRunner.REPL_TITLE;

public class RunJavaREPLConsoleAction extends AnAction implements DumbAware {

    public RunJavaREPLConsoleAction() {
        super();
        getTemplatePresentation().setIcon(IconLoader.findIcon("/javarepl/plugin/icon-run.png"));
    }

    @Override
    public void update(AnActionEvent e) {
        final Module m = getModule(e);
        final Presentation presentation = e.getPresentation();
        if (m == null) {
            presentation.setEnabled(false);
            return;
        }
        presentation.setEnabled(true);
        super.update(e);
    }


    @Override
    public void actionPerformed(AnActionEvent event) {
        final Module module = getModule(event);
        assert module != null : "Module is null";

        final String path = ModuleRootManager.getInstance(module).getContentRoots()[0].getPath();

        try {
            JavaREPLConsoleRunner.run(module, path);
        } catch (CantRunException e) {
            ExecutionHelper.showErrors(module.getProject(), Arrays.<Exception>asList(e), REPL_TITLE, null);
        }

    }

    static Module getModule(AnActionEvent e) {
        Module module = e.getData(DataKeys.MODULE);
        if (module == null) {
            final Project project = e.getData(DataKeys.PROJECT);
            if (project == null) return null;
            final Module[] modules = ModuleManager.getInstance(project).getModules();
            if (modules.length == 1) {
                module = modules[0];
            } else {
                if (modules.length > 0) {
                    module = modules[0];
                }
            }
        }
        return module;
    }
}
