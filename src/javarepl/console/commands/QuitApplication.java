package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class QuitApplication extends Command {
    private static final String COMMAND = ":quit";

    public QuitApplication(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " - quit application", equalTo(COMMAND).or(equalTo(null)), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector resultCollector) {
        resultCollector.logInfo("Terminating...");
        evaluator().clearOutputDirectory();
        System.exit(0);
    }
}
