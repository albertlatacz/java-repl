package javarepl.console.rest;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.functions.Function1;
import com.googlecode.utterlyidle.MediaType;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.annotations.*;
import javarepl.console.ConsoleLog;
import javarepl.console.ConsoleResult;
import javarepl.rendering.ExpressionTemplate;

import java.util.Map;

import static com.googlecode.totallylazy.collections.PersistentMap.constructors.emptyMap;
import static com.googlecode.utterlyidle.Response.seeOther;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static javarepl.Utils.applicationVersion;
import static javarepl.completion.CompletionResult.methods.toJson;

public class RestConsoleResource {
    private final RestConsole console;
    private final RestConsoleExpressionReader expressionReader;

    public RestConsoleResource(RestConsole console, RestConsoleExpressionReader expressionReader) {
        this.console = console;
        this.expressionReader = expressionReader;
    }

    @GET
    @Path("")
    public Response main() {
        return seeOther("/ui/console.html");
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> create() throws Exception {
        return emptyMap(String.class, Object.class)
                .insert("id", this.hashCode()+"")
                .insert("welcomeMessage", welcomeMessage()+"\n"+welcomeInstructions());
    }

    @GET
    @Path("version")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> version() {
        return emptyMap(String.class, Object.class)
                .insert("version", applicationVersion());
    }


    @GET
    @Path("status")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> status() {
        return emptyMap(String.class, Object.class)
                .insert("status", console.status());
    }

    @POST
    @Path("execute")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> execute(@FormParam("expression") String expr) {
        ConsoleResult result = console.execute(expr);

        return emptyMap(String.class, Object.class)
                .insert("expression", result.expression())
                .insert("logs", result.logs().map(toCommandResultMap()));
    }

    @POST
    @Path("readExpression")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> readExpression(@FormParam("line") String line) {
        Option<String> expression = expressionReader.readExpression(line);
        return expression.map(expression1 ->
                emptyMap(String.class, Object.class).insert("expression", expression1)).
                getOrElse(emptyMap(String.class, Object.class));
    }

    @GET
    @Path("template")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> template(@QueryParam("expression") String expr) {
        ExpressionTemplate template = console.template(expr);
        return emptyMap(String.class, Object.class)
                .insert("template", template.template())
                .insert("token", template.token());
    }

    @GET
    @Path("completions")
    @Produces(MediaType.APPLICATION_JSON)
    public String completions(@QueryParam("expression") String expr) {
        return toJson(console.completion(expr));
    }

    @GET
    @Path("history")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> history() {
        return emptyMap(String.class, Object.class)
                .insert("history", console.history().items().toList());
    }

    private static Function1<ConsoleLog, Map<String, Object>> toCommandResultMap() {
        return consoleLog -> emptyMap(String.class, Object.class)
                .insert("type", consoleLog.type())
                .insert("message", consoleLog.message());
    }

    private String welcomeMessage() {
        return format("Welcome to JavaREPL version %s (%s, Java %s)",
                applicationVersion(),
                getProperty("java.vm.name"),
                getProperty("java.version"));
    }

    private String welcomeInstructions() {
        return "Type expression to evaluate, :help for more options or press tab to auto-complete.";
    }
}
