package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Strings;
import javarepl.Evaluator;
import javarepl.console.ConsoleLogger;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Files.asFile;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;

public class LoadSourceFile extends Command {
    private static final String COMMAND = ":load";

    public LoadSourceFile(ConsoleLogger logger) {
        super(COMMAND + " <path> - loads source file ", startsWith(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        Option<String> path = parseStringCommand(expression).second();

        if (!path.isEmpty()) {
            try {
                evaluator.evaluate(Strings.lines(path.map(asFile()).get()).toString("\n"));
                logInfo(format("Loaded source file from %s", path.get()));
            } catch (Exception e) {
                logError(format("Could not load source file from %s.\n  %s", path.get(), e.getLocalizedMessage()));
            }
        } else {
            logError(format("Path not specified"));
        }

        return null;
    }


}
