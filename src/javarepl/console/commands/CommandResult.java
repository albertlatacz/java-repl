package javarepl.console.commands;

import javarepl.console.ConsoleLog;

public class CommandResult {
    private final String command;
    private final Iterable<ConsoleLog> logs;

    public CommandResult(String command, Iterable<ConsoleLog> logs) {
        this.command = command;
        this.logs = logs;
    }

    public String command() {
        return command;
    }

    public Iterable<ConsoleLog> logs() {
        return logs;
    }
}
