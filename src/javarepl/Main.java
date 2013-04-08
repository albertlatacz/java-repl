package javarepl;

import com.googlecode.totallylazy.*;
import javarepl.commands.*;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.googlecode.totallylazy.Predicates.always;
import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static javarepl.Utils.applicationVersion;

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

        Rules<String, Void> rules = Rules.<String, Void>rules()
                .addLast(commandAsRule(new QuitApplication()))
                .addLast(commandAsRule(new ShowHelp()))
                .addLast(commandAsRule(new ShowLastSource()))
                .addLast(commandAsRule(new ShowHistory()))
                .addLast(commandAsRule(new ClearAllEvaluations()))
                .addLast(commandAsRule(new ReplayAllEvaluations()))
                .addLast(commandAsRule(new AddToClasspath()))
                .addLast(commandAsRule(new ListValues()))
                .addLast(commandAsRule(new EvaluateFromHistory()))
                .addLast(commandAsRule(new EvaluateExpression()))
                .addLast(noActionRule());

        do {
            rules.apply(expressionReader.readExpression().getOrNull());
            System.out.println();
        } while (true);
    }

    private Rule<String, Void> commandAsRule(Command command) {
        return Rule.rule(command.predicate(), command.apply(evaluator));
    }

    private Rule<String, Void> noActionRule() {
        return Rule.rule(always(), Functions.<String, Void>returns1(null));
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
}
