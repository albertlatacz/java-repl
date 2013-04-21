package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class EvaluateFromHistory extends Command {
    private static final String COMMAND = ":h!";

    public EvaluateFromHistory(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " [num] - evaluate last expression (optional 'num' to evaluate expression from history)",
                startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector result) {
        Integer historyItem = parseNumericCommand(expression).second().getOrElse(evaluator().evaluations().size());
        Option<Evaluation> evaluation = evaluator().evaluations().drop(historyItem - 1).headOption();
        if (!evaluation.isEmpty()) {
            String source = evaluation.get().expression().source();
            result.logInfo(source);
            EvaluateExpression.evaluate(result, evaluator(), source);
        } else {
            result.logError("Expression not found.\n");
        }
    }
}
