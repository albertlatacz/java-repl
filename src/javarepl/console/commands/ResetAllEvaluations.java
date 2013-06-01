package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.completion.CommandCompleter;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class ResetAllEvaluations extends Command {
    private static final String COMMAND = ":reset";
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ResetAllEvaluations(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " - resets environment to initial state", equalTo(COMMAND), new CommandCompleter(COMMAND));
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        evaluator.reset();
        logger.success("All variables has been cleared");
    }
}
