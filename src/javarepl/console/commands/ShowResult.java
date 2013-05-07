package javarepl.console.commands;

import com.googlecode.totallylazy.Predicate;
import javarepl.Evaluator;
import javarepl.console.Console;
import javarepl.console.ConsoleLogger;

public final class ShowResult extends Command {
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ShowResult(Evaluator evaluator, ConsoleLogger logger) {
        super(null, null, null);
        this.evaluator = evaluator;
        this.logger = logger;
    }

    @Override
    public Predicate<String> predicate(Console console) {
        return containsResult(console.evaluator());
    }

    public void execute(String expression) {
        logger.info(evaluator.result(expression).get().toString(true));
    }

    private static Predicate<String> containsResult(final Evaluator evaluator) {
        return new Predicate<String>() {
            public boolean matches(String expression) {
                return !evaluator.result(expression).isEmpty();
            }
        };
    }
}
