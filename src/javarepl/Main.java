package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Sequence;
import javarepl.commands.Command;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.Completer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static javarepl.Utils.applicationVersion;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean simpleConsole = sequence(args).contains("-sc");
        ConsoleLogger logger = commandLogger();

        Console console = new Console(logger);
        ExpressionReader expressionReader = new ExpressionReader(simpleConsole ? readFromSimpleConsole() : readFromExtendedConsole(console.commands()));

        logger.logInfo(format("Welcome to JavaREPL version %s (%s, Java %s)", applicationVersion(), getProperty("java.vm.name"), getProperty("java.version")));

        if (environmentChecksPassed()) {
            logger.logInfo("Type in expression to evaluate.");
            logger.logInfo("Type :help for more options.");
            logger.logInfo("");

            do {
                console.execute(expressionReader.readExpression().getOrNull());
                logger.logInfo("");
            } while (true);
        }
    }

    private static ConsoleLogger commandLogger() {
        return new ConsoleLogger() {
            public Void call(LogType logType, String value) throws Exception {
                if (logType == LogType.INFO) {
                    System.out.println(value);
                }
                if (logType == LogType.ERROR) {
                    System.err.println(value);
                }
                return null;
            }
        };
    }

    private static boolean environmentChecksPassed() {
        if (getSystemJavaCompiler() == null) {
            System.err.println("\nERROR: Java compiler not found.\n" +
                    "This can occur when JavaREPL was run with JRE instead of JDK or JDK is not configured correctly.");
            return false;
        }
        return true;
    }

    private static Function1<Sequence<String>, String> readFromExtendedConsole(final Sequence<Command> commandSequence) throws IOException {
        return new Function1<Sequence<String>, String>() {
            private final ConsoleReader console;

            {
                console = new ConsoleReader(System.in, System.out);
                console.setHistoryEnabled(true);
                console.addCompleter(new AggregateCompleter(commandSequence.safeCast(Completer.class).toList()));
            }

            public String call(Sequence<String> lines) throws Exception {
                console.setPrompt(lines.isEmpty() ? "java> " : "    | ");
                return console.readLine();
            }
        };
    }

    private static Function1<Sequence<String>, String> readFromSimpleConsole() {
        return new Function1<Sequence<String>, String>() {
            private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            public String call(Sequence<String> lines) throws Exception {
                return reader.readLine();
            }
        };
    }
}
