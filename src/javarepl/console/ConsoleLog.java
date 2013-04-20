package javarepl.console;

public class ConsoleLog {
    private final ConsoleLogger.LogType type;
    private final String message;

    public ConsoleLog(ConsoleLogger.LogType type, String message) {
        this.type = type;
        this.message = message;
    }

    public ConsoleLogger.LogType type() {
        return type;
    }

    public String message() {
        return message;
    }
}
