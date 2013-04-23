package javarepl.console.rest;

import com.googlecode.utterlyidle.BasePath;
import com.googlecode.utterlyidle.RestApplication;
import javarepl.console.Console;

import static com.googlecode.utterlyidle.annotations.AnnotatedBindings.annotatedClass;
import static com.googlecode.utterlyidle.modules.Modules.applicationInstance;
import static com.googlecode.utterlyidle.modules.Modules.bindingsModule;

public class RestConsoleApplication extends RestApplication {
    public RestConsoleApplication(BasePath basePath, Console console) {
        super(basePath,
                bindingsModule(annotatedClass(RestConsoleResource.class)),
                applicationInstance(Console.class, console));

        add(new RestConsoleModule());
    }
}
