package javarepl.web;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import com.googlecode.utterlyidle.MediaType;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.Responses;
import com.googlecode.utterlyidle.annotations.*;

import static com.googlecode.funclate.Model.persistent.model;
import static com.googlecode.utterlyidle.Responses.response;
import static com.googlecode.utterlyidle.Status.BAD_REQUEST;
import static com.googlecode.utterlyidle.Status.OK;

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

        return clientHandler.map(clientHandlerToModel()).get();
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
    @Path("remove")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@FormParam("id") String id) {
        Option<WebConsoleClientHandler> clientHandler = agent.removeClient(id);

        System.out.println("clientHandler = " + clientHandler);

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

    private Function1<WebConsoleClientHandler, Model> clientHandlerToModel() {
        return new Function1<WebConsoleClientHandler, Model>() {
            public Model call(WebConsoleClientHandler webConsoleClientHandler) throws Exception {
                return model()
                        .add("id", webConsoleClientHandler.id)
                        .add("port", webConsoleClientHandler.port);
            }
        };
    }
}
