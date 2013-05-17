package javarepl.console.commands;

import com.googlecode.totallylazy.Predicate;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;

public final class ShowResult extends Command {
    private final Evaluator evaluator;
    private final ConsoleLogger logger;

    public ShowResult(Evaluator evaluator, ConsoleLogger logger) {
        super(null, containsResult(evaluator), null);
        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        logger.success(evaluator.result(expression).get().toString(true));
    }

    private static Predicate<String> containsResult(final Evaluator evaluator) {
        return new Predicate<String>() {
            public boolean matches(String expression) {
                return !evaluator.result(expression).isEmpty();
            }
        };
    }
}
