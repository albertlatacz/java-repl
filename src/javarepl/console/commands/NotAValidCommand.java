package javarepl.console.commands;

import javarepl.Evaluator;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class NotAValidCommand extends Command {
    public NotAValidCommand(Evaluator evaluator) {
        super(evaluator, null, startsWith(":"), null);
    }

    public void execute(String expression) {
        System.err.println(expression + " is not a valid command");
    }
}
