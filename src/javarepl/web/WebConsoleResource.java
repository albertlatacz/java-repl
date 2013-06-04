package javarepl.web;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.utterlyidle.MediaType;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Responses;
import com.googlecode.utterlyidle.annotations.*;

import static com.googlecode.funclate.Model.persistent.model;
import static com.googlecode.utterlyidle.Responses.response;
import static com.googlecode.utterlyidle.Status.BAD_REQUEST;
import static com.googlecode.utterlyidle.Status.OK;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static javarepl.Utils.applicationVersion;

@Hidden
public class WebConsoleResource {
    private final WebConsole agent;

    public WebConsoleResource(WebConsole agent) {
        this.agent = agent;
    }

    @POST
    @Hidden
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Model create() {
        Option<WebConsoleClientHandler> clientHandler = agent.createClient();

        return clientHandler.map(clientHandlerToModel()).get()
                .add("welcomeMessage", welcomeMessage() + "\n\n");
    }

    @POST
    @Hidden
    @Path("execute")
    @Produces(MediaType.APPLICATION_JSON)
    public Response execute(@FormParam("id") String id, @FormParam("expression") String expression) {
        Option<WebConsoleClientHandler> clientHandler = agent.client(id);

        if (!clientHandler.isEmpty()) {
            if (expression.trim().startsWith(":quit"))
                return remove(id);

            return clientHandler.get().execute(expression);
        } else {
            return response(BAD_REQUEST);
        }
    }

    @POST
    @Hidden
    @Path("readExpression")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readExpression(@FormParam("id") String id, @FormParam("line") String line) {
        Option<WebConsoleClientHandler> clientHandler = agent.client(id);

        if (!clientHandler.isEmpty()) {
            return clientHandler.get().readExpression(line);
        } else {
            return response(BAD_REQUEST);
        }
    }

    @GET
    @Hidden
    @Path("completions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response completions(@QueryParam("id") String id, @QueryParam("expression") String expression) {
        Option<WebConsoleClientHandler> clientHandler = agent.client(id);

        if (!clientHandler.isEmpty()) {
            return clientHandler.get().completions(expression);
        } else {
            return response(BAD_REQUEST);
        }
    }

    @POST
    @Hidden
    @Path("remove")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@FormParam("id") String id) {
        Option<WebConsoleClientHandler> clientHandler = agent.removeClient(id);
        if (!clientHandler.isEmpty()) {
            return response(OK);
        } else {
            return response(BAD_REQUEST);
        }
    }

    @GET
    @Hidden
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Model list() {
        return model().add("clients", agent.clients().map(clientHandlerToModel()));
    }


    @GET
    @Path("")
    public Response main() {
        return Responses.seeOther("console.html");
    }

    private Mapper<WebConsoleClientHandler, Model> clientHandlerToModel() {
        return new Mapper<WebConsoleClientHandler, Model>() {
            public Model call(WebConsoleClientHandler webConsoleClientHandler) throws Exception {
                return model()
                        .add("id", webConsoleClientHandler.id())
                        .add("port", webConsoleClientHandler.port().getOrElse(-1));
            }
        };
    }

    private String welcomeMessage() {
        return format("%s, Java %s on %s %s\nWelcome to JavaREPL Web Console version %s",
                getProperty("java.vm.name"),
                getProperty("java.version"),
                getProperty("os.name"),
                getProperty("os.version"),
                applicationVersion());
    }
}
