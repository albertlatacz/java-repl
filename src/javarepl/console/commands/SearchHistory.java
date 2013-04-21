package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.contains;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;

public final class SearchHistory extends Command {
    private static final String COMMAND = ":h?";

    public SearchHistory(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " <term> - searches the history", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector result) {
        String searchTerm = parseStringCommand(expression).second().getOrElse("");
        result.logInfo(listValues("History search for '" + searchTerm + "'", ShowHistory.history(evaluator()).filter(contains(searchTerm))));
    }
}
