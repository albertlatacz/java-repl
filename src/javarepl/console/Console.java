package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.yadic.Container;
import javarepl.Evaluator;
import javarepl.console.commands.Command;

public interface Console {
    ConsoleResult execute(String expression);

    Container context();

    Sequence<Command> commands();

    Evaluator evaluator();

    ConsoleHistory history();
}
