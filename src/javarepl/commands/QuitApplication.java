package javarepl.commands;

import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public class QuitApplication extends Command {
    private static final String COMMAND = ":quit";

    public QuitApplication() {
        super(COMMAND + " - quit application", equalTo(COMMAND).or(equalTo(null)), new StringsCompleter(COMMAND));
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        System.out.println("Terminating...");
        evaluator.clearOutputDirectory();
        System.exit(0);
        return null;
    }
}
