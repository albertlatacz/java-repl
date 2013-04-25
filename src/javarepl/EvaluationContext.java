package javarepl;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import javarepl.expressions.Expression;

import static com.googlecode.totallylazy.Option.functions.get;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.Evaluation.functions.expression;
import static javarepl.Result.functions.key;
import static javarepl.Result.noResult;

public class EvaluationContext {
    private final Sequence<Evaluation> evaluations;

    private EvaluationContext(Sequence<Evaluation> evaluations) {
        this.evaluations = sequence(evaluations);
    }

    public static EvaluationContext evaluationContext() {
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
                .map(Evaluation.functions.result())
                .filter(is(not(noResult())))
                .map(get(Result.class))
                .reverse()
                .unique(key())
                .reverse();
    }

    public Option<Result> result(final String key) {
        return results().filter(where(key(), equalTo(key))).headOption();
    }

    public String nextResultKey() {
        return "res" + results().size();
    }

    public EvaluationContext addEvaluation(Evaluation evaluationIn) {
        return addEvaluations(sequence(evaluationIn));
    }

    public EvaluationContext addEvaluations(Iterable<Evaluation> evaluationIn) {
        return new EvaluationContext(evaluations().join(sequence(evaluationIn)));
    }
}
