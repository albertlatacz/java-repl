package javarepl.console.commands;

import javarepl.console.Console;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class NotAValidCommand extends Command {
    public NotAValidCommand() {
        super(null, startsWith(":"), null);
    }

    public void execute(Console console, String expression) {
        console.logger().error(expression + " is not a valid command");
    }
}
