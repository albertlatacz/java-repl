package javarepl;

import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import static javarepl.ConsoleLogger.LogType.INFO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;


public class ConsoleTest {
    @Test
    public void supportsListingHistoryOfEvaluations() {
        assertThat(
                executing("42", "\"test\"", ":hist"),
                hasLogged(INFO, "History:\n" +
                        "    1  42\n" +
                        "    2  \"test\"\n"));
    }

    @Test
    public void supportsSearchingHistory() {
        assertThat(
                executing("42", "\"test 1\"", "\"test 2\"", ":h? test"),
                hasLoggedInfo("History search for 'test':\n" +
                        "    2  \"test 1\"\n" +
                        "    3  \"test 2\"\n"));
    }

    @Test
    public void supportsEvaluatingFromHistory() {
        assertThat(
                executing("42", "\"test\"", ":h! 1", ":hist"),
                hasLoggedInfo("History:\n" +
                        "    1  42\n" +
                        "    2  \"test\"\n" +
                        "    3  42\n"));
    }


    private static MockConsoleLogger executing(String... items) {
        MockConsoleLogger logger = new MockConsoleLogger();
        Console console = new Console(logger);

        for (String item : items) {
            console.execute(item);
        }

        return logger;
    }

    private static Matcher<MockConsoleLogger> hasLoggedInfo(final String message) {
        return hasLogged(INFO, message);
    }

    private static Matcher<MockConsoleLogger> hasLogged(final ConsoleLogger.LogType logType, final String message) {
        return new FeatureMatcher<MockConsoleLogger, Iterable<Pair<ConsoleLogger.LogType, String>>>(hasItem(Pair.pair(logType, message)), "", "") {
            @Override
            protected Iterable<Pair<ConsoleLogger.LogType, String>> featureValueOf(MockConsoleLogger mockConsoleLogger) {
                return mockConsoleLogger.logs;
            }
        };
    }

    private static class MockConsoleLogger extends ConsoleLogger {
        private Sequence<Pair<LogType, String>> logs = Sequences.empty();

        public Void call(LogType logType, String message) throws Exception {
            logs = logs.add(Pair.pair(logType, message));
            return null;
        }
    }
}
