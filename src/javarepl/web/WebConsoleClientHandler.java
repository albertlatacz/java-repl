package javarepl.web;

import com.googlecode.utterlyidle.RequestBuilder;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Status;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;
import javarepl.Main;

import java.io.File;
import java.util.UUID;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.utterlyidle.Responses.response;
import static com.googlecode.utterlyidle.Status.GATEWAY_TIMEOUT;
import static com.googlecode.utterlyidle.Status.INTERNAL_SERVER_ERROR;
import static java.io.File.pathSeparator;
import static java.lang.Thread.sleep;
import static java.net.URLDecoder.decode;
import static javarepl.Utils.randomServerPort;
import static javarepl.console.TimingOutConsole.EXPRESSION_TIMEOUT;
import static javarepl.console.TimingOutConsole.INACTIVITY_TIMEOUT;

public class WebConsoleClientHandler {
    private final String id;

    private int port;
    private Process process;

    public WebConsoleClientHandler() {
        this.id = UUID.randomUUID().toString();
    }

    public String id() {
        return id;
    }

    public int port() {
        return port;
    }

    private void createProcess() {
        if (process == null) {
            try {
                File path = new File(decode(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "ISO-8859-1"));
                String classpath = sequence(System.getProperty("java.class.path")).add(path.toURI().toURL().toString()).toString(pathSeparator);
                port = randomServerPort();
                ProcessBuilder builder = new ProcessBuilder("java", "-Xmx96M", "-cp", classpath, Main.class.getCanonicalName(),
                        "--sandboxed", "--ignoreConsole", "--port=" + port, "--expressionTimeout=15", "--inactivityTimeout=300");
                builder.redirectErrorStream(true);
                process = builder.start();

                sleep(1000);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        if (process != null) {
            process.destroy();
            process = null;
        }
    }

    public Response execute(String expression) {
        createProcess();

        try {
            return reportProcessErrors(new ClientHttpHandler().handle(RequestBuilder.post("http://localhost:" + port + "/" + "execute").form("expression", expression).build()));
        } catch (Exception e) {
            return response(INTERNAL_SERVER_ERROR);
        }
    }

    private Response reportProcessErrors(Response response) {
        if (response.status() != Status.OK) {
            try {
                switch (process.exitValue()) {
                    case EXPRESSION_TIMEOUT:
                        return response(GATEWAY_TIMEOUT);
                    case INACTIVITY_TIMEOUT:
                        return response(GATEWAY_TIMEOUT);
                }
            } catch (Exception e) {
            }
        }

        return response;
    }
}
