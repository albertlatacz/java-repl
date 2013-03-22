package repler.java;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;
import static repler.java.Result.resultKey;
import static repler.java.Result.resultValue;

public abstract class EvaluationTemplate {
    private EvaluationContext context;

    public final void init(EvaluationContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public final <T> T valueOf(final String key) {
        return (T) context.results()
                .filter(where(resultKey(), equalTo(key)))
                .map(resultValue())
                .headOption()
                .getOrThrow(new IllegalArgumentException("Result '" + key + "' not found"));
    }
}
