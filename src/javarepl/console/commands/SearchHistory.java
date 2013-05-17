package javarepl.console.commands;

import javarepl.console.ConsoleHistory;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.contains;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;
import static javarepl.console.commands.ShowHistory.numberedHistory;

public final class SearchHistory extends Command {
    private static final String COMMAND = ":h?";
    private final ConsoleLogger logger;
    private final ConsoleHistory history;

    public SearchHistory(ConsoleLogger logger, ConsoleHistory history) {
        super(COMMAND + " <term> - searches the history", startsWith(COMMAND), new StringsCompleter(COMMAND));
        this.logger = logger;
        this.history = history;
    }

    public void execute(String expression) {
        String searchTerm = parseStringCommand(expression).second().getOrElse("");
        logger.success(listValues("History search for '" + searchTerm + "'", numberedHistory(history).filter(contains(searchTerm))));
    }
}
