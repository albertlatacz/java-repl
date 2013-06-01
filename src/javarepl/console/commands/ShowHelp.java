package javarepl.console.commands;

import javarepl.completion.CommandCompleter;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Predicates.*;
import static javarepl.Utils.listValues;

public final class ShowHelp extends Command {
    private static final String COMMAND = ":help";
    private final Commands commands;
    private final ConsoleLogger logger;

    public ShowHelp(Commands commands, ConsoleLogger logger) {
        super(COMMAND + " - shows this help", equalTo(COMMAND), new CommandCompleter(COMMAND));
        this.commands = commands;
        this.logger = logger;
    }

    public void execute(String expression) {
        logger.success(listValues("Available commands", commands.userCommands().map(asString()).filter(not(nullValue()))));
    }
}
