package javarepl;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import javarepl.expressions.Expression;
import javarepl.expressions.Import;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.Result.functions.key;

public class EvaluationContext {
    private final Sequence<Expression> expressions;
    private final Sequence<Result> results;
    private final Option<String> lastSource;

    private EvaluationContext(Sequence<Expression> expressions, Sequence<Result> results, Option<String> lastSource) {
        this.expressions = expressions;
        this.results = results;
        this.lastSource = lastSource;
    }

    public static EvaluationContext evaluationContext() {
        return new EvaluationContext(defaultExpressions(), empty(Result.class), none(String.class));
    }

    public static Sequence<Expression> defaultExpressions() {
        return sequence(
                new Import("import java.lang.*", "java.lang.*"),
                new Import("import java.util.*", "java.util.*"),
                new Import("import java.math.*", "java.math.*"),
                new Import("import static java.lang.Math.*", "java.lang.Math.*")
        ).safeCast(Expression.class);
    }

    public Option<String> lastSource() {
        return lastSource;
    }

    public Sequence<Result> results() {
        return results
                .reverse()
                .unique(key())
                .reverse();
    }

    public Sequence<Expression> expressions() {
        return expressions;
    }

    public <T extends Expression> Sequence<T> expressionsOfType(Class<T> type) {
        return expressions
                .filter(instanceOf(type))
                .safeCast(type);
    }

    public Option<Result> result(final String key) {
        return results().filter(where(key(), equalTo(key))).headOption();
    }

    public String nextResultKey() {
        return "res" + results().size();
    }

    public EvaluationContext lastSource(String lastSource) {
        return new EvaluationContext(expressions, results, option(lastSource));
    }

    public EvaluationContext addResult(Result result) {
        return new EvaluationContext(expressions, results.add(result), lastSource);
    }

    public EvaluationContext addResults(Sequence<Result> result) {
        return new EvaluationContext(expressions, results.join(result), lastSource);
    }

    public EvaluationContext addExpression(Expression expression) {
        return new EvaluationContext(expressions.add(expression), results, lastSource);
    }

    public EvaluationContext removeExpressionWithKey(String key) {
        return new EvaluationContext(expressions.filter(where(Expression.functions.key(), not(key))), results, lastSource);
    }
}
