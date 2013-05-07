package javarepl.console.commands;

import javarepl.console.Console;
import jline.console.completer.StringsCompleter;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import static com.googlecode.totallylazy.Streams.copyAndClose;
import static com.googlecode.totallylazy.Strings.startsWith;
import static java.lang.String.format;
import static javarepl.Utils.*;

public final class AddToClasspath extends Command {
    private static final String COMMAND = ":cp";

    public AddToClasspath() {
        super(COMMAND + " <path> - includes given file or directory in the classpath", startsWith(COMMAND), new StringsCompleter(COMMAND));
    }

    public void execute(Console console, String expression) {
        String path = parseStringCommand(expression).second().getOrNull();
        try {
            URL url = resolveClasspath(path);

            if (isWebUrl(url)) {
                console.logger().info(format("Downloading %s...", path));

                File outputFile = new File(console.evaluator().outputDirectory(), randomIdentifier("external"));
                copyAndClose(url.openStream(), new FileOutputStream(outputFile));

                console.evaluator().addClasspathUrl(outputFile.toURI().toURL());
            } else {
                console.evaluator().addClasspathUrl(url);
            }

            console.logger().info(format("Added %s to classpath.", path));
        } catch (Exception e) {
            console.logger().error(format("Could not add %s to classpath. %s", path, e.getLocalizedMessage()));
        }
    }
}
