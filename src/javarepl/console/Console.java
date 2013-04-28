package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import javarepl.console.commands.Command;

public interface Console {
    ConsoleResult execute(String expression);

    Sequence<Command> commands();
}
