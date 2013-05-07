package javarepl.console;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Functions;
import com.googlecode.totallylazy.Rule;
import com.googlecode.totallylazy.Rules;
import com.googlecode.yadic.Container;
import com.googlecode.yadic.SimpleContainer;
import javarepl.Evaluator;
import javarepl.console.commands.Command;
import javarepl.console.commands.Commands;

import static com.googlecode.totallylazy.Predicates.always;
import static com.googlecode.totallylazy.Strings.blank;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.console.ConsoleHistory.historyFromFile;
import static javarepl.console.ConsoleResult.emptyResult;

public final class SimpleConsole implements Console {
    private final Container context;

    public SimpleConsole(ConsoleConfig config) {
        registerShutdownHook();

        context = new SimpleContainer();
        context.addInstance(Container.class, context);
        context.addInstance(Console.class, this);
        context.addInstance(ConsoleHistory.class, historyFromFile(startsWith(":h!").or(blank()), config.historyFile));
        context.addInstance(ConsoleConfig.class, config);
        context.addInstance(Evaluator.class, config.evaluator);
        context.addInstance(ConsoleLogger.class, config.logger);
        context.add(Commands.class);

        context.get(Evaluator.class).addResults(config.results);
    }

    public ConsoleResult execute(String expression) {
        ConsoleResult result = evaluationRules().apply(expression);
        context.get(ConsoleHistory.class).add(expression);
        return result;
    }

    public Container context() {
        return context;
    }

    public Evaluator evaluator() {
        return context.get(Evaluator.class);
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                context.get(Evaluator.class).clearOutputDirectory();
                context.get(ConsoleHistory.class).save();
            }
        });
    }

    private Rules<String, ConsoleResult> evaluationRules() {
        Rules<String, ConsoleResult> rules = Rules.rules();
        for (Command command : context.get(Commands.class).allCommands()) {
            rules.addLast(Rule.rule(command.predicate(), asFunction(command)));
        }
        return rules.addLast(Rule.rule(always(), Functions.<String, ConsoleResult>returns1(emptyResult())));
    }

    private Function1<String, ConsoleResult> asFunction(final Command command) {
        return new Function1<String, ConsoleResult>() {
            @Override
            public ConsoleResult call(String expression) throws Exception {
                context.get(ConsoleLogger.class).reset();
                command.execute(expression);
                ConsoleResult result = new ConsoleResult(expression, context.get(ConsoleLogger.class).logs());
                return result;
            }
        };
    }
}
