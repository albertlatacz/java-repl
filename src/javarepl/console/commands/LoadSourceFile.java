package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Strings;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Files.asFile;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;

public final class LoadSourceFile extends Command {
    private static final String COMMAND = ":load";

    public LoadSourceFile(ConsoleLogger logger, Evaluator evaluator) {
        super(evaluator, logger, COMMAND + " <path> - loads source file ", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    void execute(String expression, CommandResultCollector result) {
        Option<String> path = parseStringCommand(expression).second();

        if (!path.isEmpty()) {
            try {
                evaluator().evaluate(Strings.lines(path.map(asFile()).get()).toString("\n"));
                result.logInfo(format("Loaded source file from %s", path.get()));
            } catch (Exception e) {
                result.logError(format("Could not load source file from %s.\n  %s", path.get(), e.getLocalizedMessage()));
            }
        } else {
            result.logError(format("Path not specified"));
        }
    }


}
