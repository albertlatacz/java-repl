package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class ShowLastSource extends Command {
    private static final String COMMAND = ":src";

    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ShowLastSource(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " - show source of last evaluated expression", equalTo(COMMAND), new StringsCompleter(COMMAND));
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        logger.info(evaluator.lastSource().getOrElse("No source"));
    }
}
