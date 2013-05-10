package javarepl.completion;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import javarepl.Result;
import javarepl.console.Console;
import javarepl.expressions.Method;
import javarepl.expressions.Type;

import static com.googlecode.totallylazy.Strings.format;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.expressions.Expression.functions.key;
import static javarepl.expressions.Method.functions.methodName;

public class SimpleConsoleCompleter extends CodeCompleter {
    public final Console console;

    public SimpleConsoleCompleter(Console console) {
        this.console = console;
    }

    public CompletionResult call(String expression) throws Exception {
        int lastSpace = expression.lastIndexOf(" ") + 1;
        Evaluator evaluator = console.context().get(Evaluator.class);
        Sequence<String> candidates = results(evaluator)
                .join(methods(evaluator))
                .join(types(evaluator))
                .filter(startsWith(expression.substring(lastSpace)));

        return new CompletionResult(expression, lastSpace, candidates);
    }

    private Sequence<String> results(Evaluator evaluator) {
        return evaluator.results().map(Result.functions.key());
    }

    private Sequence<String> methods(Evaluator evaluator) {
        return evaluator.expressionsOfType(Method.class).map(methodName()).unique().map(format("%s("));
    }

    private Sequence<String> types(Evaluator evaluator) {
        return evaluator.expressionsOfType(Type.class).map(key());
    }

}
