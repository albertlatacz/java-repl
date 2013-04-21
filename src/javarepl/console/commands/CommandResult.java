package javarepl.console.commands;

import javarepl.console.ConsoleLog;

import java.util.List;

import static com.googlecode.totallylazy.Sequences.sequence;

public class CommandResult {
    private final String command;
    private final List<ConsoleLog> logs;

    public CommandResult(String command, List<ConsoleLog> logs) {
        this.command = command;
        this.logs = sequence(logs).toList();
    }

    public String command() {
        return command;
    }

    public List<ConsoleLog> logs() {
        return logs;
    }
}
