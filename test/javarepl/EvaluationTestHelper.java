package javarepl;

import com.googlecode.totallylazy.Either;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class EvaluationTestHelper {

    public static Matcher<Either<? extends Throwable,Evaluation>> hasNoResult() {
        return new FeatureMatcher<Either<? extends Throwable,Evaluation>, Object>(Matchers.<Object>is(Result.noResult()), "result value", "result value") {
            protected Object featureValueOf(Either<? extends Throwable,Evaluation> evaluation) {
                return evaluation.right().result;
            }
        };
    }

    public static Matcher<Either<? extends Throwable,Evaluation>> hasCompilationError() {
        return new FeatureMatcher<Either<? extends Throwable,Evaluation>, Object>(Matchers.<Object>is(instanceOf(ExpressionCompilationException.class)), "result value", "result value") {
            protected Throwable featureValueOf(Either<? extends Throwable,Evaluation> evaluation) {
                return evaluation.left();
            }
        };
    }

    public static <T> Matcher<Either<? extends Throwable,Evaluation>> hasResult(T value) {
        return new FeatureMatcher<Either<? extends Throwable,Evaluation>, T>(is(value), "result value", "result value") {
            protected T featureValueOf(Either<? extends Throwable,Evaluation> evaluation) {
                return (T) evaluation.right().result.get().value;
            }
        };
    }


    public static Matcher<Either<? extends Throwable,Evaluation>> hasExpressionOfType(Class<?> clazz) {
        return new FeatureMatcher<Either<? extends Throwable,Evaluation>, Expression>(instanceOf(clazz), "result value", "result value") {
            protected Expression featureValueOf(Either<? extends Throwable,Evaluation> evaluation) {
                return evaluation.right().expression;
            }
        };
    }

    public static Either<? extends Throwable, Evaluation> evaluating(String... expressions) {
        Evaluator evaluator = new Evaluator();

        Either<? extends Throwable,Evaluation> result = null;
        for (String expression : expressions) {
            result = evaluator.evaluate(expression);
        }

        return result;
    }
}
