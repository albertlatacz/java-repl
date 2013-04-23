package javarepl.console.rest;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.utterlyidle.ServerConfiguration;
import com.googlecode.utterlyidle.httpserver.RestServer;
import javarepl.console.Console;
import javarepl.console.commands.Command;
import javarepl.console.commands.CommandResult;

import static com.googlecode.utterlyidle.BasePath.basePath;
import static com.googlecode.utterlyidle.ServerConfiguration.defaultConfiguration;
import static javarepl.Utils.randomServerPort;

public class RestConsole implements Console {

    private final Console console;
    private final RestServer server;

    public RestConsole(Console console, Option<Integer> port) throws Exception {
        this.console = console;

        ServerConfiguration configuration = defaultConfiguration().port(port.getOrElse(randomServerPort()));
        RestConsoleApplication application = new RestConsoleApplication(basePath("/"), console);
        this.server = new RestServer(application, configuration);
    }

    public CommandResult execute(String expression) {
        return console.execute(expression);
    }

    public Sequence<Command> commands() {
        return console.commands();
    }
}
