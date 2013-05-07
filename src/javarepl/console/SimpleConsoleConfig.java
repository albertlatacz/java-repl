package javarepl.console;


import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import javarepl.Result;
import javarepl.console.commands.Command;

import java.io.File;

import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.sequence;

public final class SimpleConsoleConfig {
    public final Option<File> historyFile;
    public final Option<ConsoleLogger> logger;
    public final Sequence<Command> commands;
    public final Sequence<Result> results;

    private SimpleConsoleConfig(Option<File> historyFile, Sequence<Command> commands, Option<ConsoleLogger> logger, Sequence<Result> results) {
        this.historyFile = historyFile;
        this.commands = commands;
        this.logger = logger;
        this.results = results;
    }

    public static SimpleConsoleConfig consoleConfig() {
        return new SimpleConsoleConfig(Option.<File>none(), Sequences.<Command>empty(), Option.<ConsoleLogger>none(), Sequences.<Result>empty());
    }

    public SimpleConsoleConfig logger(ConsoleLogger logger) {
        return new SimpleConsoleConfig(historyFile, commands, option(logger), results);
    }

    public SimpleConsoleConfig historyFile(Option<File> file) {
        return new SimpleConsoleConfig(file, commands, logger, results);
    }

    public SimpleConsoleConfig historyFile(File file) {
        return new SimpleConsoleConfig(option(file), commands, logger, results);
    }

    public SimpleConsoleConfig commands(Command... cmds) {
        return new SimpleConsoleConfig(historyFile, sequence(cmds), logger, results);
    }

    public SimpleConsoleConfig results(Result... results) {
        return new SimpleConsoleConfig(historyFile, commands, logger, sequence(results));
    }
}
