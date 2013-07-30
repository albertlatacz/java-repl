package javarepl.console.commands;

import com.googlecode.totallylazy.Strings;
import javarepl.Evaluator;
import javarepl.completion.CommandCompleter;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;
import static javarepl.Utils.resolveURL;

public final class EvaluateFile extends Command {
    private static final String COMMAND = ":eval";
    private final ConsoleLogger logger;
    private final Evaluator evaluator;

    public EvaluateFile(ConsoleLogger logger, Evaluator evaluator) {
        super(COMMAND + " <path> - evaluates all expressions from file (expression per line)", startsWith(COMMAND), new CommandCompleter(COMMAND));
        this.logger = logger;
        this.evaluator = evaluator;
    }

    public void execute(String expression) {
        String path = parseStringCommand(expression).second().getOrNull();
        try {
            for (String line : Strings.lines(resolveURL(path).openStream())) {
                EvaluateExpression.evaluate(evaluator, logger, line);
            }

            logger.success(format("Finished evaluating %s", path));
        } catch (Exception e) {
            logger.error(format("Could evaluate %s. %s", path, e.getLocalizedMessage()));
        }
    }
}
