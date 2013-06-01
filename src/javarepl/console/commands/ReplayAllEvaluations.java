package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import javarepl.completion.CommandCompleter;
import javarepl.console.ConsoleLogger;
import javarepl.expressions.Expression;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.EvaluationContext.defaultExpressions;
import static javarepl.console.commands.EvaluateExpression.evaluate;

public final class ReplayAllEvaluations extends Command {
    private static final String COMMAND = ":replay";
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ReplayAllEvaluations(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " - replay all evaluations", equalTo(COMMAND), new CommandCompleter(COMMAND));
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String line) {
        logger.success("Replaying all evaluations:");
        Sequence<Expression> expressions = evaluator.expressions().removeAll(defaultExpressions());

        evaluator.reset();
        for (Expression expression : expressions) {
            evaluate(evaluator, logger, expression.source());
        }
    }
}
