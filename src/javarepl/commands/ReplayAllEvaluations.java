package javarepl.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.ConsoleLogger;
import javarepl.Evaluation;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.commands.EvaluateExpression.evaluate;

public class ReplayAllEvaluations extends Command {
    private static final String COMMAND = ":replay";

    public ReplayAllEvaluations(ConsoleLogger logger) {
        super(COMMAND + " - replay all evaluations", equalTo(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public Void call(final Evaluator evaluator, String s) throws Exception {
        logInfo("Replaying all evaluations:");
        Sequence<Evaluation> evaluations = evaluator.evaluations();
        evaluator.reset();

        for (Evaluation evaluation : evaluations) {
            evaluate(this, evaluator, evaluation.expression().source());
        }

        return null;
    }
}
