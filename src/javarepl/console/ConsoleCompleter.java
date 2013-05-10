package javarepl.console;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import javarepl.Result;
import jline.console.completer.Completer;

import java.util.List;

import static com.googlecode.totallylazy.Strings.startsWith;

public class ConsoleCompleter implements Completer {
    private final Console console;

    public ConsoleCompleter(Console console) {
        this.console = console;
    }

    public int complete(final String buffer, final int cursor, final List<CharSequence> candidates) {
        int lastSpace = buffer.lastIndexOf(" ") + 1;

        String extractedFromLastSpace = buffer.substring(lastSpace);
        Sequence<String> completionCandidates = potentialCandidates().filter(startsWith(extractedFromLastSpace));

        if (completionCandidates.isEmpty())
            return -1;
        else
            candidates.addAll(completionCandidates.toList());

        return lastSpace;
    }

    private Sequence<String> potentialCandidates() {
        return console.context().get(Evaluator.class).results().map(Result.functions.key());
    }
}
