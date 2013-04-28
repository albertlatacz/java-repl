package javarepl.console.commands;

import javarepl.console.Console;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class NotAValidCommand extends Command {
    public NotAValidCommand(Console console) {
        super(console, null, startsWith(":"), null);
    }

    public void execute(String expression) {
        System.err.println(expression + " is not a valid command");
    }
}
