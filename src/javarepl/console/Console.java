package javarepl.console;

import com.googlecode.totallylazy.*;
import javarepl.Evaluator;
import javarepl.console.commands.*;

import static com.googlecode.totallylazy.Predicates.always;

public class Console {
    private final Evaluator evaluator;
    private final Sequence<Command> commands;
    private final Rules<String, CommandResult> evaluationRules;

    public Console(ConsoleLogger logger) {
        evaluator = new Evaluator();
        commands = createCommands(logger, evaluator);
        evaluationRules = createEvaluationRules(commands);

        registerShutdownHook();
    }

    public CommandResult execute(String expression) {
        return evaluationRules.apply(expression);
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                evaluator.clearOutputDirectory();
            }
        });
    }

    public Sequence<Command> commands() {
        return commands;
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
            rules.addLast(Rule.rule(command.predicate(), commandToFunction(command)));
        }
        return rules.addLast(Rule.rule(always(), Functions.<String, CommandResult>returns1(null)));
    }

    private Function1<String, CommandResult> commandToFunction(final Command command) {
        return new Function1<String, CommandResult>() {
            @Override
            public CommandResult call(String expression) throws Exception {
                return command.execute(expression);
            }
        };
    }
}
