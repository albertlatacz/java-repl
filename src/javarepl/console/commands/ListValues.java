package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import javarepl.expressions.Import;
import javarepl.expressions.Method;
import javarepl.expressions.Type;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;
import static javarepl.expressions.Import.functions.typePackage;
import static javarepl.expressions.Method.functions.signature;
import static javarepl.expressions.Type.functions.type;

public final class ListValues extends Command {
    private static final String COMMAND = ":list";
    private final Evaluator evaluator;
    private final ConsoleLogger logger;


    public ListValues(Evaluator evaluator, ConsoleLogger logger) {
        super(COMMAND + " <results|types|methods|imports|all> - list specified values",
                startsWith(COMMAND), new ArgumentCompleter(new StringsCompleter(COMMAND), new StringsCompleter("results", "methods", "imports", "types", "all")));

        this.evaluator = evaluator;
        this.logger = logger;
    }

    public void execute(String expression) {
        String items = expression.replace(COMMAND, "").trim();

        if (items.equals("all")) {
            listResults();
            listTypes();
            listImports();
            listMethods();
        }

        if (items.equals("results")) {
            listResults();
        }

        if (items.equals("types")) {
            listTypes();
        }

        if (items.equals("imports")) {
            listImports();
        }

        if (items.equals("methods")) {
            listMethods();
        }
    }

    private void listMethods() {
        logger.success(listValues("Methods", sequence(evaluator.expressionsOfType(Method.class)).map(signature())));
    }

    private void listImports() {
        logger.success(listValues("Imports", sequence(evaluator.expressionsOfType(Import.class)).map(typePackage())));
    }

    private void listTypes() {
        logger.success(listValues("Types", sequence(evaluator.expressionsOfType(Type.class)).map(type())));
    }

    private void listResults() {
        logger.success(listValues("Results", evaluator.results()));
    }
}
