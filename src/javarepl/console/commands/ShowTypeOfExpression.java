package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class ShowTypeOfExpression extends Command {
    private static final String COMMAND = ":type";
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ShowTypeOfExpression(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " <expression> - shows the type of an expression without affecting current context", startsWith(COMMAND), new StringsCompleter(COMMAND));

        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        Option<Class> expressionType = evaluator.typeOfExpression(parseStringCommand(expression).second().getOrElse(""));

        if (!expressionType.isEmpty()) {
            logger.info(expressionType.get().getCanonicalName());
        } else {
            logger.error("Cannot determine the type of this expression.");
        }
    }

}
