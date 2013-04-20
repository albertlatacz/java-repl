package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static javarepl.Evaluation.functions.classSource;

public class ShowLastSource extends Command {
    private static final String COMMAND = ":src";

    public ShowLastSource(ConsoleLogger logger) {
        super(COMMAND + " - show source of last evaluated expression", equalTo(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        logInfo(evaluator.lastEvaluation().map(classSource()).getOrElse("No source"));
        return null;
    }
}
