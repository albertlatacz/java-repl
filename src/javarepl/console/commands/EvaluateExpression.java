package javarepl.console.commands;


import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Predicates;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.ExpressionCompilationException;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Strings.blank;

public final class EvaluateExpression extends Command {
    public EvaluateExpression(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, null, Predicates.<String>not(blank()), null);
    }

    void execute(String expression, CommandResultCollector resultCollector) {
        evaluate(resultCollector, evaluator(), expression);
    }

    public static void evaluate(CommandResultCollector resultCollector, Evaluator evaluator, String expression) {
        Either<? extends Throwable, Evaluation> evaluation = evaluator.evaluate(expression);

        if (evaluation.isRight()) {
            resultCollector.logInfo(evaluation.right().result().map(asString()).getOrElse(""));
        } else {
            if (evaluation.left() instanceof ExpressionCompilationException) {
                resultCollector.logError(evaluation.left().getMessage());
            } else {
                resultCollector.logError(evaluation.left().toString());
            }
        }

    }
}
