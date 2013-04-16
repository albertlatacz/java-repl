package javarepl.commands;

import javarepl.ConsoleLogger;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public class QuitApplication extends Command {
    private static final String COMMAND = ":quit";

    public QuitApplication(ConsoleLogger logger) {
        super(COMMAND + " - quit application", equalTo(COMMAND).or(equalTo(null)), new StringsCompleter(COMMAND), logger);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        logInfo("Terminating...");
        evaluator.clearOutputDirectory();
        System.exit(0);
        return null;
    }
}
