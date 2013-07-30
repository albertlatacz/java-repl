package javarepl.expressions;

public final class AssignmentWithType extends Expression {
    private final Class<?> type;
    private final String key;
    private final String value;

    public AssignmentWithType(String source, Class<?> type, String key, String value) {
        super(source);

        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String key() {
        return key;
    }

    public Class<?> type() {
        return type;
    }

    public String value() {
        return value;
    }
}
