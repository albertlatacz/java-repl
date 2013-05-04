package javarepl.console.commands;


import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Predicates;
import com.googlecode.totallylazy.pattern;
import javarepl.Evaluation;
import javarepl.ExpressionCompilationException;
import javarepl.Result;
import javarepl.console.Console;
import javarepl.expressions.Expression;
import javarepl.expressions.Import;
import javarepl.expressions.Method;
import javarepl.expressions.Type;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Strings.blank;

public final class EvaluateExpression extends Command {
    public EvaluateExpression() {
        super(null, Predicates.<String>not(blank()), null);
    }

    public void execute(Console console, String expression) {
        evaluate(console, expression);
    }

    public static void evaluate(Console console, String expression) {
        Either<? extends Throwable, Evaluation> evaluation = console.evaluator().evaluate(expression);

        if (evaluation.isRight()) {
            System.out.println(new pattern(evaluation.right().expression(), evaluation.right().result()) {
                String match(Method expr, Option<Result> result) {
                    return "Created method " + expr.signature();
                }

                String match(Import expr, Option<Result> result) {
                    return "Imported " + expr.typePackage();
                }

                String match(Type expr, Option<Result> result) {
                    return "Created type " + expr.canonicalName();
                }

                String match(Expression expr, Option<Result> result) {
                    return result.map(asString()).getOrElse("");
                }
            }.match());
        } else {
            if (evaluation.left() instanceof ExpressionCompilationException) {
                System.err.println(evaluation.left().getMessage());
            } else {
                System.err.println(evaluation.left().toString());
            }
        }

    }
}
