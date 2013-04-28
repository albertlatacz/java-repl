package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;


public final class ConsoleHistory {
    private final Sequence<String> history;

    public ConsoleHistory(Sequence<String> history) {
        this.history = history;
    }

    public static final ConsoleHistory emptyHistory() {
        return new ConsoleHistory(Sequences.<String>empty());
    }

    public ConsoleHistory add(String expression) {
        if (expression != null && !expression.startsWith(":"))
            return new ConsoleHistory(history.add(expression));
        else
            return this;
    }

    public Sequence<String> items() {
        return history;
    }

}
