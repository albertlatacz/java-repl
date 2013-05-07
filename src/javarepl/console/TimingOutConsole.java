package javarepl.console;

import com.googlecode.totallylazy.Option;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

public class TimingOutConsole extends DelegatingConsole {
    public static final int EXPRESSION_TIMEOUT = 125;
    public static final int INACTIVITY_TIMEOUT = 126;

    private final ScheduledExecutorService scheduler = newScheduledThreadPool(1);
    private final Option<Integer> expressionTimeout;
    private final Option<Integer> inactivityTimeout;
    private ScheduledFuture<Object> inactivityFuture;

    public TimingOutConsole(Console console, Option<Integer> expressionTimeout, Option<Integer> inactivityTimeout) {
        super(console);
        this.expressionTimeout = expressionTimeout;
        this.inactivityTimeout = inactivityTimeout;

        scheduleInactivityTimeout();
    }

    public ConsoleResult execute(final String expression) {
        scheduleInactivityTimeout();

        ScheduledFuture<Object> timedOut = null;
        if (!expressionTimeout.isEmpty()) {
            timedOut = scheduler.schedule(exitWithCode(EXPRESSION_TIMEOUT), expressionTimeout.get(), SECONDS);
        }

        ConsoleResult result = super.execute(expression);

        if (!expressionTimeout.isEmpty()) {
            timedOut.cancel(true);
        }
        return result;
    }

    private void scheduleInactivityTimeout() {
        if (!inactivityTimeout.isEmpty()) {
            if (inactivityFuture != null) {
                inactivityFuture.cancel(true);
            }

            inactivityFuture = scheduler.schedule(exitWithCode(INACTIVITY_TIMEOUT), inactivityTimeout.get(), SECONDS);
        }
    }

    private Callable<Object> exitWithCode(final int code) {
        return new Callable<Object>() {
            public Object call() throws Exception {
                System.exit(code);
                return null;
            }
        };
    }

}
