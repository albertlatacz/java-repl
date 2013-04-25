package javarepl.web;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.collections.ListMap;
import com.googlecode.totallylazy.collections.PersistentMap;

import static com.googlecode.totallylazy.Some.some;

public class WebConsole {
    private PersistentMap<String, WebConsoleClientHandler> clients = ListMap.emptyListMap();

    public Option<WebConsoleClientHandler> createClient() {
        WebConsoleClientHandler clientHandler = new WebConsoleClientHandler();
        clients = clients.put(clientHandler.id, clientHandler);
        return some(clientHandler);
    }


    public Option<WebConsoleClientHandler> removeClient(String id) {
        Option<WebConsoleClientHandler> clientHandler = clients.get(id);

        if (!clientHandler.isEmpty()) {
            clientHandler.get().shutdown();
            clients = clients.remove(clientHandler.get().id);
        }

        return clientHandler;
    }


    public Option<WebConsoleClientHandler> client(String id) {
        return clients.get(id);
    }

    public Sequence<WebConsoleClientHandler> clients() {
        return clients.values();
    }


}
