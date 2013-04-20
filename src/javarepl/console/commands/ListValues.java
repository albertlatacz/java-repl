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

public class ListValues extends Command {
    private static final String COMMAND = ":list";

    public ListValues(ConsoleLogger logger) {
        super(COMMAND + " <results|types|methods|imports|all> - list specified values",
                startsWith(COMMAND), new ArgumentCompleter(new StringsCompleter(COMMAND), new StringsCompleter("results", "methods", "imports", "types", "all")), logger);
    }

    public CommandResult call(Evaluator evaluator, String expression) throws Exception {
        CommandResultCollector resultCollector = createResultCollector(expression);
        String items = expression.replace(COMMAND, "").trim();

        if (items.equals("all")) {
            listResults(evaluator, resultCollector);
            listTypes(evaluator, resultCollector);
            listImports(evaluator, resultCollector);
            listMethods(evaluator, resultCollector);
        }

        if (items.equals("results")) {
            listResults(evaluator, resultCollector);
        }

        if (items.equals("types")) {
            listTypes(evaluator, resultCollector);
        }

        if (items.equals("imports")) {
            listImports(evaluator, resultCollector);
        }

        if (items.equals("methods")) {
            listMethods(evaluator, resultCollector);
        }

        return resultCollector.result();
    }

    private void listMethods(Evaluator evaluator, CommandResultCollector resultCollector) {
        resultCollector.logInfo(listValues("Methods", sequence(evaluator.expressionsOfType(Method.class)).safeCast(WithKey.class).map(key())));
    }

    private void listImports(Evaluator evaluator, CommandResultCollector resultCollector) {
        resultCollector.logInfo(listValues("Imports", sequence(evaluator.expressionsOfType(Import.class)).map(source())));
    }

    private void listTypes(Evaluator evaluator, CommandResultCollector resultCollector) {
        resultCollector.logInfo(listValues("Types", sequence(evaluator.expressionsOfType(Type.class)).safeCast(WithKey.class).map(key())));
    }

    private void listResults(Evaluator evaluator, CommandResultCollector resultCollector) {
        resultCollector.logInfo(listValues("Results", evaluator.results()));
    }
}
