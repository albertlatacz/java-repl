import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Rules;
import repler.java.Expression;
import repler.java.REPL;
import repler.java.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Predicates.always;
import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.not;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.blank;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.System.exit;

public class Repler {

    public static void main(String[] args) throws Exception {
        System.out.println("    ____                    __              ");
        System.out.println("   / __ \\  ___     ____    / /  ___    _____");
        System.out.println("  / /_/ / / _ \\   / __ \\  / /  / _ \\  / ___/");
        System.out.println(" / _, _/ /  __/  / /_/ / / /  /  __/ / /    ");
        System.out.println("/_/ |_|  \\___/  / ,___/ /_/   \\___/ /_/     ");
        System.out.println("               / /                          ");
        System.out.println("              /_/    Read-Eval-Print-Loop for Java");
        System.out.println();
        System.out.println("Type expression to start or :help for more options.");

        REPL repl = new REPL();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        Rules<String, Function1<String, Void>> rules = Rules.<String, Function1<String, Void>>rules()
                .addLast(equalTo(":exit"), exitApplication())
                .addLast(equalTo(":help"), showHelp())
                .addLast(equalTo(":src"), showLastSource(repl))
                .addLast(startsWith(":test"), test(repl))
                .addLast(not(blank()), evaluate(repl))
                .addLast(always(), noAction());
        do {
            System.out.print(">> ");
            rules.apply(console.readLine());
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
                        .append("    :test <loop | exp | array> - evaluates some exaples\n")
                        .append("    :exit - exits the app\n")
                        .toString();
                System.out.println(help);
                return null;
            }
        };
    }

    private static Function1<String, Function1<String, Void>> showLastSource(final REPL repl) {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                Option<Pair<Expression,Result>> lastEvaluation = repl.context().getEvaluations().headOption();
                if (lastEvaluation.equals(none())) {
                    System.out.println("No source");
                } else {
                    System.out.println(lastEvaluation.get().first().getClassSource());
                }

                return null;
            }
        };
    }

    private static Function1<String, Function1<String, Void>> evaluate(final REPL repl) {
        return new Function1<String, Function1<String, Void>>() {
            public Function1<String, Void> call(String line) throws Exception {
                repl.evaluate(line);
                return null;
            }
        };
    }

    private static Function1<String, Function1<String, Void>> test(final REPL repl) {
        return new Function1<String, Function1<String, Void>>() {

            public void evaluateExample(String example) {
                System.out.println("Evaluating example: " + example);
                repl.evaluate(example);
            }

            public Function1<String, Void> call(String line) throws Exception {
                switch (sequence(line.split(" ")).second()) {
                    case "loop": evaluateExample("for (int i = 0 ; i < 10 ; i++) {System.out.println(\"i = \" + i);}");break;
                    case "exp": evaluateExample("throw new RuntimeException()");break;
                    case "array": evaluateExample("new Integer[][]{{1,2,3}, {4,5,6}}");break;
                }
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
}
