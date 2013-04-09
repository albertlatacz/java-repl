package javarepl.commands;

import com.googlecode.totallylazy.Option;
import javarepl.Evaluation;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;

public class EvaluateFromHistory extends Command {
    private static final String COMMAND = ":h!";

    public EvaluateFromHistory() {
        super(COMMAND + " [num] - evaluate last expression (optional 'num' to evaluate expression from history)",
                startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        Integer historyItem = parseNumericCommand(expression).second().getOrElse(evaluator.evaluations().size());
        Option<Evaluation> evaluation = evaluator.evaluations().drop(historyItem - 1).headOption();
        if (!evaluation.isEmpty()) {
            String source = evaluation.get().expression().source();
            System.out.println(source);
            EvaluateExpression.evaluate(evaluator, source);
        } else {
            System.err.println("Expression not found.\n");
        }

        return null;
    }
}
