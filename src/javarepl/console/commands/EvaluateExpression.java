package javarepl.console.commands;


import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Predicates;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.ExpressionCompilationException;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Strings.blank;

public class EvaluateExpression extends Command {
    public EvaluateExpression(ConsoleLogger logger) {
        super(null, Predicates.<String>not(blank()), null, logger);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        evaluate(this, evaluator, expression);
        return null;
    }

    public static void evaluate(Command command, Evaluator evaluator, String expression) {
        Either<? extends Throwable, Evaluation> evaluation = evaluator.evaluate(expression);

        if (evaluation.isRight()) {
            command.logInfo(evaluation.right().result().map(asString()).getOrElse(""));
        } else {
            if (evaluation.left() instanceof ExpressionCompilationException) {
                command.logError(evaluation.left().getMessage());
            } else {
                command.logError(evaluation.left().toString());
            }
        }

    }
}
