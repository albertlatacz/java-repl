package javarepl.console.commands;

import com.googlecode.totallylazy.*;
import javarepl.Evaluator;
import javarepl.console.ConsoleLog;
import javarepl.console.ConsoleLogger;
import jline.console.completer.Completer;

import java.util.List;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.Integer.parseInt;
import static javarepl.console.ConsoleLogger.LogType.ERROR;
import static javarepl.console.ConsoleLogger.LogType.INFO;

public abstract class Command extends Function2<Evaluator, String, CommandResult> implements Predicate<String>, Completer {


    public static final String COMMAND_SEPARATOR = " ";

    private final String description;
    private final Predicate<String> predicate;
    private final Completer completer;

    private final ConsoleLogger logger;

    public final boolean matches(String expression) {
        return predicate.matches(expression);
    }

    protected Command(String description, Predicate<String> predicate, Completer completer, ConsoleLogger logger) {
        this.description = description;
        this.predicate = predicate;
        this.completer = completer;
        this.logger = logger;
    }

    public final int complete(String buffer, int index, List<CharSequence> candidates) {
        return (completer != null) ? completer.complete(buffer, index, candidates) : -1;
    }

    @Override
    public final String toString() {
        return description;
    }

    public final CommandResultCollector createResultCollector(String command) {
        return new CommandResultCollector(logger, command);
    }

    public static final Pair<String, Option<String>> parseStringCommand(String input) {
        final Sequence<String> splitInput = sequence(input.split(COMMAND_SEPARATOR));
        String command = splitInput.first();
        String value = splitInput.tail().toString(COMMAND_SEPARATOR);
        return Pair.pair(command, value.isEmpty() ? Option.none(String.class) : some(value));
    }

    public static final Pair<String, Option<Integer>> parseNumericCommand(String input) {
        final Sequence<String> splitInput = sequence(input.split(COMMAND_SEPARATOR));

        try {
            return Pair.pair(splitInput.first(), some(parseInt(splitInput.tail().toString(COMMAND_SEPARATOR))));
        } catch (Exception e) {
            return Pair.pair(splitInput.first(), none(Integer.class));
        }
    }

    public static final class CommandResultCollector {
        private final ConsoleLogger logger;
        private final String command;
        private Sequence<ConsoleLog> logs = empty();

        private CommandResultCollector(ConsoleLogger logger, String command) {
            this.logger = logger;
            this.command = command;
        }

        public CommandResultCollector logError(String message) {
            logger.logError(message);
            logs = logs.add(new ConsoleLog(ERROR, message));
            return this;
        }

        public CommandResultCollector logInfo(String message) {
            logger.logInfo(message);
            logs = logs.add(new ConsoleLog(INFO, message));
            return this;
        }

        public CommandResult result() {
            return new CommandResult(command, logs);
        }

    }
}
