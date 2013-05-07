package javarepl.console;


import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import javarepl.Evaluator;
import javarepl.Result;
import javarepl.console.commands.Command;

import java.io.File;

import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.sequence;

public final class SimpleConsoleConfig {
    public final Option<File> historyFile;
    public final ConsoleLogger logger;
    public final Evaluator evaluator;
    public final Sequence<Class<? extends Command>> commands;
    public final Sequence<Result> results;

    private SimpleConsoleConfig(Evaluator evaluator, Option<File> historyFile, ConsoleLogger logger, Sequence<Class<? extends Command>> commands, Sequence<Result> results) {
        this.evaluator = evaluator;
        this.historyFile = historyFile;
        this.logger = logger;
        this.commands = commands;
        this.results = results;
    }

    public static SimpleConsoleConfig consoleConfig() {
        return new SimpleConsoleConfig(new Evaluator(), Option.<File>none(), new ConsoleLogger(), Sequences.<Class<? extends Command>>empty(), Sequences.<Result>empty());
    }

    public SimpleConsoleConfig evaluator(Evaluator evaluator) {
        return new SimpleConsoleConfig(evaluator, historyFile, logger, commands, results);
    }

    public SimpleConsoleConfig logger(ConsoleLogger logger) {
        return new SimpleConsoleConfig(evaluator, historyFile, option(logger).getOrElse(new ConsoleLogger()), commands, results);
    }

    public SimpleConsoleConfig historyFile(Option<File> file) {
        return new SimpleConsoleConfig(evaluator, file, logger, commands, results);
    }

    public SimpleConsoleConfig historyFile(File file) {
        return new SimpleConsoleConfig(evaluator, option(file), logger, commands, results);
    }

    public SimpleConsoleConfig commands(Class... cmds) {
        return new SimpleConsoleConfig(evaluator, historyFile, logger, sequence(cmds).<Class<? extends Command>>unsafeCast(), results);
    }

    public SimpleConsoleConfig results(Result... results) {
        return new SimpleConsoleConfig(evaluator, historyFile, logger, commands, sequence(results));
    }
}
