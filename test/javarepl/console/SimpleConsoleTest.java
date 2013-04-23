package javarepl.console;

import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import javarepl.console.commands.CommandResult;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static javarepl.console.ConsoleLog.Type.INFO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;


public class SimpleConsoleTest {
    @Test
    public void supportsListingHistoryOfEvaluations() {
        assertThat(executing(":hist"), hasLoggedInfo("No history."));

        assertThat(
                executing("42", "\"test\"", ":hist"),
                hasLoggedInfo("History:\n" +
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

    @Test
    public void supportsResettingAllEvaluations() {
        assertThat(
                executing("42", "\"test\"", ":reset", ":hist"),
                hasLoggedInfo("No history."));
    }


    private static CommandResult executing(String... items) {
        MockConsoleLogger logger = new MockConsoleLogger();
        SimpleConsole console = new SimpleConsole(logger);

        CommandResult result = null;
        for (String item : items) {
            result = console.execute(item);
        }

        return result;
    }

    private static Matcher<CommandResult> hasLoggedInfo(final String message) {
        return hasLogged(INFO, message);
    }

    private static Matcher<CommandResult> hasLogged(final ConsoleLog.Type logType, final String message) {
        return new FeatureMatcher<CommandResult, List<ConsoleLog>>(contains(new ConsoleLog(logType, message)), "console log", "console log") {
            protected List<ConsoleLog> featureValueOf(CommandResult commandResult) {
                return commandResult.logs();
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
