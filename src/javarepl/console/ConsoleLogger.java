package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

public final class ConsoleLogger {
    private Sequence<ConsoleLog> logs = Sequences.empty();

    public final void log(ConsoleLog log) {
        logs = logs.cons(log);
    }

    public final Sequence<ConsoleLog> logs() {
        return logs;
    }

    public final void reset() {
        logs = Sequences.empty();
    }
}
