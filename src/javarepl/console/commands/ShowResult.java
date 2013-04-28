package javarepl.console.commands;

import com.googlecode.totallylazy.Predicate;
import javarepl.Evaluator;
import javarepl.console.Console;

public final class ShowResult extends Command {
    public ShowResult(Console console) {
        super(console, null, containsResult(console.evaluator()), null);
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
