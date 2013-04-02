package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import javarepl.expressions.Expression;

public class Evaluation {
    private final String className;
    private final String classSource;
    private final Expression expression;
    private final Option<Result> result;

    private Evaluation(String className, String classSource, Expression expression, Option<Result> result) {
        this.className = className;
        this.classSource = classSource;
        this.expression = expression;
        this.result = result;
    }

    public String className() {
        return className;
    }

    public String classSource() {
        return classSource;
    }

    public Expression expression() {
        return expression;
    }

    public Option<Result> result() {
        return result;
    }

    public static Evaluation evaluation(String className, String classSource, Expression expression, Option<Result> result) {
        return new Evaluation(className, classSource, expression, result);
    }


    @Override
    public String toString() {
        return expression + "=>" + result + " in " + className;
    }

    @Override
    public int hashCode() {
        return (className != null ? className.hashCode() : 0) +
                (classSource != null ? classSource.hashCode() : 0) +
                (expression != null ? expression.hashCode() : 0) +
                (result != null ? result.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Evaluation &&
                (className != null && className.equals(((Evaluation) other).className)) &&
                (classSource != null && classSource.equals(((Evaluation) other).classSource)) &&
                (expression != null && expression.equals(((Evaluation) other).expression)) &&
                (result != null && result.equals(((Evaluation) other).result));
    }

    public static enum functions {;
        public static Function1<Evaluation, String> classSource() {
            return new Function1<Evaluation, String>() {
                public String call(Evaluation evaluation) throws Exception {
                    return evaluation.classSource;
                }
            };
        }

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
