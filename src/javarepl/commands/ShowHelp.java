package javarepl.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.Utils.listValues;

public class ShowHelp extends Command {
    private static final String COMMAND = ":help";

    private final Sequence<Command> commands;

    public ShowHelp(Sequence<Command> commands) {
        super(COMMAND + " - shows this help", equalTo(COMMAND), new StringsCompleter(COMMAND));
        this.commands = commands.cons(this);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        listValues("Available commands", commands.map(functions.description()));
        return null;
    }
}
