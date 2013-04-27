package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.Utils.listValues;

public final class ShowHelp extends Command {
    private static final String COMMAND = ":help";

    private final Sequence<Command> commands;

    public ShowHelp(Sequence<Command> commands, Evaluator evaluator) {
        super(evaluator, COMMAND + " - shows this help", equalTo(COMMAND), new StringsCompleter(COMMAND));
        this.commands = commands.cons(this);
    }

    public void execute(String expression) {
        System.out.println(listValues("Available commands", commands));
    }
}
