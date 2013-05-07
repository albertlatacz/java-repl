package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static javarepl.console.ConsoleLog.Type.ERROR;
import static javarepl.console.ConsoleLog.Type.INFO;

public final class ConsoleLogger {
    private Sequence<ConsoleLog> logs = Sequences.empty();
    private final PrintStream infoStream;
    private final PrintStream errorStream;

    public ConsoleLogger(PrintStream infoStream, PrintStream errorStream) {
        this.infoStream = infoStream;
        this.errorStream = errorStream;
    }

    public ConsoleLogger() {
        infoStream = voidOutputStream();
        errorStream = voidOutputStream();
    }

    public final void info(String message) {
        log(new ConsoleLog(INFO, message));
    }

    public final void error(String message) {
        log(new ConsoleLog(ERROR, message));
    }

    public final void log(ConsoleLog log) {
        switch (log.type()) {
            case INFO:
                infoStream.println(log.message());
                break;
            case ERROR:
                errorStream.println(log.message());
                break;
        }

        logs = logs.cons(log);
    }

    public final Sequence<ConsoleLog> logs() {
        return logs;
    }

    public final void reset() {
        logs = Sequences.empty();
    }

    private PrintStream voidOutputStream() {
        return new PrintStream(new OutputStream() {
            public void write(int b) throws IOException {
            }
        });
    }
}
