package javarepl.client;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.functions.Function1;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;
import javarepl.completion.CompletionResult;
import javarepl.console.ConsoleStatus;
import javarepl.rendering.ExpressionTemplate;

import java.util.List;
import java.util.Map;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.json.Json.map;
import static com.googlecode.utterlyidle.RequestBuilder.get;
import static com.googlecode.utterlyidle.RequestBuilder.post;
import static javarepl.client.EvaluationLog.Type;
import static javarepl.completion.CompletionResult.methods.fromJson;
import static javarepl.console.ConsoleStatus.Idle;

public final class JavaREPLClient {
    private final String hostname;
    private final Integer port;
    private final ClientHttpHandler client;

    public JavaREPLClient(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
        this.client = new ClientHttpHandler();
    }

    public synchronized ExpressionTemplate template(String expression) throws Exception {
        Map<String, Object> model = map(client.handle(get(url("template")).query("expression", expression).build()).entity().toString());
        return new ExpressionTemplate(model.get("template").toString(), model.get("token").toString());
    }


    public synchronized Option<EvaluationResult> execute(String expr) throws Exception {
        String json = client.handle(post(url("execute")).form("expression", expr).build()).entity().toString();

        if (json.isEmpty())
            return none();

        Map<String, Object> model = map(json);
        Sequence<Map<String, Object>> logs = sequence((List<Object>)model.get("logs")).unsafeCast();
        String expression = model.get("expression").toString();

        return some(new EvaluationResult(expression, logs.map(modelToEvaluationLog())));
    }

    public synchronized CompletionResult completions(String expr) throws Exception {
        return fromJson(client.handle(get(url("completions")).query("expression", expr).build()).entity().toString());
    }

    public synchronized ConsoleStatus status() {
        try {
            return ConsoleStatus.valueOf(map(client.handle(get(url("status")).build()).entity().toString()).get("status").toString());
        } catch (Exception e) {
            return Idle;
        }
    }

    public synchronized String version() {
        try {
            return map(client.handle(get(url("version")).build()).entity().toString()).get("version").toString();
        } catch (Exception e) {
            return "[unknown]";
        }
    }

    public synchronized Sequence<String> history() throws Exception {
        Response history = client.handle(get(url("history")).build());
        Map<String, Object> model = map(history.entity().toString());
        return sequence((List<Object>)model.get("history")).safeCast(String.class);
    }

    private Function1<Map<String, Object>, EvaluationLog> modelToEvaluationLog() {
        return model -> new EvaluationLog(Type.valueOf(model.get("type").toString()), model.get("message").toString());
    }

    private String url(String path) {
        return "http://" + hostname + ":" + port + "/" + path;
    }
}
