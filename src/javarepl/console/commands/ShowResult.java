package javarepl.console.commands;

import com.googlecode.totallylazy.Predicate;
import javarepl.Evaluator;
import javarepl.console.Console;

public final class ShowResult extends Command {
    public ShowResult() {
        super(null, null, null);
    }

    @Override
    public Predicate<String> predicate(Console console) {
        return containsResult(console.evaluator());
    }

    public void execute(Console console, String expression) {
        System.out.println(console.evaluator().result(expression).get().toString(true));
    }

    private static Predicate<String> containsResult(final Evaluator evaluator) {
        return new Predicate<String>() {
            public boolean matches(String expression) {
                return !evaluator.result(expression).isEmpty();
            }
        };
    }
}
