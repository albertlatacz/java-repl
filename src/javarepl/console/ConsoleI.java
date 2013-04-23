package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import javarepl.console.commands.Command;
import javarepl.console.commands.CommandResult;

public interface ConsoleI {
    CommandResult execute(String expression);

    Sequence<Command> commands();
}
