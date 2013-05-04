package javarepl.console.commands;

import javarepl.console.Console;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public final class ShowLastSource extends Command {
    private static final String COMMAND = ":src";

    public ShowLastSource() {
        super(COMMAND + " - show source of last evaluated expression", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(Console console, String expression) {
        System.out.println(console.evaluator().lastSource().getOrElse("No source"));
    }
}
