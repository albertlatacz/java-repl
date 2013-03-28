package javarepl;

import com.googlecode.totallylazy.*;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.StringsCompleter;

import java.io.*;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import static com.googlecode.totallylazy.Callables.toString;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.blank;
import static java.lang.String.format;
import static java.lang.System.exit;
import static java.lang.System.getProperty;
import static java.net.URLDecoder.decode;
import static javarepl.Evaluation.classSource;

public class Main {


    private Evaluator evaluator = new Evaluator();
    private final ExpressionReader expressionReader;

    public static void main(String[] args) throws Exception {
        Sequence<String> arguments = sequence(args);
        boolean simpleConsole = arguments.contains("-sc");

        new Main(simpleConsole).run();
    }

    public Main(boolean simpleConsole) throws Exception {
        System.out.println(format("Welcome to JavaREPL version %s (%s, Java %s)", extractVersion(), getProperty("java.vm.name"), getProperty("java.version")));
        System.out.println("Type in expression to evaluate.");
        System.out.println("Type :help for more options.");
        System.out.println("");

        expressionReader = new ExpressionReader(simpleConsole ? readFromSimpleConsole() : readFromExtendedConsole());
    }

    public void run() throws IOException {
        Rules<String, Function1<String, Void>> rules = Rules.<String, Function1<String, Void>>rules()
                .addLast(equalTo(":exit"), exitApplication())
                .addLast(equalTo(":help"), showHelp())
                .addLast(equalTo(":src"), showLastSource())
                .addLast(equalTo(":clear"), clearContext())
                .addLast(equalTo(":!"), evaluateLatest())
                .addLast(not(blank()), evaluate())
                .addLast(always(), noAction());

        do {
            Expression expression = expressionReader.readExpression();
            rules.apply(expression.source);
            System.out.println();
        } while (true);
    }

    private static Function1<String, Function1<String, Void>> exitApplication() {
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
                String help = new StringBuilder().append("Commands include: \n")
                        .append("    :help - display this help\n")
                        .append("    :src - display last compiled source\n")
                        .append("    :clear - clears all variables\n")
                        .append("    :! - evaluate the latest expression\n")
                        .append("    :exit - exits the app\n")
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
                    String source = lastEvaluation.get().expression.source;
                    System.out.println(source);
                    evaluateExpression(source);
                }

                return null;
            }
        };
    }

    public void evaluateExpression(String expr) {
        evaluator.evaluate(expr).map(printlnToErr(), printResult());
    }

    private Function1<String, Function1<String, Void>> noAction() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
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
                console.addCompleter(new AggregateCompleter(new StringsCompleter(":exit", ":help", ":src", ":clear", ":!")));
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
                result.result.map(toString.then(printlnToOut()));
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

    private String extractVersion() throws Exception {
        File path = new File(decode(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "ISO-8859-1"));

        if (!path.isDirectory()) {
            try {
                JarInputStream jarStream = new JarInputStream(new FileInputStream(path));
                Manifest manifest = jarStream.getManifest();
                return manifest.getMainAttributes().getValue("Implementation-Version");
            } catch (Exception e) {
                // ignore
            }
        }

        return "[unknown]";
    }
}
