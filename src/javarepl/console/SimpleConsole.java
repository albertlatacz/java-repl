package javarepl.console;

import com.googlecode.totallylazy.*;
import javarepl.Evaluator;
import javarepl.console.commands.*;

import static com.googlecode.totallylazy.Predicates.always;
import static com.googlecode.totallylazy.Strings.blank;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.console.ConsoleHistory.historyFromFile;
import static javarepl.console.ConsoleResult.emptyResult;

public final class SimpleConsole implements Console {
    private final Evaluator evaluator = new Evaluator();
    private final ConsoleLogger logger;

    private Sequence<Command> commands = Sequences.empty();

    private ConsoleHistory history;

    public SimpleConsole(SimpleConsoleConfig config) {
        logger = config.logger.getOrElse(new ConsoleLogger());

        commands = config.commands
                .add(new ShowHelp(config.commands))
                .add(new NotAValidCommand())
                .add(new EvaluateExpression());

        history = historyFromFile(startsWith(":h!").or(blank()), config.historyFile);

        registerShutdownHook();
    }

    public ConsoleResult execute(String expression) {
        ConsoleResult result = evaluationRules(this).apply(expression);
        history = history.add(expression);
        return result;
    }

    public Sequence<Command> commands() {
        return commands;
    }

    public ConsoleHistory history() {
        return history;
    }

    public ConsoleLogger logger() {
        return logger;
    }

    public Evaluator evaluator() {
        return evaluator;
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                evaluator.clearOutputDirectory();
                history.save();
            }
        });
    }

    public static Command[] defaultCommands() {
        return Sequences.<Command>sequence()
                .add(new QuitApplication())
                .add(new ShowHistory())
                .add(new SearchHistory())
                .add(new EvaluateFromHistory())
                .add(new ResetAllEvaluations())
                .add(new ReplayAllEvaluations())
                .add(new AddToClasspath())
                .add(new LoadSourceFile())
                .add(new ListValues())
                .add(new ShowLastSource())
                .add(new ShowTypeOfExpression())
                .add(new ShowResult())
                .toArray(Command.class);
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
                logger.reset();
                command.execute(SimpleConsole.this, expression);
                ConsoleResult result = new ConsoleResult(expression, logger.logs());
                return result;
            }
        };
    }
}
