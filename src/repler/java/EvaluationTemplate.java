package repler.java;

public abstract class EvaluationTemplate {
    private EvaluationContext context;

    public final void $init(EvaluationContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public final <T> T $val(final String key) {
        return (T) context.resultByKey(key).get().getValue();
    }

    public abstract Object $eval();
}
