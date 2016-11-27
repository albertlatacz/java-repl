package javarepl.console.rest;

import com.googlecode.totallylazy.json.Json;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Status;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;
import javarepl.console.ConsoleConfig;
import javarepl.console.SimpleConsole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.googlecode.totallylazy.collections.PersistentMap.constructors.emptyMap;
import static com.googlecode.utterlyidle.RequestBuilder.get;
import static com.googlecode.utterlyidle.RequestBuilder.post;
import static java.util.Arrays.asList;
import static javarepl.Utils.applicationVersion;
import static javarepl.Utils.randomServerPort;
import static javarepl.console.ConsoleStatus.Running;
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
        console.start();

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
                is(emptyMap(String.class, Object.class)
                        .insert("expression", "life = 42")
                        .insert("logs", asList(emptyMap(String.class, Object.class)
                                .insert("message", "java.lang.Integer life = 42")
                                .insert("type", "SUCCESS")))));
    }

    @Test
    public void shouldReturnTemplate() throws Exception {
        Response response = client.handle(get(url("template")).query("expression", "life = 42").build());

        assertThat(response.status(), is(Status.OK));
        assertThat(body(response).get("template").toString(),
                containsString(body(response).get("token").toString()));
    }

    @Test
    public void shouldReturnCompletions() throws Exception {
        client.handle(post(url("execute")).form("expression", "expr_1 = 42").build());
        client.handle(post(url("execute")).form("expression", "expr_2 = 21").build());
        client.handle(post(url("execute")).form("expression", "expr_3 = 7").build());

        Response response = client.handle(get(url("completions")).query("expression", "prefix expr_").build());

        assertThat(response.status(), is(Status.OK));
        assertThat(body(response), is(emptyMap(String.class, Object.class)
                .insert("expression", "prefix expr_")
                .insert("position", "7")
                .insert("candidates",
                        asList(
                                emptyMap(String.class, Object.class).insert("value", "expr_1").insert("forms", asList("expr_1")),
                                emptyMap(String.class, Object.class).insert("value", "expr_2").insert("forms", asList("expr_2")),
                                emptyMap(String.class, Object.class).insert("value", "expr_3").insert("forms", asList("expr_3")))
                )));
    }

    @Test
    public void shouldReturnHistory() throws Exception {
        client.handle(post(url("execute")).form("expression", "life = 42").build());
        client.handle(post(url("execute")).form("expression", ":help").build());

        Response response = client.handle(get(url("history")).build());

        assertThat(response.status(), is(Status.OK));
        assertThat(body(response), is(emptyMap(String.class, Object.class).insert("history", asList("life = 42", ":help"))));
    }


    @Test
    public void shouldReturnCorrectStatus() throws Exception {
        Response response = client.handle(get(url("status")).build());

        assertThat(response.status(), is(Status.OK));
        assertThat(body(response), is(emptyMap(String.class, Object.class)
                .insert("status", Running.toString())));

    }

    @Test
    public void shouldReturnCorrectVersion() throws Exception {
        Response response = client.handle(get(url("version")).build());

        assertThat(response.status(), is(Status.OK));
        assertThat(body(response), is(emptyMap(String.class, Object.class)
                .insert("version", applicationVersion())));

    }

    @Test
    public void shouldReadExpression() throws Exception {
        Response response = client.handle(post(url("readExpression")).form("line", "{").build());
        assertThat(response.status(), is(Status.OK));
        assertThat(body(response), is(emptyMap(String.class, Object.class)));

        response = client.handle(post(url("readExpression")).form("line", "}").build());
        assertThat(response.status(), is(Status.OK));
        assertThat(body(response), is(emptyMap(String.class, Object.class).insert("expression", "{\n}")));

    }

    private String url(String url) {
        return prefixUrl + "/" + url;
    }

    private Map<String, Object> body(Response response) {
        return Json.map(response.entity().toString());
    }
}
