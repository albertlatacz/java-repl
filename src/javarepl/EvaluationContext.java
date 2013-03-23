package javarepl;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Predicates;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import static com.googlecode.totallylazy.Option.functions.get;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.Evaluation.evaluationExpression;
import static javarepl.Evaluation.evaluationResult;
import static javarepl.Expression.Type.IMPORT;
import static javarepl.Expression.expressionType;
import static javarepl.Result.noResult;
import static javarepl.Result.resultKey;

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
                .filter(where(evaluationExpression().then(Expression.expressionType()), Predicates.is(Expression.Type.IMPORT)));
    }

    public Sequence<Result> results() {
        return evaluations()
                .map(evaluationResult())
                .filter(is(Predicates.not(Result.noResult())))
                .map(get(Result.class));
    }

    public Option<Evaluation> evaluationForResult(final String key) {
        return evaluations()
                .filter(where(evaluationResult(), is(Predicates.not(Result.noResult()))).and(
                        where(evaluationResult().then(get(Result.class)).then(Result.resultKey()), equalTo(key))))
                .headOption();
    }

    public String nextResultKey() {
        return "res" + results().size();
    }

    public EvaluationContext addEvaluation(Evaluation evaluation) {
        return new EvaluationContext(evaluations().add(evaluation));
    }
}
