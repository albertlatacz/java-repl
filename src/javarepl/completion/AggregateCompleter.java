package javarepl.completion;

import com.googlecode.totallylazy.Group;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;

import static com.googlecode.totallylazy.Groups.groupKey;
import static com.googlecode.totallylazy.Sequences.empty;
import static javarepl.completion.CompletionResult.functions.candidates;
import static javarepl.completion.CompletionResult.functions.position;

public class AggregateCompleter extends Completer {
    private final Sequence<Completer> completers;

    public AggregateCompleter(Sequence<Completer> completers) {
        this.completers = completers;
    }

    public CompletionResult call(String expression) throws Exception {
        Option<Group<Integer, CompletionResult>> group = completeGroup(expression);

        if (group.isEmpty())
            return new CompletionResult(expression, 0, empty(String.class));

        return new CompletionResult(expression, group.get().key(), group.get().flatMap(candidates()));
    }

    private Option<Group<Integer, CompletionResult>> completeGroup(String expression) {
        return completers.map(complete(expression))
                .groupBy(position())
                .sortBy(groupKey(Integer.class))
                .reverse()
                .headOption();
    }

    private Mapper<Completer, CompletionResult> complete(final String expression) {
        return new Mapper<Completer, CompletionResult>() {
            public CompletionResult call(Completer completer) throws Exception {
                return completer.apply(expression);
            }
        };
    }
}
