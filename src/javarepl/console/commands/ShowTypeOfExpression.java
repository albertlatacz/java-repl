package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;

public class ShowTypeOfExpression extends Command {
    private static final String COMMAND = ":type";

    public ShowTypeOfExpression(ConsoleLogger logger) {
        super(COMMAND + " <expression> - shows the type of an expression without affecting current context", startsWith(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public CommandResult call(Evaluator evaluator, String expression) throws Exception {
        CommandResultCollector resultCollector = createResultCollector(expression);
        Option<Class> expressionType = evaluator.typeOfExpression(parseStringCommand(expression).second().getOrElse(""));

        if (!expressionType.isEmpty()) {
            resultCollector.logInfo(expressionType.get().getCanonicalName());
        } else {
            resultCollector.logError("Cannot determine the type of this expression.");
        }

        return resultCollector.result();
    }


}
