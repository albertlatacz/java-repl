package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Sequence;
import javarepl.client.EvaluationLog;
import javarepl.client.EvaluationResult;
import javarepl.client.JavaREPLClient;
import jline.console.ConsoleReader;

import java.io.IOException;

import static com.googlecode.totallylazy.Callables.compose;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.replaceAll;
import static com.googlecode.totallylazy.Strings.startsWith;
import static com.googlecode.totallylazy.numbers.Numbers.intValue;
import static com.googlecode.totallylazy.numbers.Numbers.valueOf;

public class RemoteMain {
    public static void main(String... args) throws Exception {
        JavaREPLClient client = new JavaREPLClient(hostname(args), port(args));

        ExpressionReader expressionReader = new ExpressionReader(readFromExtendedConsole());

        String expression = null;
        do {
            expression = expressionReader.readExpression().getOrNull();

            if (expression != null)
                printResult(client.execute(expression));

        } while (expression != null);
    }

    private static Integer port(String[] args) {
        return sequence(args).find(startsWith("--port=")).map(compose(replaceAll("--port=", ""), compose(valueOf, intValue))).getOrElse(8001);
    }

    private static String hostname(String[] args) {
        return sequence(args).find(startsWith("--hostname=")).map(replaceAll("--hostname=", "")).getOrElse("localhost");
    }

    private static void printResult(EvaluationResult result) {
        for (EvaluationLog log : result.logs()) {
            printEvaluationLog(log);
        }
    }

    public static final void printEvaluationLog(EvaluationLog log) {
        switch (log.type()) {
            case INFO:
                System.out.println("\u001B[0m" + log.message() + "\u001B[0m");
                break;
            case SUCCESS:
                System.out.println("\u001B[32m" + log.message() + "\u001B[0m");
                break;
            case ERROR:
                System.err.println("\u001B[31m" + log.message() + "\u001B[0m");
                break;
        }
    }

    private static Function1<Sequence<String>, String> readFromExtendedConsole() throws IOException {
        return new Function1<Sequence<String>, String>() {
            private final ConsoleReader consoleReader;

            {
                consoleReader = new ConsoleReader(System.in, System.out);
                consoleReader.setHistoryEnabled(true);
                consoleReader.setExpandEvents(false);
            }

            public String call(Sequence<String> lines) throws Exception {
                consoleReader.setPrompt(lines.isEmpty() ? "\u001B[1mjava> \u001B[0m" : "    \u001B[1m| \u001B[0m");
                return consoleReader.readLine();
            }

        };
    }

}
