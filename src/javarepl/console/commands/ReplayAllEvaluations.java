package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluation;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.console.commands.EvaluateExpression.evaluate;

public final class ReplayAllEvaluations extends Command {
    private static final String COMMAND = ":replay";

    public ReplayAllEvaluations(Evaluator evaluator) {
        super(evaluator, COMMAND + " - replay all evaluations", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(String expression) {
        System.out.println("Replaying all evaluations:");
        Sequence<Evaluation> evaluations = evaluator().evaluations();
        evaluator().reset();

        for (Evaluation evaluation : evaluations) {
            evaluate(evaluator(), evaluation.expression().source());
        }
    }
}
