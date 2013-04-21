package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.Evaluator;
import javarepl.Result;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;

public final class ShowResult extends Command {
    private static final String COMMAND = ":result";

    public ShowResult(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " <name> - shows the result of evaluation", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector resultCollector) {
        String key = parseStringCommand(expression).second().getOrElse("");
        Option<Result> result = evaluator().result(key);

        if (!result.isEmpty()) {
            resultCollector.logInfo(result.get().toString(true));
        } else {
            resultCollector.logError(format("Cannot find result %s.", key));
        }
    }


}
