package javarepl.client;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;
import javarepl.completion.CompletionResult;

import static com.googlecode.funclate.Model.persistent.parse;
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


    public synchronized EvaluationResult execute(String expr) throws Exception {
        Model model = parse(client.handle(post(url("execute")).form("expression", expr).build()).entity().toString());
        Sequence<Model> logs = sequence(model.getValues("logs", Model.class));
        String expression = model.get("expression", String.class);

        return new EvaluationResult(expression, logs.map(modelToEvaluationLog()));
    }

    public synchronized CompletionResult completions(String expr) throws Exception {
        Model model = parse(client.handle(get(url("completions")).query("expression", expr).build()).entity().toString());
        return new CompletionResult(model.get("expression", String.class),
                Integer.valueOf(model.get("position", String.class)),
                sequence(model.getValues("candidates", String.class)));
    }

    public synchronized Sequence<String> history() throws Exception {
        Model model = parse(client.handle(get(url("history")).build()).entity().toString());
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
