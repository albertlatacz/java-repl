package javarepl;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;
import static javarepl.Result.functions.key;
import static javarepl.Result.functions.value;

@SuppressWarnings("unused")
public abstract class EvaluationTemplate {
    private final EvaluationContext context;

    public EvaluationTemplate(EvaluationContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public final <T> T valueOf(final String key) {
        return (T) context.results()
                .filter(where(key(), equalTo(key)))
                .map(value())
                .headOption()
                .getOrThrow(new IllegalArgumentException("Result '" + key + "' not found"));
    }
}
