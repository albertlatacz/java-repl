package javarepl;

import com.googlecode.totallylazy.*;
import javarepl.commands.*;

import static com.googlecode.totallylazy.Predicates.always;

public class Console {
    private final Evaluator evaluator;
    private final Sequence<Command> commands;
    private final Rules<String, Void> evaluationRules;

    public Console(ConsoleLogger logger) {
        evaluator = new Evaluator();
        commands = createCommands(logger);
        evaluationRules = createEvaluationRules(commands, evaluator);

        registerShutdownHook();
    }

    public void execute(String expression) {
        evaluationRules.apply(expression);
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                evaluator.clearOutputDirectory();
            }
        });
    }

    Sequence<Command> commands() {
        return commands;
    }

    private Sequence<Command> createCommands(ConsoleLogger logger) {
        Sequence<Command> commandSequence = Sequences.<Command>sequence()
                .add(new QuitApplication(logger))
                .add(new ShowHistory(logger))
                .add(new SearchHistory(logger))
                .add(new EvaluateFromHistory(logger))
                .add(new ResetAllEvaluations(logger))
                .add(new ReplayAllEvaluations(logger))
                .add(new AddToClasspath(logger))
                .add(new LoadSourceFile(logger))
                .add(new ShowResult(logger))
                .add(new ListValues(logger))
                .add(new ShowLastSource(logger))
                .add(new ShowTypeOfExpression(logger));

        return commandSequence
                .add(new ShowHelp(commandSequence, logger))
                .add(new NotAValidCommand(logger))
                .add(new EvaluateExpression(logger));
    }

    private Rules<String, Void> createEvaluationRules(Sequence<Command> commands, Evaluator evaluator) {
        Rules<String, Void> rules = Rules.rules();
        for (Command command : commands) {
            rules.addLast(Rule.rule(command, command.apply(evaluator)));
        }
        return rules.addLast(Rule.rule(always(), Functions.<String, Void>returns1(null)));
    }
}
