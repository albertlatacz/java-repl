package javarepl;

import com.googlecode.totallylazy.*;

import static com.googlecode.totallylazy.Option.functions.get;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.Evaluation.expression;
import static javarepl.Evaluation.result;
import static javarepl.Result.key;

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
                .filter(instanceOf(Expression.Import.class));
    }

    public Sequence<Result> results() {
        return evaluations()
                .map(result())
                .filter(is(Predicates.not(Result.noResult())))
                .map(get(Result.class))
                .reverse()
                .unique(key())
                .reverse();
    }

    public Option<Evaluation> evaluationForResult(final String key) {
        return evaluations()
                .reverse()
                .filter(where(result(), is(Predicates.not(Result.noResult()))).and(
                        where(result().then(get(Result.class)).then(key()), equalTo(key))))
                .headOption();
    }

    public String nextResultKey() {
        return "res" + results().size();
    }

    public EvaluationContext addEvaluation(Evaluation evaluation) {
        return new EvaluationContext(evaluations().add(evaluation));
    }
}
