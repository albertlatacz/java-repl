package javarepl.console;

import com.googlecode.totallylazy.*;

import java.io.File;


public final class ConsoleHistory {

    private final Sequence<String> history;
    private final Option<File> file;
    private final Predicate<String> ignored;

    public ConsoleHistory(Sequence<String> history, Predicate<String> ignored, Option<File> file) {
        this.ignored = ignored;
        this.history = history.reverse().take(300).reverse();
        this.file = file;
    }

    public static final ConsoleHistory emptyHistory(Predicate<String> ignored, Option<File> file) {
        return new ConsoleHistory(Sequences.<String>empty(), ignored, file);
    }

    public static final ConsoleHistory historyFromFile(Predicate<String> ignored, Option<File> file) {
        if (file.isEmpty())
            return emptyHistory(ignored, file);

        try {
            return new ConsoleHistory(Strings.lines(file.get()), ignored, file);
        } catch (Exception e) {
            return emptyHistory(ignored, file);
        }
    }

    public boolean save() {
        if (file.isEmpty())
            return false;

        try {
            Files.write(history.toString("\n").getBytes(), file.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ConsoleHistory add(String expression) {
        if (expression != null && !ignored.matches(expression))
            return new ConsoleHistory(history.add(expression), ignored, file);
        else
            return this;
    }

    public Sequence<String> items() {
        return history;
    }

}
