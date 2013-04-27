package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.Evaluator;
import javarepl.Result;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;

public final class ShowResult extends Command {
    private static final String COMMAND = ":result";

    public ShowResult(Evaluator evaluator) {
        super(evaluator, COMMAND + " <name> - shows the result of evaluation", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(String expression) {
        String key = parseStringCommand(expression).second().getOrElse("");
        Option<Result> result = evaluator().result(key);

        if (!result.isEmpty()) {
            System.out.println(result.get().toString(true));
        } else {
            System.out.println(format("Cannot find result %s.", key));
        }
    }


}
