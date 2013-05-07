package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.console.Console;
import javarepl.console.ConsoleLog;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Predicates.*;
import static javarepl.Utils.listValues;

public final class ShowHelp extends Command {
    private static final String COMMAND = ":help";

    private final Sequence<Command> commands;

    public ShowHelp(Sequence<Command> commands) {
        super(COMMAND + " - shows this help", equalTo(COMMAND), new StringsCompleter(COMMAND));
        this.commands = commands.cons(this);
    }

    public void execute(Console console, String expression) {
        console.logger().log(new ConsoleLog(ConsoleLog.Type.INFO, listValues("Available commands", commands.map(asString()).filter(not(nullValue())))));
    }
}
