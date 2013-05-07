package javarepl.console;

import com.googlecode.totallylazy.*;
import com.googlecode.yadic.Container;
import com.googlecode.yadic.SimpleContainer;
import javarepl.Evaluator;
import javarepl.console.commands.*;

import static com.googlecode.totallylazy.Predicates.always;
import static com.googlecode.totallylazy.Strings.blank;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.console.ConsoleHistory.historyFromFile;
import static javarepl.console.ConsoleResult.emptyResult;

public final class SimpleConsole implements Console {
    private final Container context;
    private final SimpleConsoleConfig config;

    private ConsoleHistory history;

    public SimpleConsole(SimpleConsoleConfig config) {
        this.config = config;
        config.evaluator.addResults(config.results);

        history = historyFromFile(startsWith(":h!").or(blank()), config.historyFile);

        registerShutdownHook();

        context = new SimpleContainer();
        context.addInstance(Evaluator.class, config.evaluator);
        context.addInstance(ConsoleLogger.class, config.logger);
        context.addInstance(Console.class, this);
    }

    public ConsoleResult execute(String expression) {
        ConsoleResult result = evaluationRules(this).apply(expression);
        history = history.add(expression);
        return result;
    }

    public Container context() {
        final SimpleContainer container = new SimpleContainer(context);
        container.addInstance(ConsoleHistory.class, history());
        return container;
    }

    public Sequence<Command> commands() {
        Sequence<Command> commands = config.commands.map(createCommandIn(context()));

        return commands.add(new ShowHelp(commands, config.logger))
                .add(new NotAValidCommand(config.logger))
                .add(new ShowResult(config.evaluator, config.logger))
                .add(new EvaluateExpression(config.evaluator, config.logger));
    }

    private Function1<Class<? extends Command>, Command> createCommandIn(final Container container) {
        return new Function1<Class<? extends Command>, Command>() {
            public Command call(Class<? extends Command> aClass) throws Exception {
                return container.create(aClass);
            }
        };
    }

    public ConsoleHistory history() {
        return history;
    }

    public Evaluator evaluator() {
        return config.evaluator;
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                config.evaluator.clearOutputDirectory();
                history.save();
            }
        });
    }

    public static Class<? extends Command>[] defaultCommands() {
        return Sequences.<Class<? extends Command>>sequence()
                .add(QuitApplication.class)
                .add(ShowHistory.class)
                .add(SearchHistory.class)
                .add(EvaluateFromHistory.class)
                .add(ResetAllEvaluations.class)
                .add(ReplayAllEvaluations.class)
                .add(AddToClasspath.class)
                .add(LoadSourceFile.class)
                .add(ListValues.class)
                .add(ShowLastSource.class)
                .add(ShowTypeOfExpression.class)
                .toArray(Command.class.getClass());
    }

    private Rules<String, ConsoleResult> evaluationRules(Console console) {
        Rules<String, ConsoleResult> rules = Rules.rules();
        for (Command command : commands()) {
            rules.addLast(Rule.rule(command.predicate(console), asFunction(command)));
        }
        return rules.addLast(Rule.rule(always(), Functions.<String, ConsoleResult>returns1(emptyResult())));
    }

    private Function1<String, ConsoleResult> asFunction(final Command command) {
        return new Function1<String, ConsoleResult>() {
            @Override
            public ConsoleResult call(String expression) throws Exception {
                config.logger.reset();
                command.execute(expression);
                ConsoleResult result = new ConsoleResult(expression, config.logger.logs());
                return result;
            }
        };
    }
}
