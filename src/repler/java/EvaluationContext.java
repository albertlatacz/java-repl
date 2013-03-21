package repler.java;

import com.googlecode.totallylazy.*;

import java.util.List;

import static com.googlecode.totallylazy.Pair.pair;
import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Sequences.sequence;
import static repler.java.Result.functions.key;

public final class EvaluationContext {
    private final Sequence<Pair<Expression, Result>> evaluations;
    private final Sequence<Expression> imports;

    private EvaluationContext(Sequence<Pair<Expression, Result>> evaluations, Sequence<Expression> imports) {
        this.evaluations = sequence(evaluations);
        this.imports = sequence(imports);
    }

    public static EvaluationContext emptyContext() {
        return new EvaluationContext(Sequences.<Pair<Expression, Result>>empty(), Sequences.<Expression>empty());
    }

    public List<Result> getResults() {
        return evaluations.map(Callables.<Result>second()).toList();
    }

    public Sequence<Pair<Expression, Result>> getEvaluations() {
        return evaluations;
    }

    public Option<Pair<Expression, Result>> getLastEvaluation() {
        Option<Pair<Expression, Result>> pairs = evaluations.headOption();
        return pairs;
    }

    public Sequence<Expression> getImports() {
        return imports;
    }

    public String nextVal() {
        return "res" + evaluations.size();
    }

    public Option<Result> resultByKey(final String key) {
        return evaluations.find(where(Callables.<Result>second().then(key()), equalTo(key))).map(Callables.<Result>second());
    }

    public EvaluationContext addEvaluation(Expression expression, Result result) {
        return new EvaluationContext(evaluations.cons(pair(expression, result)), imports);
    }

    public EvaluationContext addImport(Expression imp) {
        return new EvaluationContext(evaluations, imports.cons(imp));
    }

}
