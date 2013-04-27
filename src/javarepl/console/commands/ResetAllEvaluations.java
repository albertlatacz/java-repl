package javarepl.console.commands;

import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class ResetAllEvaluations extends Command {
    private static final String COMMAND = ":reset";

    public ResetAllEvaluations(Evaluator evaluator) {
        super(evaluator, COMMAND + " - resets environment to initial state", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(String expression) {
        evaluator().reset();
        System.out.println("All variables has been cleared");
    }
}
