package javarepl.web;

import com.googlecode.utterlyidle.RequestBuilder;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.handlers.ClientHttpHandler;
import javarepl.Main;

import java.io.File;
import java.util.UUID;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.utterlyidle.Responses.response;
import static com.googlecode.utterlyidle.Status.INTERNAL_SERVER_ERROR;
import static java.io.File.pathSeparator;
import static java.lang.Thread.sleep;
import static java.net.URLDecoder.decode;
import static javarepl.Utils.randomServerPort;

public class WebConsoleClientHandler {

    public final String id;
    private Process process;
    public int port;


    public WebConsoleClientHandler() {
        id = UUID.randomUUID().toString();
    }

    private void createProcess() {
        if (process == null) {
            try {
                File path = new File(decode(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "ISO-8859-1"));
                String classpath = sequence(System.getProperty("java.class.path")).add(path.toURI().toURL().toString()).toString(pathSeparator);
                port = randomServerPort();
                ProcessBuilder builder = new ProcessBuilder("java", "-cp", classpath, Main.class.getCanonicalName(), "--sandboxed", "--ignore-console", "--port=" + port);
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
            return new ClientHttpHandler().handle(RequestBuilder.post("http://localhost:" + port + "/" + "execute").form("expression", expression).build());
        } catch (Exception e) {
            return response(INTERNAL_SERVER_ERROR);
        }
    }
}
