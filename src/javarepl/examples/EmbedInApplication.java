package javarepl.examples;

import javarepl.console.SimpleConsole;
import javarepl.console.SimpleConsoleConfig;
import javarepl.console.commands.SearchHistory;
import javarepl.console.rest.RestConsole;

import static javarepl.console.SimpleConsoleConfig.consoleConfig;

/**
 * Example shows how to embed JavaREPL inside your application. Note that your application needs to be running via JDK
 * for the JavaREPL to work.
 */
public class EmbedInApplication {
    public static void main(String... args) throws Exception {
        SimpleConsoleConfig config = consoleConfig().commands(new SearchHistory());

        new RestConsole(new SimpleConsole(config), 8001);
    }
}
