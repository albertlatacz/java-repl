package javarepl.console;

import com.googlecode.totallylazy.*;
import javarepl.Evaluator;
import javarepl.console.commands.*;

import java.io.File;

import static com.googlecode.totallylazy.Predicates.always;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.console.ConsoleHistory.historyFromFile;
import static javarepl.console.ConsoleResult.emptyResult;

public final class SimpleConsole implements Console {
    private final Evaluator evaluator;
    private final Sequence<Command> commands;
    private final Rules<String, ConsoleResult> evaluationRules;
    private final ConsoleLogger logger;

    private ConsoleHistory history;

    public SimpleConsole(ConsoleLogger logger, Option<File> historyFile) {
        this.logger = logger;

        evaluator = new Evaluator();
        history = historyFromFile(startsWith(":h!"), historyFile);

        commands = createCommands();
        evaluationRules = createEvaluationRules(commands);

        registerShutdownHook();
    }

    public ConsoleResult execute(String expression) {
        ConsoleResult result = evaluationRules.apply(expression);
        history = history.add(expression);
        return result;
    }

    public Sequence<Command> commands() {
        return commands;
    }

    public ConsoleHistory history() {
        return history;
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

    private Sequence<Command> createCommands() {
        Sequence<Command> commandSequence = Sequences.<Command>sequence()
                .add(new QuitApplication(this))
                .add(new ShowHistory(this))
                .add(new SearchHistory(this))
                .add(new EvaluateFromHistory(this))
                .add(new ResetAllEvaluations(this))
                .add(new ReplayAllEvaluations(this))
                .add(new AddToClasspath(this))
                .add(new LoadSourceFile(this))
                .add(new ListValues(this))
                .add(new ShowLastSource(this))
                .add(new ShowTypeOfExpression(this))
                .add(new ShowResult(this));

        return commandSequence
                .add(new ShowHelp(commandSequence, this))
                .add(new NotAValidCommand(this))
                .add(new EvaluateExpression(this));
    }

    private Rules<String, ConsoleResult> createEvaluationRules(Sequence<Command> commands) {
        Rules<String, ConsoleResult> rules = Rules.rules();
        for (Command command : commands) {
            rules.addLast(Rule.rule(command.predicate(), asFunction(command)));
        }
        return rules.addLast(Rule.rule(always(), Functions.<String, ConsoleResult>returns1(emptyResult())));
    }

    private Function1<String, ConsoleResult> asFunction(final Command command) {
        return new Function1<String, ConsoleResult>() {
            @Override
            public ConsoleResult call(String expression) throws Exception {
                logger.reset();
                command.execute(expression);
                ConsoleResult result = new ConsoleResult(expression, logger.logs());
                return result;
            }
        };
    }
}
