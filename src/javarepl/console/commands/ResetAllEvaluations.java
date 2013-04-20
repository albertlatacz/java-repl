package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public class ResetAllEvaluations extends Command {
    private static final String COMMAND = ":reset";

    public ResetAllEvaluations(ConsoleLogger logger) {
        super(COMMAND + " - resets environment to initial state", equalTo(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public CommandResult call(Evaluator evaluator, String expression) throws Exception {
        CommandResultCollector resultCollector = createResultCollector(expression);

        evaluator.reset();
        resultCollector.logInfo("All variables has been cleared");

        return resultCollector.result();
    }
}
