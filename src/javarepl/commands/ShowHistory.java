package javarepl.commands;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import com.googlecode.totallylazy.numbers.Numbers;
import javarepl.ConsoleLogger;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Pair.functions.values;
import static com.googlecode.totallylazy.Strings.replace;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Evaluation.functions.expression;
import static javarepl.Utils.listValues;
import static javarepl.expressions.Expression.functions.source;

public class ShowHistory extends Command {
    private static final String COMMAND = ":hist";

    public ShowHistory(ConsoleLogger logger) {
        super(COMMAND + " [num] - shows the history (optional 'num' is number of evaluations to show)",
                startsWith(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        Integer limit = parseNumericCommand(expression).second().getOrElse(evaluator.evaluations().size());
        logInfo(listValues("History", history(evaluator).reverse().take(limit).reverse()));
        return null;
    }

    public static Sequence<String> history(Evaluator evaluator) {
        return Numbers.range(1)
                .zip(evaluator.evaluations().map(expression().then(source()).then(replace("\n", "\n   "))))
                .map(values().then(Sequences.toString("  ")));
    }
}
