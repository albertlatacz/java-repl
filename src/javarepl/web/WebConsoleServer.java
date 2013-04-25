package javarepl.web;

import com.googlecode.totallylazy.Option;
import com.googlecode.utterlyidle.ServerConfiguration;
import com.googlecode.utterlyidle.httpserver.RestServer;

import static com.googlecode.totallylazy.Callables.compose;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.replaceAll;
import static com.googlecode.totallylazy.Strings.startsWith;
import static com.googlecode.totallylazy.numbers.Numbers.intValue;
import static com.googlecode.totallylazy.numbers.Numbers.valueOf;
import static com.googlecode.utterlyidle.BasePath.basePath;
import static com.googlecode.utterlyidle.ServerConfiguration.defaultConfiguration;
import static javarepl.Utils.randomServerPort;

public class WebConsoleServer {

    public static void main(String... args) throws Exception {
        ServerConfiguration configuration = defaultConfiguration().port(port(args).getOrElse(randomServerPort()));
        WebConsoleApplication application = new WebConsoleApplication(basePath("/"));
        new RestServer(application, configuration);

    }

    private static Option<Integer> port(String[] args) {
        return sequence(args).find(startsWith("--port=")).map(compose(replaceAll("--port=", ""), compose(valueOf, intValue)));
    }
}
