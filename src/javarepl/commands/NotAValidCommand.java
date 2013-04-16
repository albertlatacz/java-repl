package javarepl.commands;

import javarepl.ConsoleLogger;
import javarepl.Evaluator;

import static com.googlecode.totallylazy.Strings.startsWith;

public class NotAValidCommand extends Command {
    public NotAValidCommand(ConsoleLogger logger) {
        super(null, startsWith(":"), null, logger);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        logError(expression + " is not a valid command");
        return null;
    }
}
