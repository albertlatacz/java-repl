package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class NotAValidCommand extends Command {
    public NotAValidCommand(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, null, startsWith(":"), null);
    }

    void execute(String expression, CommandResultCollector result) {
        result.logError(expression + " is not a valid command");
    }
}
