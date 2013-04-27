package javarepl.console;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static com.googlecode.totallylazy.Predicates.not;
import static com.googlecode.totallylazy.Strings.startsWith;

public class ConsoleLogOutputStream extends PrintStream {
    private final ConsoleLog.Type type;
    private final ConsoleLogger logger;
    private final PrintStream printStream;

    @Override
    public void flush() {
        printStream.flush();
    }

    public ConsoleLogOutputStream(ConsoleLog.Type type, ConsoleLogger logger, PrintStream printStream) {
        super(new OutputStream() {
            public void write(int b) throws IOException {
            }
        });
        this.type = type;
        this.logger = logger;
        this.printStream = printStream;
    }

    @Override
    public void close() {
        printStream.close();
    }

    @Override
    public boolean checkError() {
        return printStream.checkError();
    }

    private void logMessage(String message) {
        if (not(startsWith("POST /")
                .or(startsWith("GET /"))
                .or(startsWith("Listening on http://")))
                .matches(message))
            logger.log(new ConsoleLog(type, message));
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        logMessage(new String(buf, off, len));
        printStream.write(buf, off, len);
    }

    @Override
    public void print(boolean b) {
        logMessage(String.valueOf(b));
        printStream.print(b);
    }

    @Override
    public void print(char c) {
        logMessage(String.valueOf(c));
        printStream.print(c);
    }

    @Override
    public void print(int i) {
        logMessage(String.valueOf(i));
        printStream.print(i);
    }

    @Override
    public void print(long l) {
        logMessage(String.valueOf(l));
        printStream.print(l);
    }

    @Override
    public void print(float f) {
        logMessage(String.valueOf(f));
        printStream.print(f);
    }

    @Override
    public void print(double d) {
        logMessage(String.valueOf(d));
        printStream.print(d);
    }

    @Override
    public void print(char[] s) {
        logMessage(String.valueOf(s));
        printStream.print(s);
    }

    @Override
    public void print(String s) {
        logMessage(s);
        printStream.print(s);
    }

    @Override
    public void print(Object obj) {
        logMessage(String.valueOf(obj));
        printStream.print(obj);
    }

    @Override
    public void println() {
        logMessage("");
        printStream.println();
    }

    @Override
    public void println(boolean x) {
        logMessage(String.valueOf(x));
        printStream.println(x);
    }

    @Override
    public void println(char x) {
        logMessage(String.valueOf(x));
        printStream.println(x);
    }

    @Override
    public void println(int x) {
        logMessage(String.valueOf(x));
        printStream.println(x);
    }

    @Override
    public void println(long x) {
        logMessage(String.valueOf(x));
        printStream.println(x);
    }

    @Override
    public void println(float x) {
        logMessage(String.valueOf(x));
        printStream.println(x);
    }

    @Override
    public void println(double x) {
        logMessage(String.valueOf(x));
        printStream.println(x);
    }

    @Override
    public void println(char[] x) {
        logMessage(String.valueOf(x));
        printStream.println(x);
    }

    @Override
    public void println(String x) {
        logMessage(String.valueOf(x));
        printStream.println(x);
    }

    @Override
    public void println(Object x) {
        printStream.println(x);
    }

    @Override
    public PrintStream printf(String format, Object... args) {
        logMessage(String.format(format, args));
        return printStream.printf(format, args);
    }

    @Override
    public PrintStream printf(Locale l, String format, Object... args) {
        logMessage(String.format(l, format, args));
        return printStream.printf(l, format, args);
    }

    @Override
    public PrintStream format(String format, Object... args) {
        logMessage(String.format(format, args));
        return printStream.format(format, args);
    }

    @Override
    public PrintStream format(Locale l, String format, Object... args) {
        logMessage(String.format(l, format, args));
        return printStream.format(l, format, args);
    }

    @Override
    public PrintStream append(CharSequence csq) {
        logMessage(String.valueOf(csq));
        return printStream.append(csq);
    }

    @Override
    public PrintStream append(CharSequence csq, int start, int end) {
//            logMessage(String.valueOf(csq., start, end));
        return printStream.append(csq, start, end);
    }

    @Override
    public PrintStream append(char c) {
        return printStream.append(c);
    }

    @Override
    public void write(int b) {
        printStream.write(b);
    }
}
