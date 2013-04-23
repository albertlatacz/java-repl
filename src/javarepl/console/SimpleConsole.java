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

    public SimpleConsole(ConsoleLogger logger) {
        evaluator = new Evaluator();
        commands = createCommands(logger, evaluator);
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

    private Sequence<Command> createCommands(ConsoleLogger logger, Evaluator evaluator) {
        Sequence<Command> commandSequence = Sequences.<Command>sequence()
                .add(new QuitApplication(logger, evaluator))
                .add(new ShowHistory(logger, evaluator))
                .add(new SearchHistory(logger, evaluator))
                .add(new EvaluateFromHistory(logger, evaluator))
                .add(new ResetAllEvaluations(logger, evaluator))
                .add(new ReplayAllEvaluations(logger, evaluator))
                .add(new AddToClasspath(logger, evaluator))
                .add(new LoadSourceFile(logger, evaluator))
                .add(new ShowResult(logger, evaluator))
                .add(new ListValues(logger, evaluator))
                .add(new ShowLastSource(logger, evaluator))
                .add(new ShowTypeOfExpression(logger, evaluator));

        return commandSequence
                .add(new ShowHelp(commandSequence, logger, evaluator))
                .add(new NotAValidCommand(logger, evaluator))
                .add(new EvaluateExpression(logger, evaluator));
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
                return command.execute(expression);
            }
        };
    }
}
