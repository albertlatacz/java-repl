package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.console.commands.EvaluateExpression.evaluate;

public final class ReplayAllEvaluations extends Command {
    private static final String COMMAND = ":replay";

    public ReplayAllEvaluations(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " - replay all evaluations", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector result) {
        result.logInfo("Replaying all evaluations:");
        Sequence<Evaluation> evaluations = evaluator().evaluations();
        evaluator().reset();

        for (Evaluation evaluation : evaluations) {
            evaluate(result, evaluator(), evaluation.expression().source());
        }
    }
}
