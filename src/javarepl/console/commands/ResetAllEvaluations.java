package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class ResetAllEvaluations extends Command {
    private static final String COMMAND = ":reset";
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ResetAllEvaluations(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " - resets environment to initial state", equalTo(COMMAND), new StringsCompleter(COMMAND));
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        evaluator.reset();
        logger.info("All variables has been cleared");
    }
}
