package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Rules;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;

import java.io.PrintStream;

import static com.googlecode.totallylazy.Callables.toString;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.blank;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;
import static java.lang.System.exit;
import static java.lang.System.getProperty;
import static javarepl.Evaluation.classSource;

public class Main {
    public static ConsoleReader console;
    public static final String PROMPT = "java> ";
    public static final JavaREPL repl = new JavaREPL();

    public static void main(String[] args) throws Exception {
        console = new ConsoleReader(System.in, System.out);
        console.setHistoryEnabled(true);
        console.setPrompt(PROMPT);
        console.addCompleter(new AggregateCompleter(
                new ArgumentCompleter(new StringsCompleter(":test"), new StringsCompleter("loop", "array", "exp")),
                new StringsCompleter(":exit", ":help", ":src", ":clear", ":!")
        ));

        System.out.println(format("Welcome to JavaREPL (%s, Java %s)", getProperty("java.vm.name"), getProperty("java.version")));
        System.out.println("Type in expression to evaluate.");
        System.out.println("Type :help for more options or <tab> to auto-complete.");
        System.out.println("");


        Rules<String, Function1<String, Void>> rules = Rules.<String, Function1<String, Void>>rules()
                .addLast(equalTo(":exit"), exitApplication())
                .addLast(equalTo(":help"), showHelp())
                .addLast(equalTo(":src"), showLastSource())
                .addLast(equalTo(":clear"), clearContext())
                .addLast(startsWith(":test"), test())
                .addLast(equalTo(":!"), evaluateLatest())
                .addLast(not(blank()), evaluate())
                .addLast(always(), noAction());
        do {
            rules.apply(console.readLine().trim());
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

    private static Function1<String, Function1<String, Void>> showHelp() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                String help = new StringBuilder().append("Commands include: \n")
                        .append("    :help - display this help\n")
                        .append("    :src - display last compiled source\n")
                        .append("    :clear - clears all variables\n")
                        .append("    :test <loop | exp | array> - evaluates some exaples\n")
                        .append("    :! - evaluate the latest expression\n")
                        .append("    :exit - exits the app\n")
                        .toString();
                System.out.println(help);
                return null;
            }
        };
    }

    private static Function1<String, Function1<String, Void>> showLastSource() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                repl.lastEvaluation().map(classSource().then(printlnToOut()));
                return null;
            }


        };
    }

    private static Function1<String, Function1<String, Void>> clearContext() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                repl.clear();
                console.println("All variables has been cleared");
                console.println();
                return null;
            }


        };
    }

    private static Function1<String, Function1<String, Void>> evaluate() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String expression) throws Exception {
                evaluateExpression(expression);
                return null;
            }


        };
    }


    private static Function1<String, Function1<String, Void>> evaluateLatest() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String expression) throws Exception {
                Option<Evaluation> lastEvaluation = repl.lastEvaluation();
                if (!lastEvaluation.isEmpty()) {
                    String source = lastEvaluation.get().expression.source;
                    System.out.println(PROMPT + source);
                    evaluateExpression(source);
                }

                return null;
            }


        };
    }

    public static void evaluateExpression(String expr) {
        repl.evaluate(expr).map(printlnToErr(), printResult());
    }

    private static Function1<String, Function1<String, Void>> test() {
        return new Function1<String, Function1<String, Void>>() {

            public void evaluateExample(String example) {
                System.out.println("Evaluating example: " + example);
                evaluate().apply(example);
            }

            public Function1<String, Void> call(String line) throws Exception {
//                switch (sequence(line.split(" ")).second()) {
//                    case "loop":
//                        evaluateExample("for (int i = 0 ; i < 10 ; i++) {System.out.println(\"i = \" + i);}");
//                        break;
//                    case "exp":
//                        evaluateExample("throw new RuntimeException()");
//                        break;
//                    case "array":
//                        evaluateExample("new Integer[][]{{1,2,3}, {4,5,6}}");
//                        break;
//                }
                return null;
            }
        };
    }

    private static Function1<String, Function1<String, Void>> noAction() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                return null;
            }
        };
    }

    private static Function1<Evaluation, Void> printResult() {
        return new Function1<Evaluation, Void>() {
            public Void call(Evaluation result) throws Exception {
                result.result.map(toString.then(printlnToOut()));
                return null;
            }
        };
    }

    private static Function1<Object, Void> printlnToOut() {
        return printlnTo(System.out);
    }

    private static Function1<Object, Void> printlnToErr() {
        return printlnTo(System.err);
    }

    private static Function1<Object, Void> printlnTo(final PrintStream stream) {
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
