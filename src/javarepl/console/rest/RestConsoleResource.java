package javarepl.console.rest;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.utterlyidle.MediaType;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Responses;
import com.googlecode.utterlyidle.annotations.*;
import javarepl.Evaluator;
import javarepl.completion.Completer;
import javarepl.completion.CompletionResult;
import javarepl.console.ConsoleConfig;
import javarepl.console.ConsoleHistory;
import javarepl.console.ConsoleLog;
import javarepl.console.ConsoleResult;
import javarepl.expressions.Expression;

import java.util.Map;

import static com.googlecode.funclate.Model.persistent.model;
import static javarepl.Utils.applicationVersion;
import static javarepl.Utils.randomIdentifier;
import static javarepl.console.ConsoleResult.emptyResult;
import static javarepl.rendering.EvaluationClassRenderer.renderExpressionClass;
import static javarepl.rendering.ExpressionTokenRenderer.EXPRESSION_TOKEN;

public class RestConsoleResource {
    private final RestConsole console;
    private final RestConsoleExpressionReader expressionReader;

    public RestConsoleResource(RestConsole console, RestConsoleExpressionReader expressionReader) {
        this.console = console;
        this.expressionReader = expressionReader;
    }

    @GET
    @Path("config")
    @Produces(MediaType.APPLICATION_JSON)
    public Model config() {
        ConsoleConfig consoleConfig = console.context().get(ConsoleConfig.class);
        return model()
                .add("version", applicationVersion())
                .add("sandboxed", consoleConfig.sandboxed)
                .add("systemProperties", model((Map) System.getProperties()));
    }


    @GET
    @Path("status")
    @Produces(MediaType.APPLICATION_JSON)
    public Model status() {
        return model()
                .add("isAlive", true);
    }

    @POST
    @Path("execute")
    @Produces(MediaType.APPLICATION_JSON)
    public Model execute(@FormParam("expression") String expr) {
        Option<String> expression = expressionReader.readExpression(expr);
        ConsoleResult result = expression.isEmpty() ? emptyResult() : console.execute(expression.get());

        return model()
                .add("expression", result.expression())
                .add("logs", result.logs().map(commandResultToModel()));
    }

    @GET
    @Path("template")
    @Produces(MediaType.APPLICATION_JSON)
    public Model template(@QueryParam("expression") String expr) {
        Evaluator evaluator = console.context().get(Evaluator.class);
        Expression expression = evaluator.parseExpression(expr);

        return model()
                .add("template", renderExpressionClass(evaluator.context(), randomIdentifier("Evaluation"), expression))
                .add("token", EXPRESSION_TOKEN);
    }

    @GET
    @Path("completions")
    @Produces(MediaType.APPLICATION_JSON)
    public Model completions(@QueryParam("expression") String expr) {
        CompletionResult result = console.context().get(Completer.class).apply(expr);
        return model()
                .add("expression", result.expression())
                .add("position", result.position().toString())
                .add("candidates", result.candidates().toList());
    }

    @GET
    @Path("history")
    @Produces(MediaType.APPLICATION_JSON)
    public Model history() {
        return model().add("history", console.context().get(ConsoleHistory.class).items().toList());
    }

    @GET
    @Path("")
    public Response main() {
        return Responses.seeOther("console.html");
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
