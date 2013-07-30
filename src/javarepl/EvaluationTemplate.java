package javarepl;

import com.googlecode.totallylazy.Option;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;
import static javarepl.Result.functions.key;

@SuppressWarnings("unused")
public abstract class EvaluationTemplate {
    private final EvaluationContext context;

    public EvaluationTemplate(EvaluationContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public final <T> T valueOf(final String key) {
        Option<Result> result = context.results()
                .find(where(key(), equalTo(key)));

        if (result.isEmpty())
            throw new IllegalArgumentException("Result '" + key + "' not found");

        return (T) result.get().value();
    }
}
