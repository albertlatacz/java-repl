package javarepl.commands;

import javarepl.Evaluator;
import jline.console.completer.StringsCompleter;

import static com.googlecode.totallylazy.Predicates.equalTo;

public class ShowHelp extends Command {
    private static final String COMMAND = ":help";

    public ShowHelp() {
        super(COMMAND + " - shows this help", equalTo(COMMAND), new StringsCompleter(COMMAND));
    }

    public Void call(Evaluator evaluator, String expression) throws Exception {
        System.out.println(new StringBuilder()
                .append("Available commands: \n")
                .append("    :help - display this help\n")
                .append("    :cp <path> - includes given file or directory in the classpath\n")
                .append("    :history [num] - shows the history (optional 'num' is number of evaluations to show)\n")
                .append("    :h! [num] - evaluate last expression (optional 'num' to evaluate expression from history)\n")
                .append("    :h? <search_string> - searches the history\n")
                .append("    :list <results|types|methods|imports|all> - list specified values\n")
                .append("    :src - display last compiled source\n")
                .append("    :clear - clear all variables\n")
                .append("    :replay - replay all evaluations\n")
                .append("    :quit - exits the app\n")
                .toString());
        return null;
    }
}
