package javarepl.expressions;

public final class ExternalAssignment extends Expression {
    private final String key;
    private final Object oldValue;
    private final Object newValue;

    public ExternalAssignment(String source, String key, Object oldValue, Object newValue) {
        super(source);
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String key() {
        return key;
    }

    public Object oldValue() {
        return oldValue;
    }

    public Object newValue() {
        return newValue;
    }
}
