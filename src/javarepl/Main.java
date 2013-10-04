package javarepl;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import javarepl.client.EvaluationLog;
import javarepl.client.EvaluationResult;
import javarepl.client.JavaREPLClient;
import javarepl.completion.CompletionResult;
import jline.TerminalFactory;
import jline.console.ConsoleReader;
import jline.console.history.MemoryHistory;
import org.fusesource.jansi.AnsiOutputStream;

import java.io.IOException;
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
import static org.fusesource.jansi.AnsiConsole.wrapOutputStream;

public class Main {

    private static Option<Process> process = none();

    public static void main(String... args) throws Exception {
        configureOutputStreams();

        JavaREPLClient client = clientFor(hostname(args), port(args));
        ExpressionReader expressionReader = expressionReaderFor(client);

        Option<String> expression = none();
        Option<EvaluationResult> result = none();
        while (expression.isEmpty() || !result.isEmpty()) {
            expression = expressionReader.readExpression();

            if (!expression.isEmpty()) {
                result = client.execute(expression.get());
                if (!result.isEmpty())
                    printResult(result.get());
            }
        }
    }

    private static void configureOutputStreams() {
        boolean isWindows = System.getProperty("os.name").startsWith("Windows");
        TerminalFactory.configure(isWindows ? TerminalFactory.Type.NONE : TerminalFactory.Type.AUTO);

        System.setOut(new PrintStream(isWindows ? new AnsiOutputStream(System.out) : wrapOutputStream(System.out)));
        System.setErr(new PrintStream(isWindows ? new AnsiOutputStream(System.err) : wrapOutputStream(System.err)));
    }

    private static JavaREPLClient clientFor(Option<String> hostname, Option<Integer> port) throws Exception {
        System.out.println(format("Welcome to JavaREPL version %s (%s, Java %s)",
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
            System.err.println("ERROR: Could not connect to remote REPL instance at http://" + hostname + ":" + port);
            System.exit(0);
        } else {
            System.out.println("Connected to remote instance at http://" + hostname + ":" + port);
        }

        String remoteInstanceVersion = replClient.version();
        if (!remoteInstanceVersion.equals(applicationVersion())) {
            System.err.println("WARNING: Client version (" + applicationVersion() + ") is different from remote instance version (" + remoteInstanceVersion + ")");
        }

        return replClient;
    }

    private static JavaREPLClient startNewLocalInstance(String hostname, Integer port) throws Exception {
        if (getSystemJavaCompiler() == null) {
            System.err.println("\nERROR: Java compiler not found.\n" +
                    "This can occur when JavaREPL was run with JRE instead of JDK or JDK is not configured correctly.");
            System.exit(0);
        }

        System.out.println("Type expression to evaluate, \u001B[32m:help\u001B[0m for more options or press \u001B[32mtab\u001B[0m to auto-complete.");

        ProcessBuilder builder = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), Repl.class.getCanonicalName(), "--port=" + port);
        builder.redirectErrorStream(true);

        process = some(builder.start());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("\nTerminating...");
                process.get().destroy();
            }
        });

        JavaREPLClient replClient = new JavaREPLClient(hostname, port);
        if (!waitUntilInstanceStarted(replClient)) {
            System.err.println("\nERROR: Could not start REPL instance at http://" + hostname + ":" + port);
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

    private static ExpressionReader expressionReaderFor(final JavaREPLClient client) throws IOException {
        return new ExpressionReader(new Mapper<Sequence<String>, String>() {
            private final ConsoleReader consoleReader;

            {
                consoleReader = new ConsoleReader(System.in, System.out);
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

        });
    }

}
