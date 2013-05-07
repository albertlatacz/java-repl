package javarepl.examples;

import javarepl.console.ConsoleConfig;
import javarepl.console.SimpleConsole;
import javarepl.console.commands.EvaluateFromHistory;
import javarepl.console.commands.ListValues;
import javarepl.console.commands.SearchHistory;
import javarepl.console.commands.ShowHistory;
import javarepl.console.rest.RestConsole;

import java.io.File;
import java.util.Date;

import static java.lang.System.getProperty;
import static javarepl.Result.result;
import static javarepl.console.ConsoleConfig.consoleConfig;

/**
 * Example shows how to embed JavaREPL inside your application. Note that your application needs to be running via JDK
 * for the JavaREPL to work.
 */
public class EmbedInApplication {
    public static void main(String... args) throws Exception {
        ConsoleConfig config = consoleConfig()
                .historyFile(new File(getProperty("user.home"), ".javarepl-embedded.history"))
                .commands(
                        ListValues.class,
                        ShowHistory.class,
                        EvaluateFromHistory.class,
                        SearchHistory.class)
                .results(
                        result("date", new Date()),
                        result("num", 42));

        new RestConsole(new SimpleConsole(config), 8001);
    }
}
