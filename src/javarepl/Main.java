package javarepl;

import com.googlecode.totallylazy.*;
import javarepl.expressions.*;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static com.googlecode.totallylazy.Callables.toString;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.blank;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;
import static java.lang.System.exit;
import static java.lang.System.getProperty;
import static javarepl.Evaluation.functions.classSource;
import static javarepl.Utils.applicationVersion;
import static javarepl.Utils.resolveClasspath;
import static javarepl.expressions.Expression.functions.source;
import static javarepl.expressions.WithKey.functions.key;

public class Main {
    public static void main(String[] args) throws Exception {
        Sequence<String> arguments = sequence(args);
        boolean simpleConsole = arguments.contains("-sc");

        new Main(simpleConsole).run();
    }

    private final Evaluator evaluator;
    private final ExpressionReader expressionReader;

    public Main(boolean simpleConsole) throws Exception {
        System.out.println(format("Welcome to JavaREPL version %s (%s, Java %s)", applicationVersion(), getProperty("java.vm.name"), getProperty("java.version")));

        evaluator = new Evaluator();
        expressionReader = new ExpressionReader(simpleConsole ? readFromSimpleConsole() : readFromExtendedConsole());

        System.out.println("Type in expression to evaluate.");
        System.out.println("Type :help for more options.");
        System.out.println("");
    }

    public void run() throws IOException {
        Rules<String, Function1<String, Void>> rules = Rules.<String, Function1<String, Void>>rules()
                .addLast(equalTo(":quit").or(equalTo(null)), quitApplication())
                .addLast(equalTo(":help"), showHelp())
                .addLast(equalTo(":src"), showLastSource())
                .addLast(equalTo(":clear"), clearContext())
                .addLast(startsWith(":cp "), addClasspath())
                .addLast(startsWith(":list "), list())
                .addLast(equalTo(":!"), evaluateLatest())
                .addLast(not(blank()), evaluate())
                .addLast(always(), noAction());

        do {
            rules.apply(expressionReader.readExpression());
            System.out.println();
        } while (true);
    }

    private static Function1<String, Function1<String, Void>> quitApplication() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                exit(0);
                return null;
            }
        };
    }

    private Function1<String, Function1<String, Void>> showHelp() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                String help = new StringBuilder().append("Available commands: \n")
                        .append("    :help - display this help\n")
                        .append("    :cp <path> - includes given file or directory in the classpath\n")
                        .append("    :list <results|types|methods|imports> - list specified values\n")
                        .append("    :src - display last compiled source\n")
                        .append("    :clear - clears all variables\n")
                        .append("    :! - evaluate the latest expression\n")
                        .append("    :quit - exits the app\n")
                        .toString();
                System.out.println(help);
                return null;
            }
        };
    }

    private Function1<String, Function1<String, Void>> showLastSource() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                evaluator.lastEvaluation().map(classSource().then(printlnToOut()));
                return null;
            }


        };
    }

    private Function1<String, Function1<String, Void>> clearContext() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                evaluator.clear();
                System.out.println("All variables has been cleared");
                System.out.println();
                return null;
            }


        };
    }

    private Function1<String, Function1<String, Void>> evaluate() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String expression) throws Exception {
                evaluateExpression(expression);
                return null;
            }


        };
    }

    private Function1<String, Function1<String, Void>> evaluateLatest() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String expression) throws Exception {
                Option<Evaluation> lastEvaluation = evaluator.lastEvaluation();
                if (!lastEvaluation.isEmpty()) {
                    String source = lastEvaluation.get().expression().source();
                    System.out.println(source);
                    evaluateExpression(source);
                }

                return null;
            }
        };
    }

    private Function1<String, Function1<String, Void>> addClasspath() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                String path = line.replace(":cp ", "");
                try {
                    evaluator.addClasspathUrl(resolveClasspath(path));
                    System.out.println(format("Added %s to classpath.", path));
                } catch (Exception e) {
                    System.err.println(format("Could not add %s to classpath. %s", path, e.getLocalizedMessage()));
                }

                return null;
            }
        };
    }

    private Function1<String, Function1<String, Void>> list() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                String items = line.replace(":list ", "");

                if (items.equals("results")) {
                    listValues("Results", evaluator.results());
                }

                if (items.equals("types")) {
                    listValues("Types", sequence(evaluator.expressionsOfType(Type.class)).safeCast(WithKey.class).map(key()));
                }

                if (items.equals("imports")) {
                    listValues("Imports", sequence(evaluator.expressionsOfType(Import.class)).map(source()));
                }

                if (items.equals("methods")) {
                    listValues("Methods", sequence(evaluator.expressionsOfType(Method.class)).safeCast(WithKey.class).map(key()));
                }
                return null;
            }

            private void listValues(String name, Iterable<?> list) {
                System.out.println(format(name + ":\n%s", sequence(list).toString("\n")));
            }
        };
    }

    public void evaluateExpression(String expr) {
        evaluator.evaluate(expr).map(printlnToErr(), printResult());
    }

    private Function1<String, Function1<String, Void>> noAction() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {

                System.out.println("line = " + line + " " + line.isEmpty() );
                return null;
            }
        };
    }


    private Function1<Sequence<String>, String> readFromExtendedConsole() throws IOException {
        return new Function1<Sequence<String>, String>() {
            private final ConsoleReader console;

            {
                console = new ConsoleReader(System.in, System.out);
                console.setHistoryEnabled(true);
                console.addCompleter(new AggregateCompleter(
                        new StringsCompleter(":exit", ":help", ":include", ":src", ":clear", ":!"),
                        new ArgumentCompleter(new StringsCompleter(":list"), new StringsCompleter("results", "methods", "imports", "types"))
                ));
            }

            public String call(Sequence<String> lines) throws Exception {
                console.setPrompt(lines.isEmpty() ? "java> " : "    | ");
                return console.readLine();
            }
        };
    }

    private Function1<Sequence<String>, String> readFromSimpleConsole() {
        return new Function1<Sequence<String>, String>() {
            private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            public String call(Sequence<String> lines) throws Exception {
                return reader.readLine();
            }
        };
    }


    private Function1<Evaluation, Void> printResult() {
        return new Function1<Evaluation, Void>() {
            public Void call(Evaluation result) throws Exception {
                result.result().map(toString.then(printlnToOut()));
                return null;
            }
        };
    }

    private Function1<Object, Void> printlnToOut() {
        return printlnTo(System.out);
    }

    private Function1<Object, Void> printlnToErr() {
        return printlnTo(System.err);
    }

    private Function1<Object, Void> printlnTo(final PrintStream stream) {
        return new Function1<Object, Void>() {
            public Void call(Object toPrint) throws Exception {
                if (toPrint instanceof Throwable)
                    ((Throwable) toPrint).printStackTrace(stream);
                else
                    stream.println(toPrint);
                return null;
            }
        };
    }


}
