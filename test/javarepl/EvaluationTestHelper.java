package javarepl;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class EvaluationTestHelper {

    public static Matcher<Evaluation> hasNoResult() {
        return new FeatureMatcher<Evaluation, Object>(Matchers.<Object>is(Result.noResult()), "result value", "result value") {
            protected Object featureValueOf(Evaluation evaluation) {
                return evaluation.result;
            }
        };
    }

    public static <T> Matcher<Evaluation> hasResult(T value) {
        return new FeatureMatcher<Evaluation, T>(is(value), "result value", "result value") {
            protected T featureValueOf(Evaluation evaluation) {
                return (T) evaluation.result.get().value;
            }
        };
    }


    public static Matcher<Evaluation> hasExpressionOfType(Class<?> clazz) {
        return new FeatureMatcher<Evaluation, Expression>(instanceOf(clazz), "result value", "result value") {
            protected Expression featureValueOf(Evaluation evaluation) {
                return evaluation.expression;
            }
        };
    }

    public static Evaluation evaluating(String... expressions) {
        Evaluator evaluator = new Evaluator();

        for (String expression : expressions) {
            evaluator.evaluate(expression);
        }

        return evaluator.lastEvaluation().get();
    }
}
