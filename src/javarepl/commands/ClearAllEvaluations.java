package javarepl.commands;

import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public class ClearAllEvaluations extends Command {
    private static final String COMMAND = ":clear";

    public ClearAllEvaluations() {
        super(COMMAND + " - clear all evaluations", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public Void call(Evaluator evaluator, String s) throws Exception {
        evaluator.clear();
        System.out.println("All variables has been cleared");
        System.out.println();
        return null;
    }
}
