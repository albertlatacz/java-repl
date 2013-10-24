package javarepl.completion;

import com.googlecode.totallylazy.Sequence;

import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.completion.CompletionCandidate.functions.asCompletionCandidate;

public class StringCompleter extends Completer {
    private final Sequence<String> candidates;

    public StringCompleter(Sequence<String> candidates) {
        this.candidates = candidates;
    }

    @Override
    public CompletionResult call(String expression) throws Exception {
        int lastSpace = expression.lastIndexOf(" ") + 1;
        return new CompletionResult(expression, lastSpace,
                candidates.filter(startsWith(expression.substring(lastSpace))).map(asCompletionCandidate()));
    }
}
