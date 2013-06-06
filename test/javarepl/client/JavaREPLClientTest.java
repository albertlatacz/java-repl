package javarepl.client;

import javarepl.completion.CompletionResult;
import javarepl.console.ConsoleConfig;
import javarepl.console.SimpleConsole;
import javarepl.console.rest.RestConsole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.one;
import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.Utils.applicationVersion;
import static javarepl.Utils.randomServerPort;
import static javarepl.client.EvaluationLog.Type.SUCCESS;
import static javarepl.rendering.ExpressionTokenRenderer.EXPRESSION_TOKEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class JavaREPLClientTest {

    private RestConsole console;
    private JavaREPLClient client;

    @Before
    public void setUp() throws Exception {
        int port = randomServerPort();
        console = new RestConsole(new SimpleConsole(ConsoleConfig.consoleConfig()), port);
        client = new JavaREPLClient("localhost", port);
    }

    @After
    public void tearDown() throws Exception {
        console.shutdown();
    }

    @Test
    public void returnsTemplateForSpecifiedExpression() throws Exception {
        ExpressionTemplate template = client.template("void method(){}");

        assertThat(template.token(), is(EXPRESSION_TOKEN));
        assertThat(template.template(), containsString(template.token()));
    }

    @Test
    public void returnsResultAfterExecuting() throws Exception {
        EvaluationResult result = client.execute("life = 42").get();

        assertThat(result.expression(), is("life = 42"));
        assertThat(result.logs().get(0).message(), is("Integer life = 42"));
        assertThat(result.logs().get(0).type(), is(SUCCESS));
    }

    @Test
    public void returnsCompletions() throws Exception {
        client.execute("life = 42");
        CompletionResult result = client.completions("li");

        assertThat(result.expression(), is("li"));
        assertThat(result.position(), is(0));
        assertThat(result.candidates(), is(one("life")));
    }


    @Test
    public void returnsHistory() throws Exception {
        client.execute("life = 42");
        client.execute(":help");

        assertThat(client.history(), is(sequence("life = 42", ":help")));
    }

    @Test
    public void returnsVersion() throws Exception {
        assertThat(client.version(), is(applicationVersion()));
    }

    @Test
    public void checksIfEndpointIsAlive() throws Exception {
        assertThat(client.isAlive(), is(true));
    }
}
