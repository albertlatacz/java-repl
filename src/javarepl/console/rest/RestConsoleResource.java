package javarepl.console.rest;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import com.googlecode.utterlyidle.MediaType;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Responses;
import com.googlecode.utterlyidle.annotations.*;
import javarepl.Evaluator;
import javarepl.console.ConsoleLog;
import javarepl.console.ConsoleResult;
import javarepl.expressions.Expression;

import static com.googlecode.funclate.Model.persistent.model;
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

    @POST
    @Path("execute")
    @Produces(MediaType.APPLICATION_JSON)
    public Model execute(@FormParam("expression") String expr) {
        Option<String> expression = expressionReader.readExpression(expr);
        return resultToModel(expression.isEmpty() ? emptyResult() : console.execute(expression.get()));
    }

    @GET
    @Path("template")
    @Produces(MediaType.APPLICATION_JSON)
    public Model template(@QueryParam("expression") String expr) {
        Evaluator evaluator = console.context().get(Evaluator.class);
        Expression expression = evaluator.parseExpression(expr);
        return model().add("template", renderExpressionClass(evaluator.context(), randomIdentifier("Evaluation"), expression))
                .add("token", EXPRESSION_TOKEN);
    }

    @GET
    @Path("")
    public Response main() {
        return Responses.seeOther("console.html");
    }

    public static Model resultToModel(ConsoleResult result) {
        return model()
                .add("expression", result.expression())
                .add("logs", result.logs().map(commandResultToModel()));
    }

    private static Function1<ConsoleLog, Model> commandResultToModel() {
        return new Function1<ConsoleLog, Model>() {
            public Model call(ConsoleLog consoleLog) throws Exception {
                return model().add("type", consoleLog.type())
                        .add("message", consoleLog.message());
            }
        };
    }

}
