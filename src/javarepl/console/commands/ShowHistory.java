package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import com.googlecode.totallylazy.numbers.Numbers;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.expressions.ExternalAssignment;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Pair.functions.values;
import static com.googlecode.totallylazy.Predicates.instanceOf;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Strings.replace;
import static com.googlecode.totallylazy.Strings.startsWith;
import static com.googlecode.totallylazy.predicates.Not.not;
import static javarepl.Evaluation.functions.expression;
import static javarepl.Utils.listValues;
import static javarepl.expressions.Expression.functions.source;

public final class ShowHistory extends Command {
    private static final String COMMAND = ":hist";

    public ShowHistory(Evaluator evaluator) {
        super(evaluator, COMMAND + " [num] - shows the history (optional 'num' is number of evaluations to show)",
                startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(String expression) {
        Integer limit = parseNumericCommand(expression).second().getOrElse(evaluator().evaluations().size());
        Sequence<String> numberedHistory = numberedHistory(evaluator()).reverse().take(limit).reverse();

        if (!numberedHistory.isEmpty()) {
            System.out.println(listValues("History", numberedHistory));
        } else {
            System.out.println("No history.");
        }
    }

    public static Sequence<String> numberedHistory(Evaluator evaluator) {
        return Numbers.range(1)
                .zip(history(evaluator).map(expression().then(source()).then(replace("\n", "\n   "))))
                .map(values().then(Sequences.toString("  ")));
    }

    public static Sequence<Evaluation> history(Evaluator evaluator) {
        return evaluator.evaluations().filter(where(expression(), not(instanceOf(ExternalAssignment.class))));
    }
}
