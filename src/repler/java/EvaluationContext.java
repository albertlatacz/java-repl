package repler.java;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import static com.googlecode.totallylazy.Option.functions.get;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static repler.java.Evaluation.evaluationExpression;
import static repler.java.Evaluation.evaluationResult;
import static repler.java.Expression.Type.IMPORT;
import static repler.java.Expression.expressionType;
import static repler.java.Result.noResult;
import static repler.java.Result.resultKey;

class EvaluationContext {
    private final Sequence<Evaluation> evaluations;

    private EvaluationContext(Sequence<Evaluation> evaluations) {
        this.evaluations = sequence(evaluations);
    }

    public static EvaluationContext emptyEvaluationContext() {
        return new EvaluationContext(Sequences.<Evaluation>empty());
    }

    public Sequence<Evaluation> evaluations() {
        return evaluations;
    }

    public Option<Evaluation> lastEvaluation() {
        return evaluations().lastOption();
    }

    public Sequence<Evaluation> imports() {
        return evaluations()
                .filter(where(evaluationExpression().then(expressionType()), is(IMPORT)));
    }

    public Sequence<Result> results() {
        return evaluations()
                .map(evaluationResult())
                .filter(is(not(noResult())))
                .map(get(Result.class));
    }

    public Option<Evaluation> evaluationForResult(final String key) {
        return evaluations()
                .filter(where(evaluationResult(), is(not(noResult()))).and(
                        where(evaluationResult().then(get(Result.class)).then(resultKey()), equalTo(key))))
                .headOption();
    }

    public String nextResultKey() {
        return "res" + results().size();
    }

    public EvaluationContext addEvaluation(Evaluation evaluation) {
        return new EvaluationContext(evaluations().add(evaluation));
    }
}
