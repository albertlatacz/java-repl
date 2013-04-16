package javarepl.commands;

import javarepl.ConsoleLogger;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public class ResetAllEvaluations extends Command {
    private static final String COMMAND = ":reset";

    public ResetAllEvaluations(ConsoleLogger logger) {
        super(COMMAND + " - resets environment to initial state", equalTo(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        evaluator.reset();
        logInfo("All variables has been cleared");
        return null;
    }
}
