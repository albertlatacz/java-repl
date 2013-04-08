package javarepl.commands;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluation;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.Integer.parseInt;

public class EvaluateFromHistory extends Command {
    private static final String COMMAND = ":h!";

    public EvaluateFromHistory() {
        super(COMMAND + "  [num] - evaluate last expression (optional 'num' to evaluate expression from history)",
                startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        Sequence<String> splitLine = sequence(expression.split(" "));
        Integer historyItem = (splitLine.size() == 2) ? parseInt(splitLine.second()) : evaluator.evaluations().size();
        Option<Evaluation> lastEvaluation = evaluator.evaluations().drop(historyItem - 1).headOption();
        if (!lastEvaluation.isEmpty()) {
            String source = lastEvaluation.get().expression().source();
            System.out.println(source);
            EvaluateExpression.evaluate(evaluator, source);
        } else {
            System.err.println("Expression not found.\n");
        }

        return null;
    }
}
