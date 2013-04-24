package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class QuitApplication extends Command {
    private static final String COMMAND = ":quit";
		private static final String ALT_COMMAND = ":exit";

    public QuitApplication(ConsoleLogger logger, Evaluator evaluator) {
        super(
					evaluator, logger, 
					COMMAND + ", " + ALT_COMMAND + " - quit application", 
					equalTo(COMMAND).or(equalTo(ALT_COMMAND)).or(equalTo(null)), 
					new StringsCompleter(COMMAND)
				);
    }

    void execute(String expression, CommandResultCollector result) {
        result.logInfo("Terminating...");
        evaluator().clearOutputDirectory();
        System.exit(0);
    }
}
