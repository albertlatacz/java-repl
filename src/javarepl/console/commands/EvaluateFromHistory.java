package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import javarepl.completion.CommandCompleter;
import javarepl.console.Console;
import javarepl.console.ConsoleHistory;
import javarepl.console.ConsoleLogger;

import static com.googlecode.totallylazy.Strings.startsWith;

public final class EvaluateFromHistory extends Command {
    private static final String COMMAND = ":h!";
    private final Console console;
    private final ConsoleHistory history;
    private final ConsoleLogger logger;

    public EvaluateFromHistory(Console console, ConsoleHistory history, ConsoleLogger logger) {
        super(COMMAND + " num - evaluate expression from history)", startsWith(COMMAND), new CommandCompleter(COMMAND));
        this.console = console;
        this.history = history;
        this.logger = logger;
    }

    public void execute(String expression) {
        Integer historyItem = parseNumericCommand(expression).second().getOrElse(history.items().size());
        Option<String> fromHistory = history.items().drop(historyItem - 1).headOption();

        if (!fromHistory.isEmpty()) {
            console.execute(fromHistory.get());
        } else {
            logger.error("Expression not found.\n");
        }
    }
}
