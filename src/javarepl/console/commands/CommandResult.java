package javarepl.console.commands;

import javarepl.console.ConsoleLog;

import java.util.ArrayList;
import java.util.List;

public class CommandResult {
    private final String command;
    private final List<ConsoleLog> logs;

    public CommandResult(String command, List<ConsoleLog> logs) {
        this.command = command;
        this.logs = new ArrayList<ConsoleLog>(logs);
    }

    public String command() {
        return command;
    }

    public List<ConsoleLog> logs() {
        return logs;
    }
}
