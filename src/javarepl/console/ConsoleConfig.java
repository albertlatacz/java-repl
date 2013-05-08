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
    public final Option<String> expression;
    public final ConsoleLogger logger;
    public final Evaluator evaluator;
    public final Sequence<Class<? extends Command>> commands;
    public final Sequence<Result> results;

    private ConsoleConfig(Evaluator evaluator, Option<File> historyFile, Option<String> expression, ConsoleLogger logger, Sequence<Class<? extends Command>> commands, Sequence<Result> results) {
        this.evaluator = evaluator;
        this.historyFile = historyFile;
        this.expression = expression;
        this.logger = logger;
        this.commands = commands;
        this.results = results;
    }

    public static ConsoleConfig consoleConfig() {
        return new ConsoleConfig(new Evaluator(), Option.<File>none(), Option.<String>none(), new ConsoleLogger(), Sequences.<Class<? extends Command>>empty(), Sequences.<Result>empty());
    }

    public ConsoleConfig evaluator(Evaluator evaluator) {
        return new ConsoleConfig(evaluator, historyFile, expression, logger, commands, results);
    }

    public ConsoleConfig logger(ConsoleLogger logger) {
        return new ConsoleConfig(evaluator, historyFile, expression, option(logger).getOrElse(new ConsoleLogger()), commands, results);
    }

    public ConsoleConfig historyFile(Option<File> file) {
        return new ConsoleConfig(evaluator, file, expression, logger, commands, results);
    }

    public ConsoleConfig historyFile(File file) {
        return new ConsoleConfig(evaluator, option(file), expression, logger, commands, results);
    }

    public ConsoleConfig expression(Option<String> expression) {
        return new ConsoleConfig(evaluator, historyFile, expression, logger, commands, results);
    }

    public ConsoleConfig commands(Class... cmds) {
        return new ConsoleConfig(evaluator, historyFile, expression, logger, sequence(cmds).<Class<? extends Command>>unsafeCast(), results);
    }

    public ConsoleConfig results(Result... results) {
        return new ConsoleConfig(evaluator, historyFile, expression, logger, commands, sequence(results));
    }
}
