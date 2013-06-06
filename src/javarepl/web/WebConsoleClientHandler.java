package javarepl.web;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.utterlyidle.RequestBuilder;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Status;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;
import javarepl.Repl;

import java.util.UUID;

import static com.googlecode.funclate.Model.persistent.parse;
import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.utterlyidle.RequestBuilder.get;
import static com.googlecode.utterlyidle.Responses.response;
import static com.googlecode.utterlyidle.Status.GATEWAY_TIMEOUT;
import static com.googlecode.utterlyidle.Status.INTERNAL_SERVER_ERROR;
import static javarepl.Utils.randomServerPort;
import static javarepl.console.TimingOutConsole.EXPRESSION_TIMEOUT;
import static javarepl.console.TimingOutConsole.INACTIVITY_TIMEOUT;

public final class WebConsoleClientHandler {
    private final String id = UUID.randomUUID().toString();
    private Option<Integer> port = none();
    private Option<Process> process = none();


    public String id() {
        return id;
    }

    public Option<Integer> port() {
        return port;
    }

    private void createProcess() {
        if (port.isEmpty()) {
            try {
                port = some(randomServerPort());
                ProcessBuilder builder = new ProcessBuilder("java", "-Xmx128M", "-cp", System.getProperty("java.class.path"), Repl.class.getCanonicalName(),
                        "--sandboxed", "--ignoreConsole", "--port=" + port.get(), "--expressionTimeout=5", "--inactivityTimeout=300");
                builder.redirectErrorStream(true);
                process = some(builder.start());

                waitUntilProcessStarted();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isAlive() {
        try {
            return parse(new ClientHttpHandler().handle(get("http://localhost:" + port.get() + "/status").build()).entity().toString())
                    .get("isAlive", Boolean.class);

        } catch (Exception e) {
            return false;
        }
    }

    private boolean waitUntilProcessStarted() throws Exception {
        for (int i = 0; i < 50; i++) {
            Thread.sleep(100);
            if (isAlive())
                return true;
        }

        return false;
    }

    public void shutdown() {
        if (!process.isEmpty()) {
            process.get().destroy();
            process = none();
        }
    }

    public Response execute(String expression) {
        createProcess();

        try {
            return reportProcessError(new ClientHttpHandler().handle(RequestBuilder.post("http://localhost:" + port.get() + "/" + "execute").form("expression", expression).build()));
        } catch (Exception e) {
            e.printStackTrace();
            return response(INTERNAL_SERVER_ERROR);
        }
    }

    public Response readExpression(String line) {
        createProcess();

        try {
            return reportProcessError(new ClientHttpHandler().handle(RequestBuilder.post("http://localhost:" + port.get() + "/" + "readExpression").form("line", line).build()));
        } catch (Exception e) {
            e.printStackTrace();
            return response(INTERNAL_SERVER_ERROR);
        }
    }

    public Response completions(String expression) {
        createProcess();

        try {
            return reportProcessError(new ClientHttpHandler().handle(RequestBuilder.get("http://localhost:" + port.get() + "/" + "completions").query("expression", expression).build()));
        } catch (Exception e) {
            e.printStackTrace();
            return response(INTERNAL_SERVER_ERROR);
        }
    }

    private Response reportProcessError(final Response response) {
        if (response.status() == Status.OK)
            return response;

        return exitCode().map(new Mapper<Integer, Response>() {
            public Response call(Integer code) throws Exception {
                switch (code) {
                    case EXPRESSION_TIMEOUT:
                    case INACTIVITY_TIMEOUT: {
                        shutdown();
                        return response(GATEWAY_TIMEOUT);
                    }
                    default:
                        return response;
                }
            }
        }).getOrElse(response);
    }

    public Option<Integer> exitCode() {
        if (process.isEmpty())
            return none();

        try {
            Thread.sleep(100);
            return some(process.get().exitValue());
        } catch (Exception e) {
            return none();
        }
    }
}
