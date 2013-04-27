package javarepl.console.commands;

import com.googlecode.totallylazy.Predicate;
import javarepl.Evaluator;

public final class ShowResult extends Command {
    public ShowResult(Evaluator evaluator) {
        super(evaluator, null, containsResult(evaluator), null);
    }

    public void execute(String expression) {
        System.out.println(evaluator().result(expression).get().toString(true));
    }

    private static Predicate<String> containsResult(final Evaluator evaluator) {
        return new Predicate<String>() {
            public boolean matches(String expression) {
                return !evaluator.result(expression).isEmpty();
            }
        };
    }
}
