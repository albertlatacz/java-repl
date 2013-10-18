package javarepl.web;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.collections.ListMap;
import com.googlecode.totallylazy.collections.PersistentMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.googlecode.totallylazy.Some.some;

public class WebConsole {
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private PersistentMap<String, WebConsoleClientHandler> clients = ListMap.emptyListMap();

    public WebConsole() {
        executorService.scheduleAtFixedRate(clearStaleSessions(), 10, 10, TimeUnit.SECONDS);
    }

    private Runnable clearStaleSessions() {
        return new Runnable() {
            public void run() {
                for (WebConsoleClientHandler client : clients.values()) {
                    if (!client.exitCode().isEmpty()) {
                        removeClient(client.id());
                    }
                }
            }
        };
    }

    public synchronized Option<WebConsoleClientHandler> createClient(Option<String> expression) {
        WebConsoleClientHandler clientHandler = new WebConsoleClientHandler(expression);
        clients = clients.insert(clientHandler.id(), clientHandler);
        return some(clientHandler);
    }


    public synchronized Option<WebConsoleClientHandler> removeClient(String id) {
        Option<WebConsoleClientHandler> clientHandler = clients.lookup(id);

        if (!clientHandler.isEmpty()) {
            clientHandler.get().shutdown();
            clients = clients.delete(clientHandler.get().id());
        }

        return clientHandler;
    }


    public Option<WebConsoleClientHandler> client(String id) {
        return clients.lookup(id);
    }

    public Sequence<WebConsoleClientHandler> clients() {
        return clients.values();
    }


}
