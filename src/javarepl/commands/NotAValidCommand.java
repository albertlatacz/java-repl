package javarepl.commands;

import javarepl.Evaluator;

import static com.googlecode.totallylazy.Strings.startsWith;

public class NotAValidCommand extends Command {
    public NotAValidCommand() {
        super(null, startsWith(":"), null);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        System.err.println(expression + " is not a valid command");
        return null;
    }
}
