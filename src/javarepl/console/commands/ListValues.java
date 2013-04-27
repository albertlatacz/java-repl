package javarepl.console.commands;

import javarepl.Evaluator;
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

    public ListValues(Evaluator evaluator) {
        super(evaluator, COMMAND + " <results|types|methods|imports|all> - list specified values",
                startsWith(COMMAND), new ArgumentCompleter(new StringsCompleter(COMMAND), new StringsCompleter("results", "methods", "imports", "types", "all")));
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
        System.out.println(listValues("Methods", sequence(evaluator().expressionsOfType(Method.class)).safeCast(WithKey.class).map(key())));
    }

    private void listImports() {
        System.out.println(listValues("Imports", sequence(evaluator().expressionsOfType(Import.class)).map(source())));
    }

    private void listTypes() {
        System.out.println(listValues("Types", sequence(evaluator().expressionsOfType(Type.class)).safeCast(WithKey.class).map(key())));
    }

    private void listResults() {
        System.out.println(listValues("Results", evaluator().results()));
    }
}
