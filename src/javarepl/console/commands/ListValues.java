package javarepl.console.commands;

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
            listResults(console);
            listTypes(console);
            listImports(console);
            listMethods(console);
        }

        if (items.equals("results")) {
            listResults(console);
        }

        if (items.equals("types")) {
            listTypes(console);
        }

        if (items.equals("imports")) {
            listImports(console);
        }

        if (items.equals("methods")) {
            listMethods(console);
        }
    }

    private void listMethods(Console console) {
        console.logger().info(listValues("Methods", sequence(console.evaluator().expressionsOfType(Method.class)).map(signature())));
    }

    private void listImports(Console console) {
        console.logger().info(listValues("Imports", sequence(console.evaluator().expressionsOfType(Import.class)).map(typePackage())));
    }

    private void listTypes(Console console) {
        console.logger().info(listValues("Types", sequence(console.evaluator().expressionsOfType(Type.class)).map(type())));
    }

    private void listResults(Console console) {
        console.logger().info(listValues("Results", console.evaluator().results()));
    }
}
