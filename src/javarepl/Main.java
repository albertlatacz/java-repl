package javarepl;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import javarepl.client.EvaluationLog;
import javarepl.client.EvaluationResult;
import javarepl.client.JavaREPLClient;
import javarepl.completion.CompletionResult;
import jline.console.ConsoleReader;
import jline.console.history.MemoryHistory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static com.googlecode.totallylazy.Callables.compose;
import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.replaceAll;
import static com.googlecode.totallylazy.Strings.startsWith;
import static com.googlecode.totallylazy.numbers.Numbers.intValue;
import static com.googlecode.totallylazy.numbers.Numbers.valueOf;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static javarepl.Utils.applicationVersion;
import static javarepl.Utils.randomServerPort;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class Main {
    public static final InputStream inStream = System.in;
    public static final PrintStream outStream = System.out;
    public static final PrintStream errStream = System.err;

    private static Option<Process> process = none();
    private static JavaREPLClient client;

    public static void main(String... args) throws Exception {
        client = createClient(args);
        ExpressionReader expressionReader = new ExpressionReader(jlineConsole(client));

        String expression = null;
        Option<EvaluationResult> result = none();
        do {
            expression = expressionReader.readExpression().getOrNull();

            if (expression != null) {
                result = client.execute(expression);
                if (!result.isEmpty())
                    printResult(result.get());
            }


        } while (expression != null && !result.isEmpty());
    }

    private static JavaREPLClient createClient(String[] args) throws Exception {
        Option<String> hostname = hostname(args);
        Option<Integer> port = port(args);

        outStream.println(format("Welcome to JavaREPL version %s (%s, Java %s)",
                applicationVersion(),
                getProperty("java.vm.name"),
                getProperty("java.version")));

        if (hostname.isEmpty() && port.isEmpty()) {
            return startNewLocalInstance("localhost", randomServerPort());
        } else {
            return connectToRemoteInstance(hostname.getOrElse("localhost"), port.getOrElse(randomServerPort()));
        }
    }

    private static JavaREPLClient connectToRemoteInstance(String hostname, Integer port) {
        JavaREPLClient replClient = new JavaREPLClient(hostname, port);

        if (!replClient.isAlive()) {
            errStream.println("ERROR: Could not connect to remote REPL instance at http://" + hostname + ":" + port);
            System.exit(0);
        } else {
            outStream.println("Connected to remote instance at http://" + hostname + ":" + port);
        }

        String remoteInstanceVersion = replClient.version();
        if (!remoteInstanceVersion.equals(applicationVersion())) {
            errStream.println("WARNING: Client version (" + applicationVersion() + ") is different from remote instance version (" + remoteInstanceVersion + ")");
        }

        return replClient;
    }

    private static JavaREPLClient startNewLocalInstance(String hostname, Integer port) throws Exception {
        if (getSystemJavaCompiler() == null) {
            errStream.println("\nERROR: Java compiler not found.\n" +
                    "This can occur when JavaREPL was run with JRE instead of JDK or JDK is not configured correctly.");
            System.exit(0);
        }

        outStream.println("Type expression to evaluate, \u001B[32m:help\u001B[0m for more options or press \u001B[32mtab\u001B[0m to auto-complete.");

        ProcessBuilder builder = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), Repl.class.getCanonicalName(), "--port=" + port);
        builder.redirectErrorStream(true);

        process = some(builder.start());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                outStream.println("\nTerminating...");
                process.get().destroy();
            }
        });

        JavaREPLClient replClient = new JavaREPLClient(hostname, port);
        if (!waitUntilInstanceStarted(replClient)) {
            errStream.println("\nERROR: Could not start REPL instance at http://" + hostname + ":" + port);
            System.exit(0);
        }

        return replClient;
    }

    private static boolean waitUntilInstanceStarted(JavaREPLClient client) throws Exception {
        for (int i = 0; i < 50; i++) {
            Thread.sleep(100);
            if (client.isAlive())
                return true;
        }

        return false;
    }

    private static Option<Integer> port(String[] args) {
        return sequence(args).find(startsWith("--port=")).map(compose(replaceAll("--port=", ""), compose(valueOf, intValue)));
    }

    private static Option<String> hostname(String[] args) {
        return sequence(args).find(startsWith("--hostname=")).map(replaceAll("--hostname=", ""));
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

    private static Mapper<Sequence<String>, String> jlineConsole(final JavaREPLClient client) throws IOException {
        return new Mapper<Sequence<String>, String>() {
            private final ConsoleReader consoleReader;

            {
                consoleReader = new ConsoleReader(inStream, outStream);
                consoleReader.setHistoryEnabled(true);
                consoleReader.setExpandEvents(false);
                consoleReader.addCompleter(clientCompleter());
            }

            public String call(Sequence<String> lines) throws Exception {
                consoleReader.setPrompt(lines.isEmpty() ? "\u001B[1mjava> \u001B[0m" : "    \u001B[1m| \u001B[0m");
                consoleReader.setHistory(clientHistory());
                return consoleReader.readLine();
            }

            private MemoryHistory clientHistory() throws Exception {
                MemoryHistory history = new MemoryHistory();
                for (String historyItem : client.history()) {
                    history.add(historyItem);
                }
                return history;
            }

            private jline.console.completer.Completer clientCompleter() {
                return new jline.console.completer.Completer() {
                    public int complete(String expression, int cursor, List<CharSequence> candidates) {
                        try {
                            CompletionResult result = client.completions(expression);
                            candidates.addAll(result.candidates().toList());
                            return result.candidates().isEmpty() ? -1 : result.position();
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                };
            }

        };
    }

}
