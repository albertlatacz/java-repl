package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

/**
 * Run the garbage collector.
 */
public final class GarbageCollection extends Command {
    private static final String COMMAND = ":gc";

    public GarbageCollection(ConsoleLogger logger, Evaluator evaluator) {
        super(
						evaluator, logger, COMMAND + " - perform garbage collection", 
						equalTo(COMMAND), new StringsCompleter(COMMAND)
				);
    }

    void execute(String expression, CommandResultCollector result) {
        result.logInfo("Performing GC...");
				System.gc();
    }
}
