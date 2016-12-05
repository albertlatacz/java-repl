package javarepl.web;

import com.googlecode.totallylazy.Files;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Strings;
import com.googlecode.totallylazy.collections.PersistentMap;
import com.googlecode.totallylazy.functions.Function1;
import com.googlecode.utterlyidle.BaseUri;
import com.googlecode.utterlyidle.MediaType;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.annotations.*;

import java.io.File;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

import static com.googlecode.totallylazy.Files.directory;
import static com.googlecode.totallylazy.Files.workingDirectory;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.collections.PersistentMap.constructors.emptyMap;
import static com.googlecode.totallylazy.io.URLs.uri;
import static com.googlecode.utterlyidle.Response.ok;
import static com.googlecode.utterlyidle.Response.response;
import static com.googlecode.utterlyidle.Response.seeOther;
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
    public Map<String, Object> create(@FormParam("expression") Option<String> expression,
                                      @FormParam("snap") Option<String> snap) throws Exception {


        Option<String> initial = snap.isDefined()
                ? some(format(":eval %s", snapUri(snap.get())))
                : expression;

        Option<WebConsoleClientHandler> clientHandler = agent.createClient(initial);

        return clientHandler.map(clientHandlerToModel()).get()
                .insert("welcomeMessage", welcomeMessage());
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

            return ok()
                    .entity(emptyMap(String.class, Object.class)
                            .insert("snap", snapId)
                            .insert("uri", snapUri(snapId).toString())
                    );

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
            return ok().entity(Strings.lines(snap).toString("\n"));
        } else {
            return response(NOT_FOUND);
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
    public Map<String, Object> list() {
        return emptyMap(String.class, Object.class)
                .insert("clients", agent.clients().map(clientHandlerToModel()));
    }


    @GET
    @Path("")
    public Response main() {
        return seeOther("term.html");
    }

    private Function1<WebConsoleClientHandler, PersistentMap<String, Object>> clientHandlerToModel() {
        return webConsoleClientHandler -> emptyMap(String.class, Object.class)
                .insert("id", webConsoleClientHandler.id())
                .insert("port", webConsoleClientHandler.port().getOrElse(-1));
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
