package javarepl.client;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;
import javarepl.completion.CompletionResult;

import static com.googlecode.funclate.Model.persistent.parse;
import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.utterlyidle.RequestBuilder.get;
import static com.googlecode.utterlyidle.RequestBuilder.post;
import static javarepl.client.EvaluationLog.Type;

public final class JavaREPLClient {
    private final String hostname;
    private final Integer port;
    private final ClientHttpHandler client;

    public JavaREPLClient(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
        this.client = new ClientHttpHandler(5000);
    }

    public synchronized ExpressionTemplate template(String expression) throws Exception {
        Model model = parse(client.handle(get(url("template")).query("expression", expression).build()).entity().toString());
        return new ExpressionTemplate(model.get("template", String.class), model.get("token", String.class));
    }


    public synchronized Option<EvaluationResult> execute(String expr) throws Exception {
        String json = client.handle(post(url("execute")).form("expression", expr).build()).entity().toString();

        if (json.isEmpty())
            return none();

        Model model = parse(json);
        Sequence<Model> logs = sequence(model.getValues("logs", Model.class));
        String expression = model.get("expression", String.class);

        return some(new EvaluationResult(expression, logs.map(modelToEvaluationLog())));
    }

    public synchronized CompletionResult completions(String expr) throws Exception {
        Model model = parse(client.handle(get(url("completions")).query("expression", expr).build()).entity().toString());
        return new CompletionResult(model.get("expression", String.class),
                Integer.valueOf(model.get("position", String.class)),
                sequence(model.getValues("candidates", String.class)));
    }

    public synchronized boolean isAlive() {
        try {
            return parse(client.handle(get(url("status")).build()).entity().toString()).get("isAlive", Boolean.class);
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized String version() {
        try {
            return parse(client.handle(get(url("version")).build()).entity().toString()).get("version", String.class);
        } catch (Exception e) {
            return "[unknown]";
        }
    }

    public synchronized Sequence<String> history() throws Exception {
        Response history = client.handle(get(url("history")).build());
        Model model = parse(history.entity().toString());
        return sequence(model.getValues("history", String.class));
    }

    private Mapper<Model, EvaluationLog> modelToEvaluationLog() {
        return new Mapper<Model, EvaluationLog>() {
            public EvaluationLog call(Model model) throws Exception {
                return new EvaluationLog(Type.valueOf(model.get("type", String.class)), model.get("message", String.class));
            }
        };
    }

    private String url(String path) {
        return "http://" + hostname + ":" + port + "/" + path;
    }
}
