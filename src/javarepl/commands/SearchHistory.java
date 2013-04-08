package javarepl.commands;

import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.contains;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;
import static javarepl.commands.ShowHistory.history;

public class SearchHistory extends Command {
    private static final String COMMAND = ":h?";

    public SearchHistory() {
        super(COMMAND + " <term> - searches the history", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        String searchTerm = sequence(expression.split(" ")).tail().toString(" ");
        listValues("History search for '" + searchTerm + "'", history(evaluator).filter(contains(searchTerm)));
        return null;
    }
}
