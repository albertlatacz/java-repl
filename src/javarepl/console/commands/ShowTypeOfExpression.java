package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class ShowTypeOfExpression extends Command {
    private static final String COMMAND = ":type";

    public ShowTypeOfExpression(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " <expression> - shows the type of an expression without affecting current context", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector result) {
        Option<Class> expressionType = evaluator().typeOfExpression(parseStringCommand(expression).second().getOrElse(""));

        if (!expressionType.isEmpty()) {
            result.logInfo(expressionType.get().getCanonicalName());
        } else {
            result.logError("Cannot determine the type of this expression.");
        }
    }

}
