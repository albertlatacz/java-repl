package javarepl.web;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Files;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Strings;
import com.googlecode.utterlyidle.*;
import com.googlecode.utterlyidle.annotations.*;

import java.io.File;
import java.net.URI;
import java.util.UUID;

import static com.googlecode.funclate.Model.persistent.model;
import static com.googlecode.totallylazy.Files.directory;
import static com.googlecode.totallylazy.Files.workingDirectory;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.URLs.uri;
import static com.googlecode.utterlyidle.Responses.response;
import static com.googlecode.utterlyidle.Status.*;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static javarepl.Utils.applicationVersion;

@Hidden
public class WebConsoleResource {
    private final WebConsole agent;
    private final BaseUri baseUri;

    public WebConsoleResource(WebConsole agent, BaseUri baseUri) {
        this.agent = agent;
        this.baseUri = baseUri;
    }


    @POST
    @Hidden
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Model create(@FormParam("expression") Option<String> expression,
                        @FormParam("snap") Option<String> snap) throws Exception {


        Option<String> initial = snap.isDefined()
                ? some(format(":eval %s", snapUri(snap.get())))
                : expression;

        Option<WebConsoleClientHandler> clientHandler = agent.createClient(initial);

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
    @Path("snap")
    @Produces(MediaType.APPLICATION_JSON)
    public Response snap(@FormParam("id") String id) {
        Option<WebConsoleClientHandler> clientHandler = agent.client(id);

        if (!clientHandler.isEmpty()) {
            String snapId = UUID.randomUUID().toString();

            Files.write(clientHandler.get().history().toString("\n").getBytes(), snapFile(snapId));

            return ResponseBuilder
                    .response()
                    .entity(model()
                            .add("snap", snapId)
                            .add("uri", snapUri(snapId).toString())
                    ).build();

        } else {
            return response(BAD_REQUEST);
        }
    }

    @GET
    @Hidden
    @Path("snap/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSnap(@PathParam("id") String id) {
        File snap = snapFile(id);
        if (snap.exists()) {
            return ResponseBuilder.response().entity(Strings.lines(snap).toString("\n")).build();
        } else {
            return ResponseBuilder.response(NOT_FOUND).build();
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

    private URI snapUri(String snapId) {
        return uri(baseUri + "snap/" + snapId);
    }

    private File snapFile(String id) {
        return new File(directory(workingDirectory(), "snapped"), id + ".repl");
    }
}
