package javarepl.console.rest;

import com.googlecode.funclate.Model;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Status;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;
import javarepl.console.ConsoleConfig;
import javarepl.console.SimpleConsole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.googlecode.funclate.Model.persistent.model;
import static com.googlecode.funclate.Model.persistent.parse;
import static com.googlecode.utterlyidle.RequestBuilder.get;
import static com.googlecode.utterlyidle.RequestBuilder.post;
import static java.util.Arrays.asList;
import static javarepl.Utils.randomServerPort;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class RestConsoleTest {
    private RestConsole console;
    private ClientHttpHandler client;
    private String prefixUrl;

    @Before
    public void setUp() throws Exception {
        console = new RestConsole(new SimpleConsole(ConsoleConfig.consoleConfig()), randomServerPort());
        client = new ClientHttpHandler();
        prefixUrl = "http://localhost:" + console.port();
    }

    @After
    public void tearDown() throws Exception {
        console.shutdown();
    }

    @Test
    public void shouldReturnResultForGivenExpression() throws Exception {
        Response response = client.handle(post(url("execute")).form("expression", "life = 42").build());

        assertThat(response.status(), is(Status.OK));
        assertThat(body(response),
                is(model()
                        .add("expression", "life = 42")
                        .add("logs", asList(model().add("message", "Integer life = 42").add("type", "SUCCESS")))));
    }

    @Test
    public void shouldReturnTemplate() throws Exception {
        Response response = client.handle(get(url("template")).query("expression", "life = 42").build());

        assertThat(response.status(), is(Status.OK));
        assertThat(body(response).get("template", String.class),
                containsString(body(response).get("token", String.class)));
    }

    private String url(String url) {
        return prefixUrl + "/" + url;
    }

    private Model body(Response response) {
        return parse(response.entity().toString());
    }
}
