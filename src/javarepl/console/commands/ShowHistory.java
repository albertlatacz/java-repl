package javarepl.console.commands;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import com.googlecode.totallylazy.numbers.Numbers;
import javarepl.console.Console;
import javarepl.console.ConsoleHistory;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Pair.functions.values;
import static com.googlecode.totallylazy.Strings.replace;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;

public final class ShowHistory extends Command {
    private static final String COMMAND = ":hist";

    public ShowHistory() {
        super(COMMAND + " [num] - shows the history (optional 'num' is number of evaluations to show)",
                startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(Console console, String expression) {
        Integer limit = parseNumericCommand(expression).second().getOrElse(console.history().items().size());
        Sequence<String> numberedHistory = numberedHistory(console.history()).reverse().take(limit).reverse();

        if (!numberedHistory.isEmpty()) {
            System.out.println(listValues("History", numberedHistory));
        } else {
            System.out.println("No history.");
        }
    }

    public static Sequence<String> numberedHistory(ConsoleHistory consoleHistory) {
        return Numbers.range(1)
                .zip(consoleHistory.items().map(replace("\n", "\n   ")))
                .map(values().then(Sequences.toString("  ")));
    }
}
