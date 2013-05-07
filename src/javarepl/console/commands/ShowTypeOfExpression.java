package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.console.Console;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class ShowTypeOfExpression extends Command {
    private static final String COMMAND = ":type";

    public ShowTypeOfExpression() {
        super(COMMAND + " <expression> - shows the type of an expression without affecting current context", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(Console console, String expression) {
        Option<Class> expressionType = console.evaluator().typeOfExpression(parseStringCommand(expression).second().getOrElse(""));

        if (!expressionType.isEmpty()) {
            console.logger().info(expressionType.get().getCanonicalName());
        } else {
            console.logger().error("Cannot determine the type of this expression.");
        }
    }

}
