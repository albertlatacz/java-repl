package javarepl.console;

import com.googlecode.yadic.Container;

public abstract class DelegatingConsole implements Console {
    private final Console delegate;

    protected DelegatingConsole(Console delegate) {
        this.delegate = delegate;
    }

    public ConsoleResult execute(String expression) {
        return delegate.execute(expression);
    }

    public Container context() {
        return delegate.context();
    }

    public void shutdown() {
        delegate.shutdown();
    }
}
