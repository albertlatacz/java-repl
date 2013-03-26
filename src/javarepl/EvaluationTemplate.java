package javarepl;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;

public abstract class EvaluationTemplate {
    private EvaluationContext context;

    public final void init(EvaluationContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public final <T> T valueOf(final String key) {
        return (T) context.results()
                .filter(where(Result.key(), equalTo(key)))
                .map(Result.value())
                .headOption()
                .getOrThrow(new IllegalArgumentException("Result '" + key + "' not found"));
    }
}
