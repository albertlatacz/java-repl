package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class QuitApplication extends Command {
    private static final String COMMAND = ":quit";
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public QuitApplication(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " - quit application", equalTo(COMMAND).or(equalTo(null)), new StringsCompleter(COMMAND));
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        logger.success("Terminating...");
        evaluator.clearOutputDirectory();
        System.exit(0);
    }
}
