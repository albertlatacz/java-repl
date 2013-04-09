package javarepl.commands;

import com.googlecode.totallylazy.Function2;
import com.googlecode.totallylazy.Predicate;
import javarepl.Evaluator;
import jline.console.completer.Completer;

import java.util.List;

public abstract class Command extends Function2<Evaluator, String, Void> implements Predicate<String>, Completer {
    private final String description;
    private final Predicate<String> predicate;
    private final Completer completer;

    public final boolean matches(String expression) {
        return predicate.matches(expression);
    }

    protected Command(String description, Predicate<String> predicate, Completer completer) {
        this.description = description;
        this.predicate = predicate;
        this.completer = completer;
    }

    public final int complete(String buffer, int index, List<CharSequence> candidates) {
        return (completer != null) ? completer.complete(buffer, index, candidates) : -1;
    }

    @Override
    public final String toString() {
        return description;
    }
}
