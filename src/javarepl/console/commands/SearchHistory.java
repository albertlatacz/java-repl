package javarepl.console.commands;

import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.contains;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;
import static javarepl.console.commands.ShowHistory.numberedHistory;

public final class SearchHistory extends Command {
    private static final String COMMAND = ":h?";

    public SearchHistory(Evaluator evaluator) {
        super(evaluator, COMMAND + " <term> - searches the history", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(String expression) {
        String searchTerm = parseStringCommand(expression).second().getOrElse("");
        System.out.println(listValues("History search for '" + searchTerm + "'", numberedHistory(evaluator()).filter(contains(searchTerm))));
    }
}
