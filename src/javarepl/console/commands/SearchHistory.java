package javarepl.console.commands;

import javarepl.console.Console;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.contains;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;
import static javarepl.console.commands.ShowHistory.numberedHistory;

public final class SearchHistory extends Command {
    private static final String COMMAND = ":h?";

    public SearchHistory() {
        super(COMMAND + " <term> - searches the history", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(Console console, String expression) {
        String searchTerm = parseStringCommand(expression).second().getOrElse("");
        console.logger().info(listValues("History search for '" + searchTerm + "'", numberedHistory(console.history()).filter(contains(searchTerm))));
    }
}
