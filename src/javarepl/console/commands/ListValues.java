package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import javarepl.expressions.Import;
import javarepl.expressions.Method;
import javarepl.expressions.Type;
import javarepl.expressions.WithKey;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;
import static javarepl.expressions.Expression.functions.source;
import static javarepl.expressions.WithKey.functions.key;

public final class ListValues extends Command {
    private static final String COMMAND = ":list";

    public ListValues(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " <results|types|methods|imports|all> - list specified values",
                startsWith(COMMAND), new ArgumentCompleter(new StringsCompleter(COMMAND), new StringsCompleter("results", "methods", "imports", "types", "all")));
    }

    void execute(String expression, CommandResultCollector resultCollector) {
        String items = expression.replace(COMMAND, "").trim();

        if (items.equals("all")) {
            listResults(resultCollector);
            listTypes(resultCollector);
            listImports(resultCollector);
            listMethods(resultCollector);
        }

        if (items.equals("results")) {
            listResults(resultCollector);
        }

        if (items.equals("types")) {
            listTypes(resultCollector);
        }

        if (items.equals("imports")) {
            listImports(resultCollector);
        }

        if (items.equals("methods")) {
            listMethods(resultCollector);
        }
    }

    private void listMethods(CommandResultCollector resultCollector) {
        resultCollector.logInfo(listValues("Methods", sequence(evaluator().expressionsOfType(Method.class)).safeCast(WithKey.class).map(key())));
    }

    private void listImports(CommandResultCollector resultCollector) {
        resultCollector.logInfo(listValues("Imports", sequence(evaluator().expressionsOfType(Import.class)).map(source())));
    }

    private void listTypes(CommandResultCollector resultCollector) {
        resultCollector.logInfo(listValues("Types", sequence(evaluator().expressionsOfType(Type.class)).safeCast(WithKey.class).map(key())));
    }

    private void listResults(CommandResultCollector resultCollector) {
        resultCollector.logInfo(listValues("Results", evaluator().results()));
    }
}
