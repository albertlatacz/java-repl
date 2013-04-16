package javarepl.commands;

import javarepl.ConsoleLogger;
import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import static com.googlecode.totallylazy.Streams.copyAndClose;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;
import static javarepl.Utils.*;

public class AddToClasspath extends Command {
    private static final String COMMAND = ":cp";

    public AddToClasspath(ConsoleLogger logger) {
        super(COMMAND + " <path> - includes given file or directory in the classpath", startsWith(COMMAND), new StringsCompleter(COMMAND), logger);
    }

    public Void call(final Evaluator evaluator, String expression) throws Exception {
        String path = parseStringCommand(expression).second().getOrNull();
        try {
            URL url = resolveClasspath(path);

            if (isWebUrl(url)) {
                logInfo(format("Downloading %s...", path));

                File outputFile = new File(evaluator.outputDirectory(), randomIdentifier("external"));
                copyAndClose(url.openStream(), new FileOutputStream(outputFile));

                evaluator.addClasspathUrl(outputFile.toURI().toURL());
            } else {
                evaluator.addClasspathUrl(url);
            }

            logInfo(format("Added %s to classpath.", path));
        } catch (Exception e) {
            logError(format("Could not add %s to classpath. %s", path, e.getLocalizedMessage()));
        }

        return null;
    }
}
