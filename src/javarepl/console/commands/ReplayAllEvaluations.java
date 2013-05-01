package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import javarepl.console.Console;
import javarepl.expressions.Expression;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.console.commands.EvaluateExpression.evaluate;

public final class ReplayAllEvaluations extends Command {
    private static final String COMMAND = ":replay";

    public ReplayAllEvaluations(Console console) {
        super(console, COMMAND + " - replay all evaluations", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(String line) {
        System.out.println("Replaying all evaluations:");
        Sequence<Expression> expressions = evaluator().expressions();
        evaluator().reset();

        for (Expression expression : expressions) {
            evaluate(evaluator(), expression.source());
        }
    }
}
