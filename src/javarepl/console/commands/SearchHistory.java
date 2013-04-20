package javarepl.console.commands;

import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Strings.contains;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.Utils.listValues;

public class SearchHistory extends Command {
    private static final String COMMAND = ":h?";

    public SearchHistory(ConsoleLogger logger) {
        super(COMMAND + " <term> - searches the history", startsWith(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public CommandResult call(Evaluator evaluator, String expression) throws Exception {
        CommandResultCollector resultCollector = createResultCollector(expression);
        String searchTerm = parseStringCommand(expression).second().getOrElse("");
        resultCollector.logInfo(listValues("History search for '" + searchTerm + "'", ShowHistory.history(evaluator).filter(contains(searchTerm))));
        return resultCollector.result();
    }
}
