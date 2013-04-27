package javarepl.console;

import javarepl.console.commands.CommandResult;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static javarepl.console.ConsoleLog.Type;
import static javarepl.console.ConsoleLog.Type.ERROR;
import static javarepl.console.ConsoleLog.Type.INFO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;


public class SimpleConsoleTest {
    @Test
    public void supportsDisplayingSingleResult() {
        assertThat(
                executing("42", "\"test\"", ":result res1"),
                hasLogged(info("java.lang.String res1 = \"test\"")));
    }

    @Test
    public void supportsDisplayingTypeOfExpression() {
        assertThat(
                executing(":type new Date()"),
                hasLogged(info("java.util.Date")));
    }

    @Test
    public void supportsListingOfResults() {
        assertThat(
                executing("import java.io.*", "42", "\"test\"", ":list results"),
                hasLogged(info("Results:\n" +
                        "    Integer res0 = 42\n" +
                        "    String res1 = \"test\"\n")));
    }

    @Test
    public void supportsListingOfImports() {
        assertThat(
                executing("import java.io.*", "\"test\"", "import java.net.*", ":list imports"),
                hasLogged(info("Imports:\n" +
                        "    import java.io.*\n" +
                        "    import java.net.*\n")));
    }

    @Test
    public void supportsListingOfTypes() {
        assertThat(
                executing("class AClass{}", "\"test\"", "interface AnInterface{}", ":list types"),
                hasLogged(info("Types:\n" +
                        "    AClass\n" +
                        "    AnInterface\n")));
    }

    @Test
    public void supportsListingOfMethods() {
        assertThat(
                executing("void method1(){}", "\"test\"", "int method2(int param){return param;}", ":list methods"),
                hasLogged(info("Methods:\n" +
                        "    void method1()\n" +
                        "    int method2()\n")));
    }

    @Test
    public void supportsListingOfAllValues() {
        assertThat(
                executing("void method1(){}", "\"test\"", "import java.net.*", "class AClass{}", ":list all"),
                hasLogged(
                        info("Results:\n" +
                                "    String res0 = \"test\"\n"),
                        info("Types:\n" +
                                "    AClass\n"),
                        info("Imports:\n" +
                                "    import java.net.*\n"),
                        info("Methods:\n" +
                                "    void method1()\n")));
    }

    @Test
    public void supportsListingHistoryOfEvaluations() {
        assertThat(
                executing(":hist"),
                hasLogged(info("No history.")));

        assertThat(
                executing("42", "\"test\"", ":hist"),
                hasLogged(info("History:\n" +
                        "    1  42\n" +
                        "    2  \"test\"\n")));

        assertThat(
                executing("42", "21", "\"test\"", ":hist 2"),
                hasLogged(info("History:\n" +
                        "    2  21\n" +
                        "    3  \"test\"\n")));
    }


    @Test
    public void supportsSearchingHistory() {

        assertThat(
                executing("42", "\"test 1\"", "\"test 2\"", ":h?"),
                hasLogged(info("History search for '':\n" +
                        "    1  42\n" +
                        "    2  \"test 1\"\n" +
                        "    3  \"test 2\"\n")));

        assertThat(
                executing("42", "\"test 1\"", "\"test 2\"", ":h? test"),
                hasLogged(info("History search for 'test':\n" +
                        "    2  \"test 1\"\n" +
                        "    3  \"test 2\"\n")));
    }

    @Test
    public void supportsEvaluatingFromHistory() {
        assertThat(
                executing("42", "\"test\"", ":h!", ":hist"),
                hasLogged(info("History:\n" +
                        "    1  42\n" +
                        "    2  \"test\"\n" +
                        "    3  \"test\"\n")));

        assertThat(
                executing("42", "\"test\"", ":h! 1", ":hist"),
                hasLogged(info("History:\n" +
                        "    1  42\n" +
                        "    2  \"test\"\n" +
                        "    3  42\n")));
    }

    @Test
    public void supportsResettingAllEvaluations() {
        assertThat(
                executing("42", "\"test\"", ":reset", ":hist"),
                hasLogged(info("No history.")));
    }

    public static int reevaluationCounter;

    @Test
    public void supportsReevaluatingAllExpression() {
        reevaluationCounter = 0;
        assertThat(
                executing("++javarepl.console.SimpleConsoleTest.reevaluationCounter", ":replay", ":result res0"),
                hasLogged(info("java.lang.Integer res0 = 2")));
    }


    private static CommandResult executing(String... items) {
        ConsoleLogger logger = new ConsoleLogger();
        System.setOut(new ConsoleLogOutputStream(INFO, logger, System.out));
        System.setErr(new ConsoleLogOutputStream(ERROR, logger, System.err));
        SimpleConsole console = new SimpleConsole(logger);

        CommandResult result = null;
        for (String item : items) {
            result = console.execute(item);
        }

        return result;
    }

    private static ConsoleLog info(String messages) {
        return new ConsoleLog(Type.INFO, messages);
    }

    private static Matcher<CommandResult> hasLogged(final ConsoleLog... logs) {
        return new FeatureMatcher<CommandResult, List<ConsoleLog>>(hasItems(logs), "console log", "console log") {
            protected List<ConsoleLog> featureValueOf(CommandResult commandResult) {
                return commandResult.logs();
            }
        };
    }
}
