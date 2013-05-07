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

public final class ConsoleConfig {
    public final Option<File> historyFile;
    public final ConsoleLogger logger;
    public final Evaluator evaluator;
    public final Sequence<Class<? extends Command>> commands;
    public final Sequence<Result> results;

    private ConsoleConfig(Evaluator evaluator, Option<File> historyFile, ConsoleLogger logger, Sequence<Class<? extends Command>> commands, Sequence<Result> results) {
        this.evaluator = evaluator;
        this.historyFile = historyFile;
        this.logger = logger;
        this.commands = commands;
        this.results = results;
    }

    public static ConsoleConfig consoleConfig() {
        return new ConsoleConfig(new Evaluator(), Option.<File>none(), new ConsoleLogger(), Sequences.<Class<? extends Command>>empty(), Sequences.<Result>empty());
    }

    public ConsoleConfig evaluator(Evaluator evaluator) {
        return new ConsoleConfig(evaluator, historyFile, logger, commands, results);
    }

    public ConsoleConfig logger(ConsoleLogger logger) {
        return new ConsoleConfig(evaluator, historyFile, option(logger).getOrElse(new ConsoleLogger()), commands, results);
    }

    public ConsoleConfig historyFile(Option<File> file) {
        return new ConsoleConfig(evaluator, file, logger, commands, results);
    }

    public ConsoleConfig historyFile(File file) {
        return new ConsoleConfig(evaluator, option(file), logger, commands, results);
    }

    public ConsoleConfig commands(Class... cmds) {
        return new ConsoleConfig(evaluator, historyFile, logger, sequence(cmds).<Class<? extends Command>>unsafeCast(), results);
    }

    public ConsoleConfig results(Result... results) {
        return new ConsoleConfig(evaluator, historyFile, logger, commands, sequence(results));
    }
}
