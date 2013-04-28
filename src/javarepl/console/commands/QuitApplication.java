package javarepl.console.commands;

import javarepl.console.Console;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class QuitApplication extends Command {
    private static final String COMMAND = ":quit";

    public QuitApplication(Console console) {
        super(console, COMMAND + " - quit application", equalTo(COMMAND).or(equalTo(null)), new StringsCompleter(COMMAND));
    }

    public void execute(String expression) {
        System.out.println("Terminating...");
        evaluator().clearOutputDirectory();
        System.exit(0);
    }
}
