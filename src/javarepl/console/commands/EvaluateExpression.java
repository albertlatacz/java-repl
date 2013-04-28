package javarepl.console.commands;


import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Predicates;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.ExpressionCompilationException;
import javarepl.console.Console;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Strings.blank;

public final class EvaluateExpression extends Command {
    public EvaluateExpression(Console console) {
        super(console, null, Predicates.<String>not(blank()), null);
    }

    public void execute(String expression) {
        evaluate(evaluator(), expression);
    }

    public static void evaluate(Evaluator evaluator, String expression) {
        Either<? extends Throwable, Evaluation> evaluation = evaluator.evaluate(expression);

        if (evaluation.isRight()) {
            System.out.println(evaluation.right().result().map(asString()).getOrElse(""));
        } else {
            if (evaluation.left() instanceof ExpressionCompilationException) {
                System.err.println(evaluation.left().getMessage());
            } else {
                System.err.println(evaluation.left().toString());
            }
        }

    }
}
