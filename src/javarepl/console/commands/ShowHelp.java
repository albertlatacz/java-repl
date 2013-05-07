package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.console.ConsoleLog;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Predicates.*;
import static javarepl.Utils.listValues;

public final class ShowHelp extends Command {
    private static final String COMMAND = ":help";
    private final Sequence<Command> commands;
    private final ConsoleLogger logger;

    public ShowHelp(Sequence<Command> commands, ConsoleLogger logger) {
        super(COMMAND + " - shows this help", equalTo(COMMAND), new StringsCompleter(COMMAND));
        this.logger = logger;
        this.commands = commands.cons(this);
    }

    public void execute(String expression) {
        logger.log(new ConsoleLog(ConsoleLog.Type.INFO, listValues("Available commands", commands.map(asString()).filter(not(nullValue())))));
    }
}
