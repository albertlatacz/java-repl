package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.Utils.listValues;

public class ShowHelp extends Command {
    private static final String COMMAND = ":help";

    private final Sequence<Command> commands;

    public ShowHelp(Sequence<Command> commands, ConsoleLogger logger) {
        super(COMMAND + " - shows this help", equalTo(COMMAND), new StringsCompleter(COMMAND), logger);
        this.commands = commands.cons(this);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        logInfo(listValues("Available commands", commands));
        return null;
    }
}
