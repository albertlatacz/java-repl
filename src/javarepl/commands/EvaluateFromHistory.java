package javarepl.commands;

import com.googlecode.totallylazy.Option;
import javarepl.ConsoleLogger;
import javarepl.Evaluation;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.commands.EvaluateExpression.evaluate;

public class EvaluateFromHistory extends Command {
    private static final String COMMAND = ":h!";

    public EvaluateFromHistory(ConsoleLogger logger) {
        super(COMMAND + " [num] - evaluate last expression (optional 'num' to evaluate expression from history)",
                startsWith(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        Integer historyItem = parseNumericCommand(expression).second().getOrElse(evaluator.evaluations().size());
        Option<Evaluation> evaluation = evaluator.evaluations().drop(historyItem - 1).headOption();
        if (!evaluation.isEmpty()) {
            String source = evaluation.get().expression().source();
            logInfo(source);
            evaluate(this, evaluator, source);
        } else {
            logError("Expression not found.\n");
        }

        return null;
    }
}
