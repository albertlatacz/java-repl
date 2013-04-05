package javarepl;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import javarepl.expressions.Expression;
import javarepl.expressions.Import;
import javarepl.expressions.Method;
import javarepl.expressions.Type;

import static com.googlecode.totallylazy.Option.functions.get;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.Evaluation.functions.expression;
import static javarepl.Evaluation.functions.result;
import static javarepl.Result.functions.key;
import static javarepl.Result.noResult;

public class EvaluationContext {
    private final Sequence<Evaluation> evaluations;

    private EvaluationContext(Sequence<Evaluation> evaluations) {
        this.evaluations = sequence(evaluations);
    }

    public static EvaluationContext emptyEvaluationContext() {
        return new EvaluationContext(Sequences.<Evaluation>empty());
    }

    public Option<Evaluation> lastEvaluation() {
        return evaluations().lastOption();
    }

    public Sequence<Evaluation> evaluations() {
        return evaluations;
    }

    public <T extends Expression> Sequence<T> expressionsOfType(Class<T> type) {
        return evaluations()
                .filter(where(expression(), instanceOf(type)))
                .map(expression())
                .safeCast(type);
    }

    public Sequence<Result> results() {
        return evaluations()
                .map(result())
                .filter(is(not(noResult())))
                .map(get(Result.class))
                .reverse()
                .unique(key());
    }

    public Option<Evaluation> evaluationForResult(final String key) {
        return evaluations()
                .reverse()
                .filter(where(result(), is(not(noResult()))).and(
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
