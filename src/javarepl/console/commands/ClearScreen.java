package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.completion.CommandCompleter;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class ClearScreen extends Command {
    private static final String COMMAND = ":cls";
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ClearScreen(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " - clear screen", equalTo(COMMAND).or(equalTo(null)), new CommandCompleter(COMMAND));
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        this.logger.reset();
        this.logger.info("\033[2J");
    }
}
