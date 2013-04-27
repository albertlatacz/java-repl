package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import java.util.List;

public final class ConsoleLogger {
    private Sequence<ConsoleLog> logs = Sequences.empty();

    public final void log(ConsoleLog log) {
        logs = logs.cons(log);
    }

    public final List<ConsoleLog> logs() {
        return logs.toList();
    }

    public final void reset() {
        logs = Sequences.empty();
    }
}
