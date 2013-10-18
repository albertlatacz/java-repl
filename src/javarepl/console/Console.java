package javarepl.console;

import javarepl.completion.CompletionResult;
import javarepl.rendering.ExpressionTemplate;

public interface Console {

    ConsoleResult execute(String expression);

    CompletionResult completion(String expression);

    ExpressionTemplate template(String expression);

    ConsoleStatus status();

    ConsoleHistory history();

    void start();

    void shutdown();

}
