package javarepl.commands;

import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public class ResetAllEvaluations extends Command {
    private static final String COMMAND = ":reset";

    public ResetAllEvaluations() {
        super(COMMAND + " - resets environment to initial state", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public Void call(Evaluator evaluator, String s) throws Exception {
        evaluator.reset();
        System.out.println("All variables has been cleared");
        System.out.println();
        return null;
    }
}
