package javarepl;

import com.googlecode.totallylazy.*;
import com.googlecode.totallylazy.numbers.Numbers;
import javarepl.expressions.Import;
import javarepl.expressions.Method;
import javarepl.expressions.Type;
import javarepl.expressions.WithKey;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Pair.functions.values;
import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.*;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.System.exit;
import static java.lang.System.getProperty;
import static java.util.regex.Matcher.quoteReplacement;
import static javarepl.Evaluation.functions.classSource;
import static javarepl.Evaluation.functions.expression;
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
                .addLast(startsWith(":history"), showHistory())
                .addLast(equalTo(":clear"), clearAll())
                .addLast(equalTo(":replay"), replayAll())
                .addLast(startsWith(":cp "), addClasspath())
                .addLast(startsWith(":list "), list())
                .addLast(startsWith(":h!"), evaluateFromHistory())
                .addLast(startsWith(":h?"), searchHistory())
                .addLast(not(blank()), evaluate())
                .addLast(always(), noAction());

        do {
            rules.apply(expressionReader.readExpression().getOrNull());
            System.out.println();
        } while (true);
    }

    private Function1<String, Function1<String, Void>> showHelp() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                String help = new StringBuilder().append("Available commands: \n")
                        .append("    :help - display this help\n")
                        .append("    :cp <path> - includes given file or directory in the classpath\n")
                        .append("    :history [num] - shows the history (optional 'num' is number of evaluations to show)\n")
                        .append("    :h! [num] - evaluate last expression (optional 'num' to evaluate expression from history)\n")
                        .append("    :h? <search_string> - searches the history\n")
                        .append("    :list <results|types|methods|imports|all> - list specified values\n")
                        .append("    :src - display last compiled source\n")
                        .append("    :clear - clear all variables\n")
                        .append("    :replay - replay all evaluations\n")
                        .append("    :quit - exits the app\n")
                        .toString();
                System.out.println(help);
                return null;
            }
        };
    }

    private Function1<String, Function1<String, Void>> quitApplication() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                exit(0);
                return null;
            }
        };
    }

    private Function1<String, Function1<String, Void>> showHistory() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                Sequence<String> splitLine = sequence(line.split(" "));
                Integer limit = (splitLine.size() == 2) ? parseInt(splitLine.second()) : evaluator.evaluations().size();
                listValues("History", history().reverse().take(limit).reverse());
                return null;
            }
        };
    }

    private Function1<String, Function1<String, Void>> searchHistory() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                String searchStr = sequence(line.split(" ")).tail().toString(" ");
                listValues("History search for '" + searchStr + "'", history().filter(contains(searchStr)));
                return null;
            }
        };
    }

    private Sequence<String> history() {
        return Numbers.range(1)
                .zip(evaluator.evaluations().map(expression().then(source()).then(replace("\n", "\n   "))))
                .map(values().then(Sequences.toString("  ")));
    }

    private Function1<String, Function1<String, Void>> showLastSource() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                System.out.println(evaluator.lastEvaluation().map(classSource()).getOrElse("No source"));
                return null;
            }
        };
    }

    private Function1<String, Function1<String, Void>> clearAll() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                evaluator.clear();
                System.out.println("All variables has been cleared");
                System.out.println();
                return null;
            }


        };
    }

    private Function1<String, Function1<String, Void>> replayAll() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                System.out.println("Replaying all evaluations:");
                Sequence<Evaluation> evaluations = evaluator.evaluations();
                evaluator.clear();
                evaluations.forEach(new Function1<Evaluation, Void>() {
                    public Void call(Evaluation evaluation) throws Exception {
                        evaluateExpression(evaluation.expression().source());
                        return null;
                    }
                });

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

    private Function1<String, Function1<String, Void>> evaluateFromHistory() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                Sequence<String> splitLine = sequence(line.split(" "));
                Integer historyItem = (splitLine.size() == 2) ? parseInt(splitLine.second()) : evaluator.evaluations().size();
                Option<Evaluation> lastEvaluation = evaluator.evaluations().drop(historyItem - 1).headOption();
                if (!lastEvaluation.isEmpty()) {
                    String source = lastEvaluation.get().expression().source();
                    System.out.println(source);
                    evaluateExpression(source);
                } else {
                    System.err.println("Expression not found.\n");
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
                return null;
            }

            private void listMethods() {
                listValues("Methods", sequence(evaluator.expressionsOfType(Method.class)).safeCast(WithKey.class).map(key()));
            }

            private void listImports() {
                listValues("Imports", sequence(evaluator.expressionsOfType(Import.class)).map(source()));
            }

            private void listTypes() {
                listValues("Types", sequence(evaluator.expressionsOfType(Type.class)).safeCast(WithKey.class).map(key()));
            }

            private void listResults() {
                listValues("Results", evaluator.results());
            }
        };
    }

    private void listValues(String name, Iterable<?> list) {
        System.out.println(format(name + ":\n    %s\n", sequence(list).toString("\n").replaceAll("\n", "\n    ")));
    }

    public void evaluateExpression(String expr) {
        evaluator.evaluate(expr).map(printError(), printResult());
    }

    private Function1<String, Function1<String, Void>> noAction() {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                if (line == null)
                    System.exit(0);
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
                        new StringsCompleter(":exit", ":help", ":include", ":src", ":clear", ":replay", ":history", ":h!", ":h?"),
                        new ArgumentCompleter(new StringsCompleter(":list"), new StringsCompleter("results", "methods", "imports", "types", "all"))
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
                System.out.println(result.result().map(asString()).getOrElse(""));
                return null;
            }
        };
    }

    private Function1<Throwable, Void> printError() {
        return new Function1<Throwable, Void>() {
            public Void call(Throwable error) throws Exception {
                if (error instanceof ExpressionCompilationException) {
                    String firstLocation = sequence(error.toString().split(":")).take(3).toString(":").trim() + ": ";
                    String otherLocations = sequence(error.toString().split(":")).drop(1).take(2).toString(":").trim() + ": ";
                    String message = error.toString()
                            .replaceAll(quoteReplacement(firstLocation), "ERROR: ")
                            .replaceAll(quoteReplacement(otherLocations), "ERROR: ")
                            .replaceAll("\n", "\n  ")
                            .replaceAll("  ERROR", "ERROR");
                    System.err.println(message);
                } else {
                     error.printStackTrace(System.err);
                }
                return null;
            }
        };
    }
}
