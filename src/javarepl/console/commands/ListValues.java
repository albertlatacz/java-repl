package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.Console;
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

    public ListValues() {
        super(COMMAND + " <results|types|methods|imports|all> - list specified values",
                startsWith(COMMAND), new ArgumentCompleter(new StringsCompleter(COMMAND), new StringsCompleter("results", "methods", "imports", "types", "all")));
    }

    public void execute(Console console, String expression) {
        String items = expression.replace(COMMAND, "").trim();

        if (items.equals("all")) {
            listResults(console.evaluator());
            listTypes(console.evaluator());
            listImports(console.evaluator());
            listMethods(console.evaluator());
        }

        if (items.equals("results")) {
            listResults(console.evaluator());
        }

        if (items.equals("types")) {
            listTypes(console.evaluator());
        }

        if (items.equals("imports")) {
            listImports(console.evaluator());
        }

        if (items.equals("methods")) {
            listMethods(console.evaluator());
        }
    }

    private void listMethods(Evaluator evaluator) {
        System.out.println(listValues("Methods", sequence(evaluator.expressionsOfType(Method.class)).map(signature())));
    }

    private void listImports(Evaluator evaluator) {
        System.out.println(listValues("Imports", sequence(evaluator.expressionsOfType(Import.class)).map(typePackage())));
    }

    private void listTypes(Evaluator evaluator) {
        System.out.println(listValues("Types", sequence(evaluator.expressionsOfType(Type.class)).map(type())));
    }

    private void listResults(Evaluator evaluator) {
        System.out.println(listValues("Results", evaluator.results()));
    }
}
