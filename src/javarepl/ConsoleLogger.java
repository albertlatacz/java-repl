package javarepl;

import com.googlecode.totallylazy.Function2;

public abstract class ConsoleLogger extends Function2<ConsoleLogger.LogType, String, Void> {
    public static enum LogType {
        ERROR, INFO
    }

    public final void logError(String message) {
        apply(LogType.ERROR, message);
    }

    public final void logInfo(String message) {
        apply(LogType.INFO, message);
    }

}
