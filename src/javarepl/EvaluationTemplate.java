package javarepl;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;
import static javarepl.Result.resultKey;
import static javarepl.Result.resultValue;

public abstract class EvaluationTemplate {
    private EvaluationContext context;

    public final void init(EvaluationContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public final <T> T valueOf(final String key) {
        return (T) context.results()
                .filter(where(Result.resultKey(), equalTo(key)))
                .map(Result.resultValue())
                .headOption()
                .getOrThrow(new IllegalArgumentException("Result '" + key + "' not found"));
    }
}
