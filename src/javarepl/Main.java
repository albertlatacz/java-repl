package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import javarepl.console.ConsoleI;
import javarepl.console.ConsoleLogger;
import javarepl.console.SimpleConsole;
import javarepl.console.commands.Command;
import javarepl.console.rest.RestConsole;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;

import java.io.BufferedReader;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementPermission;
import java.lang.reflect.ReflectPermission;
import java.net.SocketPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.util.PropertyPermission;

import static com.googlecode.totallylazy.Callables.compose;
import static com.googlecode.totallylazy.Predicates.notNullValue;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.replaceAll;
import static com.googlecode.totallylazy.Strings.startsWith;
import static com.googlecode.totallylazy.numbers.Numbers.intValue;
import static com.googlecode.totallylazy.numbers.Numbers.valueOf;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static javarepl.Utils.applicationVersion;
import static javarepl.console.ConsoleLogger.systemConsoleLogger;
import static javarepl.console.commands.Command.functions.completer;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean simpleConsole = isSimpleConsole(args);
        boolean sandboxed = isSandboxed(args);
        Option<Integer> port = port(args);

        ConsoleLogger logger = systemConsoleLogger();
        ConsoleI console = new RestConsole(new SimpleConsole(logger), port);
        ExpressionReader expressionReader = new ExpressionReader(simpleConsole ? readFromSimpleConsole() : readFromExtendedConsole(console.commands()));

        logger.logInfo(format("Welcome to JavaREPL version %s (%s, %s, Java %s)",
                applicationVersion(),
                sandboxed ? "sandboxed" : "unrestricted",
                getProperty("java.vm.name"),
                getProperty("java.version")));

        if (sandboxed) {
            sandboxApplication();
        }

        if (environmentChecksPassed(logger)) {
            logger.logInfo("Type in expression to evaluate.");
            logger.logInfo("Type :help for more options.");
            logger.logInfo("");

            do {
                console.execute(expressionReader.readExpression().getOrNull());
                logger.logInfo("");
            } while (true);
        }
    }

    private static boolean isSimpleConsole(String[] args) {
        return sequence(args).contains("--simple-console");
    }

    private static boolean isSandboxed(String[] args) {
        return sequence(args).contains("--sandboxed");
    }

    private static Option<Integer> port(String[] args) {
        return sequence(args).find(startsWith("--port=")).map(compose(replaceAll("--port=", ""), compose(valueOf, intValue)));
    }

    private static boolean environmentChecksPassed(ConsoleLogger logger) {
        if (getSystemJavaCompiler() == null) {
            logger.logError("\nERROR: Java compiler not found.\n" +
                    "This can occur when JavaREPL was run with JRE instead of JDK or JDK is not configured correctly.");
            return false;
        }
        return true;
    }

    private static void sandboxApplication() {
        Policy.setPolicy(new Policy() {
            private final PermissionCollection permissions = new Permissions();

            {
                java.security.Security.setProperty("package.access", Thread.class.getCanonicalName());
                permissions.add(new SocketPermission("*", "connect, listen, resolve, accept"));
                permissions.add(new RuntimePermission("getProtectionDomain"));
                permissions.add(new RuntimePermission("accessDeclaredMembers"));
                permissions.add(new RuntimePermission("createClassLoader"));
                permissions.add(new RuntimePermission("closeClassLoader"));
                permissions.add(new RuntimePermission("modifyThreadGroup"));
                permissions.add(new RuntimePermission("getStackTrace"));
                permissions.add(new ManagementPermission("monitor"));
                permissions.add(new ReflectPermission("suppressAccessChecks"));
                permissions.add(new PropertyPermission("*", "read"));
                permissions.add(new FilePermission("/var/folders/1x/q0fyq22n6n54qsjw97vknhrw0000gn/T/JavaREPL/-", "read, write, delete"));
                permissions.add(new FilePermission("<<ALL FILES>>", "read"));
            }

            @Override
            public PermissionCollection getPermissions(CodeSource codesource) {
                return permissions;
            }
        });

        System.setSecurityManager(new SecurityManager());
    }

    private static Function1<Sequence<String>, String> readFromExtendedConsole(final Sequence<Command> commandSequence) throws IOException {
        return new Function1<Sequence<String>, String>() {
            private final ConsoleReader console;

            {
                console = new ConsoleReader(System.in, System.out);
                console.setHistoryEnabled(true);
                console.addCompleter(new AggregateCompleter(commandSequence.map(completer()).filter(notNullValue()).toList()));
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
