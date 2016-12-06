package javarepl;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Strings;
import com.googlecode.totallylazy.functions.Function1;
import javarepl.client.EvaluationLog;
import javarepl.client.EvaluationResult;
import javarepl.client.JavaREPLClient;
import javarepl.completion.CompletionCandidate;
import javarepl.completion.CompletionResult;
import jline.console.ConsoleReader;
import jline.console.CursorBuffer;
import jline.console.completer.CandidateListCompletionHandler;
import jline.console.completer.CompletionHandler;
import jline.console.history.MemoryHistory;
import org.fusesource.jansi.AnsiConsole;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static com.googlecode.totallylazy.Files.fileOption;
import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.replaceAll;
import static com.googlecode.totallylazy.Strings.startsWith;
import static com.googlecode.totallylazy.functions.Callables.compose;
import static com.googlecode.totallylazy.numbers.Numbers.intValue;
import static com.googlecode.totallylazy.numbers.Numbers.valueOf;
import static com.googlecode.totallylazy.predicates.Not.not;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.util.Collections.singletonList;
import static javarepl.Utils.applicationVersion;
import static javarepl.Utils.randomServerPort;
import static javarepl.client.EvaluationLog.Type.CONTROL;
import static javarepl.completion.CompletionResult.methods.fromJson;
import static javarepl.completion.CompletionResult.methods.toJson;
import static javarepl.console.commands.ClearScreen.CLEAR_SCREEN_CMD;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class Main {

    private static Option<Process> process = none();
    private static ResultPrinter console;

    public static void main(String... args) throws Exception {
        console = new ResultPrinter(printColors(args));

        ConsoleReader consoleReader = new ConsoleReader(System.in, AnsiConsole.out);
        JavaREPLClient client = clientFor(hostname(args), port(args));
        Sequence<String> initialExpressions = initialExpressionsFromFile().join(initialExpressionsFromArgs(args));
        ExpressionReader expressionReader = expressionReaderFor(consoleReader, client, initialExpressions);

        Option<String> expression = none();
        Option<EvaluationResult> result = none();
        while (expression.isEmpty() || !result.isEmpty()) {
            expression = expressionReader.readExpression();

            if (!expression.isEmpty()) {
                result = client.execute(expression.get());
                if (!result.isEmpty()) {
                    for (EvaluationLog log : result.get().logs()) {
                        if (!handleTerminalCommand(log)) {
                            handleTerminalMessage(log);
                        }
                    }
                }
            }
        }
    }

    private static void handleTerminalMessage(EvaluationLog log) {
        console.printEvaluationLog(log);
    }

    private static boolean handleTerminalCommand(EvaluationLog log) {
        if (log.type().equals(CONTROL)){
            switch (log.message()) {
                case CLEAR_SCREEN_CMD: {
                    console.printInfo("\033c");
                    console.printInfo(welcomeMessage());
                    console.printInfo(welcomeInstructions());
                } break;

                default: return false;
            }
            return true;
        }
        return false;
    }

    private static String welcomeMessage() {
        return format("Welcome to JavaREPL version %s (%s, Java %s)",
                applicationVersion(),
                getProperty("java.vm.name"),
                getProperty("java.version"));
    }

    private static String welcomeInstructions() {
        return "Type expression to evaluate, \u001B[32m:help\u001B[0m for more options or press \u001B[32mtab\u001B[0m to auto-complete.";
    }

    private static JavaREPLClient clientFor(Option<String> hostname, Option<Integer> port) throws Exception {
        console.printInfo(welcomeMessage());

        if (hostname.isDefined() && port.isDefined()) {
            return connectToRemoteInstance(hostname.get(), port.get());
        } else {
            return startNewLocalInstance("localhost", port.getOrElse(randomServerPort()));
        }
    }

    private static JavaREPLClient connectToRemoteInstance(String hostname, Integer port) {
        JavaREPLClient replClient = new JavaREPLClient(hostname, port);

        if (!replClient.status().isRunning()) {
            console.printError("ERROR: Could not connect to remote REPL instance at http://" + hostname + ":" + port);
            System.exit(0);
        } else {
            console.printInfo("Connected to remote instance at http://" + hostname + ":" + port);
        }

        String remoteInstanceVersion = replClient.version();
        if (!remoteInstanceVersion.equals(applicationVersion())) {
            console.printError("WARNING: Client version (" + applicationVersion() + ") is different from remote instance version (" + remoteInstanceVersion + ")");
        }

        return replClient;
    }

    private static JavaREPLClient startNewLocalInstance(String hostname, Integer port) throws Exception {
        if (getSystemJavaCompiler() == null) {
            console.printError("\nERROR: Java compiler not found.\n" +
                    "This can occur when JavaREPL was run with JRE instead of JDK or JDK is not configured correctly.");
            System.exit(0);
        }

        console.printInfo(welcomeInstructions());

        ProcessBuilder builder = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), Repl.class.getCanonicalName(), "--port=" + port);
        builder.redirectErrorStream(true);

        console.printInfo("Connected to local instance at http://" + hostname + ":" + port);

        process = some(builder.start());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                console.printInfo("\nTerminating...");
                process.get().destroy();
            }
        });

        JavaREPLClient replClient = new JavaREPLClient(hostname, port);
        if (!waitUntilInstanceStarted(replClient)) {
            console.printError("\nERROR: Could not start REPL instance at http://" + hostname + ":" + port);
            System.exit(0);
        }

        return replClient;
    }

    private static boolean waitUntilInstanceStarted(JavaREPLClient client) throws Exception {
        for (int i = 0; i < 500; i++) {
            Thread.sleep(10);
            if (client.status().isRunning())
                return true;
        }

        return false;
    }

    private static Sequence<String> initialExpressionsFromArgs(String[] args) {
        return sequence(args)
                .find(startsWith("--expression="))
                .map(replaceAll("--expression=", ""))
                .toSequence();
    }

    private static Sequence<String> initialExpressionsFromFile() {
        return fileOption(new File("."), "javarepl.init")
                .map(readExpressionsFile())
                .getOrElse(empty(String.class));
    }

    private static Function1<File, Sequence<String>> readExpressionsFile() {
        return f -> sequence(Files.readAllLines(f.toPath(), StandardCharsets.UTF_8)).filter(not(Strings::isBlank));
    }

    private static Option<Integer> port(String[] args) {
        return sequence(args).find(startsWith("--port=")).map(compose(replaceAll("--port=", ""), compose(valueOf, intValue)));
    }

    private static Option<String> hostname(String[] args) {
        return sequence(args).find(startsWith("--hostname=")).map(replaceAll("--hostname=", ""));
    }

    private static Boolean printColors(String[] args) {
        return !sequence(args).contains("--noColors");
    }

    private static ExpressionReader expressionReaderFor(ConsoleReader consoleReader, final JavaREPLClient client, Sequence<String> initialExpressions) throws IOException {
        return new ExpressionReader(new Function1<Sequence<String>, String>() {
            private static final char CTRL_C = (char) 3;
            private static final char CTRL_D = (char) 4;
            private Sequence<String> expressions = initialExpressions;

            {
                consoleReader.setCompletionHandler(new JlineCompletionHandler());
                consoleReader.setHistoryEnabled(true);
                consoleReader.setExpandEvents(false);
                consoleReader.addCompleter(clientCompleter());
                consoleReader.addTriggeredAction(CTRL_D, e -> System.exit(0));
            }

            public String call(Sequence<String> lines) throws Exception {
                consoleReader.setPrompt(console.ansiColored(lines.isEmpty() ? "\u001B[1mjava> \u001B[0m" : "    \u001B[1m| \u001B[0m"));
                consoleReader.setHistory(clientHistory());
                return readExpression();
            }

            private String readExpression() throws IOException {
                String expressionLine = null;
                if (!expressions.isEmpty()) {
                    expressionLine = expressions.head();
                    expressions = expressions.tail();

                }
                return expressionLine != null ? expressionLine : consoleReader.readLine();
            }

            private MemoryHistory clientHistory() throws Exception {
                MemoryHistory history = new MemoryHistory();
                for (String historyItem : client.history()) {
                    history.add(historyItem);
                }
                return history;
            }

            private jline.console.completer.Completer clientCompleter() {
                return (expression, cursor, candidates) -> {
                    try {
                        CompletionResult result = client.completions(expression);
                        candidates.addAll(singletonList(toJson(result)));
                        return result.candidates().isEmpty() ? -1 : result.position();
                    } catch (Exception e) {
                        return -1;
                    }
                };
            }
        });
    }


    /**
     * Copied from JLine sourcecode and heavily modified
     * Original sources: https://raw.github.com/jline/jline2/master/src/main/java/jline/console/completer/CandidateListCompletionHandler.java
     */
    public static class JlineCompletionHandler implements CompletionHandler {
        // TODO: handle quotes and escaped quotes && enable automatic escaping of whitespace

        public boolean complete(final ConsoleReader reader, final List<CharSequence> candidatesJson, final int pos) throws IOException {
            CursorBuffer buf = reader.getCursorBuffer();
            CompletionResult completionResult = fromJson(sequence(candidatesJson).head().toString());
            Sequence<String> candidatesToPrint = empty(String.class);

            // if there is only one completion, then fill in the buffer
            if (completionResult.candidates().size() == 1) {
                CharSequence value = completionResult.candidates().head().value();

                // fail if the only candidate is the same as the current buffer
                if (value.equals(buf.toString())) {
                    return false;
                }

                setBuffer(reader, value, pos);
                candidatesToPrint = completionResult.candidates().flatMap(CompletionCandidate::forms);
            } else if (completionResult.candidates().size() > 1) {
                String value = getUnambiguousCompletions(completionResult.candidates());
                setBuffer(reader, value, pos);
                candidatesToPrint = completionResult.candidates().map(CompletionCandidate::value);
            }

            printCandidates(reader, candidatesToPrint.safeCast(CharSequence.class).toList());

            // redraw the current console buffer
            reader.drawLine();

            return true;
        }

        public static void setBuffer(final ConsoleReader reader, final CharSequence value, final int offset) throws
                IOException {
            while ((reader.getCursorBuffer().cursor > offset) && reader.backspace()) {
                // empty
            }

            reader.putString(value);
            reader.setCursorPosition(offset + value.length());
        }


        public static void printCandidates(final ConsoleReader reader, Collection<CharSequence> candidates) throws
                IOException {
            Set<CharSequence> distinct = new HashSet<CharSequence>(candidates);

            if (distinct.size() > reader.getAutoprintThreshold()) {
                //noinspection StringConcatenation
                reader.print(Messages.DISPLAY_CANDIDATES.format(candidates.size()));
                reader.flush();

                int c;

                String noOpt = Messages.DISPLAY_CANDIDATES_NO.format();
                String yesOpt = Messages.DISPLAY_CANDIDATES_YES.format();
                char[] allowed = {yesOpt.charAt(0), noOpt.charAt(0)};

                while ((c = reader.readCharacter(allowed)) != -1) {
                    String tmp = new String(new char[]{(char) c});

                    if (noOpt.startsWith(tmp)) {
                        reader.println();
                        return;
                    } else if (yesOpt.startsWith(tmp)) {
                        break;
                    } else {
                        reader.beep();
                    }
                }
            }

            // copy the values and make them distinct, without otherwise affecting the ordering. Only do it if the sizes differ.
            if (distinct.size() != candidates.size()) {
                Collection<CharSequence> copy = new ArrayList<CharSequence>();

                for (CharSequence next : candidates) {
                    if (!copy.contains(next)) {
                        copy.add(next);
                    }
                }

                candidates = copy;
            }

            reader.println();
            reader.printColumns(candidates);
        }

        /**
         * Returns a root that matches all the {@link String} elements of the specified {@link List},
         * or null if there are no commonalities. For example, if the list contains
         * <i>foobar</i>, <i>foobaz</i>, <i>foobuz</i>, the method will return <i>foob</i>.
         */
        private String getUnambiguousCompletions(final Sequence<CompletionCandidate> candidates) {
            if (candidates == null || candidates.isEmpty()) {
                return null;
            }

            // convert to an array for speed
            String[] strings = candidates.map(CompletionCandidate::value).toArray(new String[candidates.size()]);

            String first = strings[0];
            StringBuilder candidate = new StringBuilder();

            for (int i = 0; i < first.length(); i++) {
                if (startsWith(first.substring(0, i + 1), strings)) {
                    candidate.append(first.charAt(i));
                } else {
                    break;
                }
            }

            return candidate.toString();
        }

        /**
         * @return true is all the elements of <i>candidates</i> start with <i>starts</i>
         */
        private boolean startsWith(final String starts, final String[] candidates) {
            for (String candidate : candidates) {
                if (!candidate.startsWith(starts)) {
                    return false;
                }
            }

            return true;
        }

        private static enum Messages {
            DISPLAY_CANDIDATES,
            DISPLAY_CANDIDATES_YES,
            DISPLAY_CANDIDATES_NO,;

            private static final
            ResourceBundle
                    bundle =
                    ResourceBundle.getBundle(CandidateListCompletionHandler.class.getName(), Locale.getDefault());

            public String format(final Object... args) {
                if (bundle == null)
                    return "";
                else
                    return String.format(bundle.getString(name()), args);
            }
        }
    }


}
