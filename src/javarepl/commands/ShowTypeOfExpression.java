package javarepl.commands;

import com.googlecode.totallylazy.Option;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;

public class ShowTypeOfExpression extends Command {
    private static final String COMMAND = ":type";

    public ShowTypeOfExpression() {
        super(COMMAND + " <expression> - shows the type of an expression without evaluating it", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        Option<Class> expressionType = evaluator.typeOfExpression(parseStringCommand(expression).second().getOrElse(""));

        if (!expressionType.isEmpty()) {
            System.out.println(expressionType.get().getCanonicalName());
        } else {
            System.err.println("Cannot determine the type of this expression.");
        }

        return null;
    }


}
