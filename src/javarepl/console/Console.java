package javarepl.console;

import com.googlecode.yadic.Container;

public interface Console {
    ConsoleResult execute(String expression);

    Container context();

    void shutdown();
}
