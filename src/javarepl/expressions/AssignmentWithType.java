package javarepl.expressions;

public final class AssignmentWithType extends Expression {
    private final String type;
    private final String key;
    private final String value;

    public AssignmentWithType(String source, String type, String key, String value) {
        super(source);

        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String key() {
        return key;
    }

    public String type() {
        return type;
    }

    public String value() {
        return value;
    }
}
