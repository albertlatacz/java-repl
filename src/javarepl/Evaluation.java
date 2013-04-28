package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import javarepl.expressions.Expression;

public class Evaluation {
    private final Expression expression;
    private final Option<Result> result;

    private Evaluation(Expression expression, Option<Result> result) {
        this.expression = expression;
        this.result = result;
    }

    public Expression expression() {
        return expression;
    }

    public Option<Result> result() {
        return result;
    }

    public static Evaluation evaluation(Expression expression, Option<Result> result) {
        return new Evaluation(expression, result);
    }


    @Override
    public String toString() {
        return expression + "=>" + result;
    }

    @Override
    public int hashCode() {
        return (expression != null ? expression.hashCode() : 0) +
                (result != null ? result.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Evaluation &&
                (expression != null && expression.equals(((Evaluation) other).expression)) &&
                (result != null && result.equals(((Evaluation) other).result));
    }

    public static enum functions {
        ;

        public static Function1<Evaluation, Option<Result>> result() {
            return new Function1<Evaluation, Option<Result>>() {
                public Option<Result> call(Evaluation value) throws Exception {
                    return value.result;
                }
            };
        }

        public static Function1<Evaluation, Expression> expression() {
            return new Function1<Evaluation, Expression>() {
                public Expression call(Evaluation value) throws Exception {
                    return value.expression;
                }
            };
        }
    }
}
