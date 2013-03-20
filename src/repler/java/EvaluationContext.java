package repler.java;

import com.googlecode.totallylazy.*;

import java.util.List;

import static com.googlecode.totallylazy.Callables.second;
import static com.googlecode.totallylazy.Pair.pair;
import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;
import static repler.java.Result.functions.key;

public final class EvaluationContext {
    private final Sequence<Pair<Expression, Result>> evaluations;

    private EvaluationContext(Sequence<Pair<Expression, Result>> evaluations) {
        this.evaluations = evaluations;
    }

    public static EvaluationContext emptyContext() {
        return new EvaluationContext(Sequences.<Pair<Expression, Result>>empty());
    }

    public List<Result> getResults() {
        return evaluations.map(Callables.<Result>second()).toList();
    }

    public Sequence<Pair<Expression, Result>> getEvaluations() {
        return evaluations;
    }

    public String nextVal() {
        return "res" + evaluations.size();
    }

    public Option<Result> resultByKey(final String key) {
        return evaluations.find(where(Callables.<Result>second().then(key()), equalTo(key))).map(Callables.<Result>second());
    }

    public EvaluationContext add(Expression expression, Result result) {
        return new EvaluationContext(evaluations.cons(pair(expression, result)));
    }

}
