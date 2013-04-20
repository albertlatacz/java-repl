package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.console.commands.EvaluateExpression.evaluate;

public class ReplayAllEvaluations extends Command {
    private static final String COMMAND = ":replay";

    public ReplayAllEvaluations(ConsoleLogger logger) {
        super(COMMAND + " - replay all evaluations", equalTo(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public CommandResult call(final Evaluator evaluator, String expression) throws Exception {
        CommandResultCollector resultCollector = createResultCollector(expression);
        resultCollector.logInfo("Replaying all evaluations:");
        Sequence<Evaluation> evaluations = evaluator.evaluations();
        evaluator.reset();

        for (Evaluation evaluation : evaluations) {
            evaluate(resultCollector, evaluator, evaluation.expression().source());
        }

        return resultCollector.result();
    }
}
