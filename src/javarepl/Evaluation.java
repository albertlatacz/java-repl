package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;

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

    public static Evaluation evaluation(String className, String classSource, Expression expression, Option<Result> result) {
        return new Evaluation(className, classSource, expression, result);
    }

    public String getClassName() {
        return className;
    }

    public String getClassSource() {
        return classSource;
    }

    public Expression getExpression() {
        return expression;
    }

    public Option<Result> getResult() {
        return result;
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

    public static enum ExpressionType {
        IMPORT, EVALUATION
    }

    public static Function1<Evaluation, String> classSource() {
        return new Function1<Evaluation, String>() {
            public String call(Evaluation evaluation) throws Exception {
                return evaluation.getClassSource();
            }
        };
    }

    public static Function1<Evaluation, Option<Result>> evaluationResult() {
        return new Function1<Evaluation, Option<Result>>() {
            public Option<Result> call(Evaluation value) throws Exception {
                return value.result;
            }
        };
    }

    public static Function1<Evaluation, Expression> evaluationExpression() {
        return new Function1<Evaluation, Expression>() {
            public Expression call(Evaluation value) throws Exception {
                return value.expression;
            }
        };
    }
}
