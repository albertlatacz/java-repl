package javarepl.console.commands;

import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class ShowLastSource extends Command {
    private static final String COMMAND = ":src";

    public ShowLastSource(Evaluator evaluator) {
        super(evaluator, COMMAND + " - show source of last evaluated expression", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(String expression) {
        System.out.println(evaluator().lastSource().getOrElse("No source"));
    }
}
