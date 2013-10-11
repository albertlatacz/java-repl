package javarepl.console.commands;


import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Predicates;
import com.googlecode.totallylazy.match;
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
        final Either<? extends Throwable, Evaluation> evaluation = evaluator.evaluate(expression);


        if (evaluation.isRight()) {
            final Option<Result> result = evaluation.right().result();
            Option<String> messsage = new match<Expression, String>() {
                String value(Method expr) {
                    return "Created method " + expr.signature();
                }

                String value(Import expr) {
                    return "Imported " + expr.typePackage();
                }

                String value(Type expr) {
                    return "Created type " + expr.canonicalName();
                }

                String value(Expression expr) {
                    return result.map(asString()).getOrElse("");
                }
            }.apply(evaluation.right().expression());

            logger.success(messsage.get());
        } else {
            if (evaluation.left() instanceof ExpressionCompilationException) {
                logger.error(evaluation.left().getMessage());
            } else {
                logger.error(evaluation.left().toString());
            }
        }

    }
}
