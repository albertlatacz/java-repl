package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.console.Console;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class EvaluateFromHistory extends Command {
    private static final String COMMAND = ":h!";

    public EvaluateFromHistory() {
        super(COMMAND + " num - evaluate expression from history)", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(Console console, String expression) {
        Integer historyItem = parseNumericCommand(expression).second().getOrElse(console.history().items().size());
        Option<String> fromHistory = console.history().items().drop(historyItem - 1).headOption();

        if (!fromHistory.isEmpty()) {
            console.execute(fromHistory.get());
        } else {
            console.logger().error("Expression not found.\n");
        }
    }
}
