package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.Evaluation.functions.classSource;

public final class ShowLastSource extends Command {
    private static final String COMMAND = ":src";

    public ShowLastSource(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " - show source of last evaluated expression", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector resultCollector) {
        resultCollector.logInfo(evaluator().lastEvaluation().map(classSource()).getOrElse("No source"));
    }
}
