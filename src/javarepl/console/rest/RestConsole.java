package javarepl.console.rest;

import com.googlecode.utterlyidle.ServerConfiguration;
import com.googlecode.utterlyidle.httpserver.RestServer;
import javarepl.console.Console;
import javarepl.console.DelegatingConsole;

import static com.googlecode.utterlyidle.BasePath.basePath;
import static com.googlecode.utterlyidle.ServerConfiguration.defaultConfiguration;

public class RestConsole extends DelegatingConsole {
    public RestConsole(Console console, Integer port) throws Exception {
        super(console);

        ServerConfiguration configuration = defaultConfiguration().port(port);
        RestConsoleApplication application = new RestConsoleApplication(basePath("/"), this);
        new RestServer(application, configuration);
    }
}
