package javarepl.rest;

import com.googlecode.utterlyidle.BasePath;
import com.googlecode.utterlyidle.RestApplication;
import com.googlecode.utterlyidle.httpserver.RestServer;
import javarepl.console.Console;

import static com.googlecode.utterlyidle.BasePath.basePath;
import static com.googlecode.utterlyidle.ServerConfiguration.defaultConfiguration;
import static com.googlecode.utterlyidle.annotations.AnnotatedBindings.annotatedClass;
import static com.googlecode.utterlyidle.modules.Modules.applicationInstance;
import static com.googlecode.utterlyidle.modules.Modules.bindingsModule;
import static javarepl.console.ConsoleLogger.systemConsoleLogger;

public class Main extends RestApplication {

    public Main(BasePath basePath) {
        super(basePath,
                bindingsModule(annotatedClass(ConsoleResource.class)),
                applicationInstance(new Console(systemConsoleLogger())));

        add(new ConsoleModule());
    }

    public static void main(String[] args) throws Exception {
        Main application = new Main(basePath("/"));
        new RestServer(application, defaultConfiguration().port(8001));
    }
}
