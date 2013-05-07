package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import javarepl.expressions.Expression;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.console.commands.EvaluateExpression.evaluate;

public final class ReplayAllEvaluations extends Command {
    private static final String COMMAND = ":replay";
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ReplayAllEvaluations(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " - replay all evaluations", equalTo(COMMAND), new StringsCompleter(COMMAND));
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String line) {
        logger.info("Replaying all evaluations:");
        Sequence<Expression> expressions = evaluator.expressions();
        evaluator.reset();

        for (Expression expression : expressions) {
            evaluate(evaluator, logger, expression.source());
        }
    }
}
