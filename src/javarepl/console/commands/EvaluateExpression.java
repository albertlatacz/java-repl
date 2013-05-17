package javarepl.console.commands;


import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Predicates;
import com.googlecode.totallylazy.pattern;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.ExpressionCompilationException;
import javarepl.Result;
import javarepl.console.ConsoleLogger;
import javarepl.expressions.Expression;
import javarepl.expressions.Import;
import javarepl.expressions.Method;
import javarepl.expressions.Type;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Strings.blank;

public final class EvaluateExpression extends Command {

    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public EvaluateExpression(Evaluator evaluator, ConsoleLogger logger) {
        super(null, Predicates.<String>not(blank()), null);

        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        evaluate(evaluator, logger, expression);
    }

    public static void evaluate(Evaluator evaluator, ConsoleLogger logger, String expression) {
        Either<? extends Throwable, Evaluation> evaluation = evaluator.evaluate(expression);

        if (evaluation.isRight()) {
            logger.success(new pattern(evaluation.right().expression(), evaluation.right().result()) {
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
            }.<String>match());
        } else {
            if (evaluation.left() instanceof ExpressionCompilationException) {
                logger.error(evaluation.left().getMessage());
            } else {
                logger.error(evaluation.left().toString());
            }
        }

    }
}
