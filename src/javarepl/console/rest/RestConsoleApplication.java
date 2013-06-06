package javarepl.console.rest;

import com.googlecode.utterlyidle.BasePath;
import com.googlecode.utterlyidle.RestApplication;

import static com.googlecode.utterlyidle.annotations.AnnotatedBindings.annotatedClass;
import static com.googlecode.utterlyidle.modules.Modules.applicationInstance;
import static com.googlecode.utterlyidle.modules.Modules.bindingsModule;

public class RestConsoleApplication extends RestApplication {
    public RestConsoleApplication(BasePath basePath, RestConsole console) {
        super(basePath,
                bindingsModule(annotatedClass(RestConsoleResource.class)),
                applicationInstance(console),
                applicationInstance(new RestConsoleExpressionReader()));

        add(new RestConsoleModule());
    }
}
