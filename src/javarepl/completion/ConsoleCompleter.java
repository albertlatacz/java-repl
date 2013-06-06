package javarepl.completion;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import javarepl.Result;
import javarepl.console.Console;
import javarepl.expressions.Import;
import javarepl.expressions.Method;
import javarepl.expressions.Type;

import static com.googlecode.totallylazy.Predicates.in;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Strings.*;
import static javarepl.completion.ResolvedClass.functions.className;
import static javarepl.completion.ResolvedPackage.functions.classes;
import static javarepl.completion.ResolvedPackage.functions.packageName;
import static javarepl.expressions.Expression.functions.key;
import static javarepl.expressions.Import.functions.typePackage;
import static javarepl.expressions.Method.functions.methodName;

public class ConsoleCompleter extends Completer {
    private final Console console;
    private final TypeResolver typeResolver;

    public ConsoleCompleter(Console console, TypeResolver typeResolver) {
        this.console = console;
        this.typeResolver = typeResolver;
    }

    public CompletionResult call(String expression) throws Exception {
        int lastSpace = expression.lastIndexOf(" ") + 1;
        Evaluator evaluator = console.context().get(Evaluator.class);
        Sequence<String> candidates = results(evaluator)
                .join(methods(evaluator))
                .join(types(evaluator))
                .join(imports(evaluator))
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

    private Sequence<String> imports(Evaluator evaluator) {
        Sequence<String> importedPackages = evaluator
                .expressionsOfType(Import.class)
                .map(typePackage().then(replace(".*", "")).then(replace(" ", "")));

        return typeResolver.packages()
                .filter(where(packageName(), in(importedPackages)))
                .flatMap(classes())
                .map(className())
                .unique();
    }
}
