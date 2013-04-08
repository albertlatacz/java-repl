package javarepl.commands;


import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Predicates;
import javarepl.Evaluation;
import javarepl.Evaluator;
import javarepl.ExpressionCompilationException;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.blank;
import static java.util.regex.Matcher.quoteReplacement;

public class EvaluateExpression extends Command {
    public EvaluateExpression() {
        super("", Predicates.<String>not(blank()), null);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        evaluate(evaluator, expression);
        return null;
    }

    public static void evaluate(Evaluator evaluator, String expression) {
        evaluator.evaluate(expression).map(printError(), printResult());
    }

    private static Function1<Evaluation, Void> printResult() {
        return new Function1<Evaluation, Void>() {
            public Void call(Evaluation result) throws Exception {
                System.out.println(result.result().map(asString()).getOrElse(""));
                return null;
            }
        };
    }

    private static Function1<Throwable, Void> printError() {
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
