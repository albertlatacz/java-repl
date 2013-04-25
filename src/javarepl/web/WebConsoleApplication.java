package javarepl.web;

import com.googlecode.utterlyidle.BasePath;
import com.googlecode.utterlyidle.RestApplication;

import static com.googlecode.utterlyidle.annotations.AnnotatedBindings.annotatedClass;
import static com.googlecode.utterlyidle.modules.Modules.applicationInstance;
import static com.googlecode.utterlyidle.modules.Modules.bindingsModule;

public class WebConsoleApplication extends RestApplication {
    public WebConsoleApplication(BasePath basePath) {
        super(basePath,
                bindingsModule(annotatedClass(WebConsoleResource.class)),
                applicationInstance(new WebConsole())
        );

        add(new WebConsoleModule());
    }
}
