package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Strings.startsWith;

public class NotAValidCommand extends Command {
    public NotAValidCommand(ConsoleLogger logger) {
        super(null, startsWith(":"), null, logger);
    }

    public CommandResult call(Evaluator evaluator, String expression) throws Exception {
        CommandResultCollector resultCollector = createResultCollector(expression);
        resultCollector.logError(expression + " is not a valid command");
        return resultCollector.result();
    }
}
