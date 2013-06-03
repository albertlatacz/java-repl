package javarepl.console;


import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import javarepl.Evaluator;
import javarepl.Result;
import javarepl.console.commands.*;

import java.io.File;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;

public final class ConsoleConfig {
    public final Option<File> historyFile;
    public final Sequence<String> expressions;
    public final ConsoleLogger logger;
    public final Evaluator evaluator;
    public final Sequence<Class<? extends Command>> commands;
    public final Sequence<Result> results;
    public final Boolean sandboxed;


    private ConsoleConfig(Evaluator evaluator, Option<File> historyFile, Sequence<String> expressions, ConsoleLogger logger, Sequence<Class<? extends Command>> commands, Sequence<Result> results, Boolean sandboxed) {
        this.evaluator = evaluator;
        this.historyFile = historyFile;
        this.expressions = expressions;
        this.logger = logger;
        this.commands = commands;
        this.results = results;
        this.sandboxed = sandboxed;
    }

    public static ConsoleConfig consoleConfig() {
        return new ConsoleConfig(new Evaluator(), none(File.class), empty(String.class), new ConsoleLogger(), defaultCommands(), empty(Result.class), false);
    }

    public ConsoleConfig logger(ConsoleLogger logger) {
        return new ConsoleConfig(evaluator, historyFile, expressions, option(logger).getOrElse(new ConsoleLogger()), commands, results, sandboxed);
    }

    public ConsoleConfig historyFile(Option<File> file) {
        return new ConsoleConfig(evaluator, file, expressions, logger, commands, results, sandboxed);
    }

    public ConsoleConfig historyFile(File file) {
        return new ConsoleConfig(evaluator, option(file), expressions, logger, commands, results, sandboxed);
    }

    public ConsoleConfig expressions(String... expressions) {
        return new ConsoleConfig(evaluator, historyFile, sequence(expressions), logger, commands, results, sandboxed);
    }

    public ConsoleConfig commands(Class... cmds) {
        return new ConsoleConfig(evaluator, historyFile, expressions, logger, sequence(cmds).<Class<? extends Command>>unsafeCast(), results, sandboxed);
    }

    public ConsoleConfig results(Result... results) {
        return new ConsoleConfig(evaluator, historyFile, expressions, logger, commands, sequence(results), sandboxed);
    }

    public ConsoleConfig sandboxed(boolean sandboxed) {
        return new ConsoleConfig(evaluator, historyFile, expressions, logger, commands, results, sandboxed);
    }

    public static Sequence<Class<? extends Command>> defaultCommands() {
        return Sequences.<Class<? extends Command>>sequence()
                .add(QuitApplication.class)
                .add(ShowHistory.class)
                .add(SearchHistory.class)
                .add(EvaluateFromHistory.class)
                .add(ResetAllEvaluations.class)
                .add(ReplayAllEvaluations.class)
                .add(EvaluateFile.class)
                .add(AddToClasspath.class)
                .add(LoadSourceFile.class)
                .add(ListValues.class)
                .add(ShowLastSource.class)
                .add(ShowTypeOfExpression.class);
    }

}
