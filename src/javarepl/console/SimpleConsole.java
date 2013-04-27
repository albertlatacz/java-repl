package javarepl.console;

import com.googlecode.totallylazy.*;
import javarepl.Evaluator;
import javarepl.console.commands.*;

import java.util.Collections;

import static com.googlecode.totallylazy.Predicates.always;

public final class SimpleConsole implements Console {
    private final Evaluator evaluator;
    private final Sequence<Command> commands;
    private final Rules<String, CommandResult> evaluationRules;
    private final ConsoleLogger logger;

    public SimpleConsole(ConsoleLogger logger) {
        this.logger = logger;
        evaluator = new Evaluator();
        commands = createCommands(evaluator);
        evaluationRules = createEvaluationRules(commands);

        registerShutdownHook();
    }

    public CommandResult execute(String expression) {
        return evaluationRules.apply(expression);
    }

    public Sequence<Command> commands() {
        return commands;
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                evaluator.clearOutputDirectory();
            }
        });
    }

    private Sequence<Command> createCommands(Evaluator evaluator) {
        Sequence<Command> commandSequence = Sequences.<Command>sequence()
                .add(new QuitApplication(evaluator))
                .add(new ShowHistory(evaluator))
                .add(new SearchHistory(evaluator))
                .add(new EvaluateFromHistory(evaluator))
                .add(new ResetAllEvaluations(evaluator))
                .add(new ReplayAllEvaluations(evaluator))
                .add(new AddToClasspath(evaluator))
                .add(new LoadSourceFile(evaluator))
                .add(new ShowResult(evaluator))
                .add(new ListValues(evaluator))
                .add(new ShowLastSource(evaluator))
                .add(new ShowTypeOfExpression(evaluator));

        return commandSequence
                .add(new ShowHelp(commandSequence, evaluator))
                .add(new NotAValidCommand(evaluator))
                .add(new EvaluateExpression(evaluator));
    }

    private Rules<String, CommandResult> createEvaluationRules(Sequence<Command> commands) {
        Rules<String, CommandResult> rules = Rules.rules();
        for (Command command : commands) {
            rules.addLast(Rule.rule(command.predicate(), asFunction(command)));
        }
        return rules.addLast(Rule.rule(always(), Functions.<String, CommandResult>returns1(new CommandResult("", Collections.<ConsoleLog>emptyList()))));
    }

    private Function1<String, CommandResult> asFunction(final Command command) {
        return new Function1<String, CommandResult>() {
            @Override
            public CommandResult call(String expression) throws Exception {
                logger.reset();
                command.execute(expression);
                CommandResult result = new CommandResult(expression, logger.logs());
                return result;
            }
        };
    }
}
