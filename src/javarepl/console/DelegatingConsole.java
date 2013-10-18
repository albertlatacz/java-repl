package javarepl.console;

import javarepl.completion.CompletionResult;
import javarepl.rendering.ExpressionTemplate;

public abstract class DelegatingConsole implements Console {
    private final Console delegate;

    protected DelegatingConsole(Console delegate) {
        this.delegate = delegate;
    }

    public ConsoleResult execute(String expression) {
        return delegate.execute(expression);
    }

    public CompletionResult completion(String expression) {
        return delegate.completion(expression);
    }

    public ExpressionTemplate template(String expression) {
        return delegate.template(expression);
    }

    public ConsoleStatus status() {
        return delegate.status();
    }

    public ConsoleHistory history() {
        return delegate.history();
    }

    public void shutdown() {
        delegate.shutdown();
    }

    public void start() {
        delegate.start();
    }
}
