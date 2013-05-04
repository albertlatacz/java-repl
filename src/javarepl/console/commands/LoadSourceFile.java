package javarepl.console.commands;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Strings;
import javarepl.console.Console;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Files.asFile;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;

public final class LoadSourceFile extends Command {
    private static final String COMMAND = ":load";

    public LoadSourceFile() {
        super(COMMAND + " <path> - loads source file ", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(Console console, String expression) {
        Option<String> path = parseStringCommand(expression).second();

        if (!path.isEmpty()) {
            try {
                console.evaluator().evaluate(Strings.lines(path.map(asFile()).get()).toString("\n"));
                System.out.println(format("Loaded source file from %s", path.get()));
            } catch (Exception e) {
                System.err.println(format("Could not load source file from %s.\n  %s", path.get(), e.getLocalizedMessage()));
            }
        } else {
            System.err.println(format("Path not specified"));
        }
    }


}
