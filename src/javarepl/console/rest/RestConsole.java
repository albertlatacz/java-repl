package javarepl.console.rest;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.utterlyidle.ServerConfiguration;
import com.googlecode.utterlyidle.httpserver.RestServer;
import javarepl.Evaluator;
import javarepl.console.Console;
import javarepl.console.ConsoleHistory;
import javarepl.console.ConsoleResult;
import javarepl.console.commands.Command;

import static com.googlecode.utterlyidle.BasePath.basePath;
import static com.googlecode.utterlyidle.ServerConfiguration.defaultConfiguration;

public class RestConsole implements Console {

    private final Console console;

    public RestConsole(Console console, Integer port) throws Exception {
        this.console = console;

        ServerConfiguration configuration = defaultConfiguration().port(port);
        RestConsoleApplication application = new RestConsoleApplication(basePath("/"), this);
        new RestServer(application, configuration);
    }

    public ConsoleResult execute(String expression) {
        return console.execute(expression);
    }

    public Sequence<Command> commands() {
        return console.commands();
    }

    public ConsoleHistory history() {
        return console.history();
    }

    public Evaluator evaluator() {
        return console.evaluator();
    }
}
