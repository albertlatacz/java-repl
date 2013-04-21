package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.console.ConsoleLog;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Sequences.empty;

public final class CommandResultCollector {
    private final ConsoleLogger logger;
    private final String command;
    private Sequence<ConsoleLog> logs = empty();

    CommandResultCollector(ConsoleLogger logger, String command) {
        this.logger = logger;
        this.command = command;
    }

    public CommandResultCollector logError(String message) {
        logger.logError(message);
        logs = logs.add(new ConsoleLog(ConsoleLog.Type.ERROR, message));
        return this;
    }

    public CommandResultCollector logInfo(String message) {
        logger.logInfo(message);
        logs = logs.add(new ConsoleLog(ConsoleLog.Type.INFO, message));
        return this;
    }

    public CommandResult result() {
        return new CommandResult(command, logs.toList());
    }

}
