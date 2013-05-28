package javarepl.client;

import javarepl.console.ConsoleConfig;
import javarepl.console.SimpleConsole;
import javarepl.console.rest.RestConsole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        EvaluationResult result = client.execute("life = 42");

        assertThat(result.expression(), is("life = 42"));
        assertThat(result.logs().get(0).message(), is("Integer life = 42"));
        assertThat(result.logs().get(0).type(), is(SUCCESS));
    }
}
