package javarepl;

import com.googlecode.totallylazy.*;
import javarepl.commands.*;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.Completer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.googlecode.totallylazy.Predicates.always;
import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static javarepl.Utils.applicationVersion;

public class Main implements Runnable {

    public static void main(String[] args) throws Exception {
        Sequence<String> arguments = sequence(args);
        boolean simpleConsole = arguments.contains("-sc");

        new Main(simpleConsole).run();
    }

    private final Evaluator evaluator;
    private final ExpressionReader expressionReader;
    private final Sequence<Command> commands;

    public Main(boolean simpleConsole) throws Exception {
        System.out.println(format("Welcome to JavaREPL version %s (%s, Java %s)", applicationVersion(), getProperty("java.vm.name"), getProperty("java.version")));

        evaluator = new Evaluator();
        commands = createCommands();
        expressionReader = new ExpressionReader(simpleConsole ? readFromSimpleConsole() : readFromExtendedConsole(commands));

        registerShutdownHook();

        System.out.println("Type in expression to evaluate.");
        System.out.println("Type :help for more options.");
        System.out.println("");
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                evaluator.clearOutputDirectory();
            }
        });
    }

    private Sequence<Command> createCommands() {
        Sequence<Command> commandSequence = Sequences.<Command>sequence()
                .add(new QuitApplication())
                .add(new ShowHistory())
                .add(new SearchHistory())
                .add(new EvaluateFromHistory())
                .add(new ResetAllEvaluations())
                .add(new ReplayAllEvaluations())
                .add(new AddToClasspath())
                .add(new ListValues())
                .add(new ShowLastSource())
                .add(new ShowTypeOfExpression());

        return commandSequence
                .add(new ShowHelp(commandSequence))
                .add(new NotAValidCommand())
                .add(new EvaluateExpression());
    }

    public void run() {
        Rules<String, Void> rules = commandsToRules(commands, evaluator);

        do {
            rules.apply(expressionReader.readExpression().getOrNull());
            System.out.println();
        } while (true);
    }

    public static final Rules<String, Void> commandsToRules(Sequence<? extends Command> commands, Evaluator evaluator) {
        Rules<String, Void> rules = Rules.rules();
        for (Command command : commands) {
            rules.addLast(Rule.rule(command, command.apply(evaluator)));
        }
        return rules.addLast(Rule.rule(always(), Functions.<String, Void>returns1(null)));
    }

    private Function1<Sequence<String>, String> readFromExtendedConsole(final Sequence<Command> commandSequence) throws IOException {
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

    private Function1<Sequence<String>, String> readFromSimpleConsole() {
        return new Function1<Sequence<String>, String>() {
            private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            public String call(Sequence<String> lines) throws Exception {
                return reader.readLine();
            }
        };
    }
}
