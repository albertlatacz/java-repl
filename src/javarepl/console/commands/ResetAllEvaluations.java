package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class ResetAllEvaluations extends Command {
    private static final String COMMAND = ":reset";

    public ResetAllEvaluations(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " - resets environment to initial state", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector resultCollector) {
        evaluator().reset();
        resultCollector.logInfo("All variables has been cleared");
    }
}
