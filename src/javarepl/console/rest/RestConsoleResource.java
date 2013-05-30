package javarepl.console.rest;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import com.googlecode.utterlyidle.MediaType;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Responses;
import com.googlecode.utterlyidle.annotations.*;
import javarepl.Evaluator;
import javarepl.completion.CompletionResult;
import javarepl.completion.SimpleConsoleCompleter;
import javarepl.console.ConsoleHistory;
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
    private final SimpleConsoleCompleter completer;

    public RestConsoleResource(RestConsole console, RestConsoleExpressionReader expressionReader, SimpleConsoleCompleter completer) {
        this.console = console;
        this.expressionReader = expressionReader;
        this.completer = completer;
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
        CompletionResult result = completer.apply(expr);
        return model()
                .add("expression", result.expression())
                .add("position", result.position().toString())
                .add("candidates", result.candidates());
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

    private static Function1<ConsoleLog, Model> commandResultToModel() {
        return new Function1<ConsoleLog, Model>() {
            public Model call(ConsoleLog consoleLog) throws Exception {
                return model().add("type", consoleLog.type())
                        .add("message", consoleLog.message());
            }
        };
    }

}
