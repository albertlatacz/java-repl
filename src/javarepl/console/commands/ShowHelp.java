package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.Utils.listValues;

public final class ShowHelp extends Command {
    private static final String COMMAND = ":help";

    private final Sequence<Command> commands;

    public ShowHelp(Sequence<Command> commands, ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " - shows this help", equalTo(COMMAND), new StringsCompleter(COMMAND));
        this.commands = commands.cons(this);
    }

    void execute(String expression, CommandResultCollector result) {
        result.logInfo(listValues("Available commands", commands));
    }
}
