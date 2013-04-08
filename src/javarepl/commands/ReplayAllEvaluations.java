package javarepl.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluation;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public class ReplayAllEvaluations extends Command {
    private static final String COMMAND = ":replay";

    public ReplayAllEvaluations() {
        super(COMMAND + " - replay all evaluations", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public Void call(final Evaluator evaluator, String s) throws Exception {
        System.out.println("Replaying all evaluations:");
        Sequence<Evaluation> evaluations = evaluator.evaluations();
        evaluator.clear();

        for (Evaluation evaluation : evaluations) {
            EvaluateExpression.evaluate(evaluator, evaluation.expression().source());
        }

        return null;
    }
}
