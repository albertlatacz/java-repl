package javarepl.client;

import com.googlecode.funclate.Model;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;

import static com.googlecode.funclate.Model.persistent.parse;
import static com.googlecode.utterlyidle.RequestBuilder.get;

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

    private String url(String path) {
        return "http://" + hostname + ":" + port + "/" + path;
    }

}
