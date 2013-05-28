package javarepl.client;

public class EvaluationLog {
    public static enum Type {
        INFO, SUCCESS, ERROR
    }

    private final Type type;
    private final String message;

    public EvaluationLog(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type type() {
        return type;
    }

    public String message() {
        return message;
    }
}
