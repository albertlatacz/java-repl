package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.yadic.Container;
import javarepl.Evaluator;
import javarepl.console.commands.Command;

public abstract class DelegatingConsole implements Console {
    private final Console delegate;

    protected DelegatingConsole(Console delegate) {
        this.delegate = delegate;
    }

    public Sequence<Command> commands() {
        return delegate.commands();
    }

    public ConsoleHistory history() {
        return delegate.history();
    }

    public Evaluator evaluator() {
        return delegate.evaluator();
    }

    public ConsoleResult execute(String expression) {
        return delegate.execute(expression);
    }

    public Container context() {
        return delegate.context();
    }

    public Console delegate() {
        return delegate;
    }
}
