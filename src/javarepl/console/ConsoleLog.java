package javarepl.console;

public class ConsoleLog {
    public static enum Type {
        INFO, SUCCESS, ERROR
    }

    private final Type type;
    private final String message;

    public ConsoleLog(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type type() {
        return type;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return type + " " + message;
    }

    @Override
    public int hashCode() {
        return (type != null ? type.hashCode() : 0) +
                (message != null ? message.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ConsoleLog &&
                (type != null && type.equals(((ConsoleLog) other).type)) &&
                (message != null && message.equals(((ConsoleLog) other).message));
    }
}
