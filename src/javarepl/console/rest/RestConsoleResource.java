package javarepl.console.rest;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.utterlyidle.MediaType;
import com.googlecode.utterlyidle.annotations.*;
import javarepl.console.ConsoleLog;
import javarepl.console.ConsoleResult;
import javarepl.rendering.ExpressionTemplate;

import static com.googlecode.funclate.Model.persistent.model;
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
    @Path("version")
    @Produces(MediaType.APPLICATION_JSON)
    public Model version() {
        return model()
                .add("version", applicationVersion());
    }


    @GET
    @Path("status")
    @Produces(MediaType.APPLICATION_JSON)
    public Model status() {
        return model()
                .add("status", console.status());
    }

    @POST
    @Path("execute")
    @Produces(MediaType.APPLICATION_JSON)
    public Model execute(@FormParam("expression") String expr) {
        ConsoleResult result = console.execute(expr);

        return model()
                .add("expression", result.expression())
                .add("logs", result.logs().map(commandResultToModel()));
    }

    @POST
    @Path("readExpression")
    @Produces(MediaType.APPLICATION_JSON)
    public Model readExpression(@FormParam("line") String line) {
        Option<String> expression = expressionReader.readExpression(line);
        return expression.map(new Mapper<String, Model>() {
            public Model call(String expression) throws Exception {
                return model().add("expression", expression);
            }
        }).getOrElse(model());
    }

    @GET
    @Path("template")
    @Produces(MediaType.APPLICATION_JSON)
    public Model template(@QueryParam("expression") String expr) {
        ExpressionTemplate template = console.template(expr);
        return model()
                .add("template", template.template())
                .add("token", template.token());
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
    public Model history() {
        return model().add("history", console.history().items().toList());
    }

    private static Mapper<ConsoleLog, Model> commandResultToModel() {
        return new Mapper<ConsoleLog, Model>() {
            public Model call(ConsoleLog consoleLog) throws Exception {
                return model().add("type", consoleLog.type())
                        .add("message", consoleLog.message());
            }
        };
    }
}
