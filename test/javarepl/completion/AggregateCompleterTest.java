package javarepl.completion;

import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AggregateCompleterTest {

    @Test
    public void shouldAggregateCompletions() {
        AggregateCompleter completer = new AggregateCompleter(sequence(
                new StringCompleter(sequence("text", "someText", "otherText")),
                new CommandCompleter("someCommand", sequence("param1", "param2"))));

        assertThat(completer.apply("so"), is(new CompletionResult("so", 0, sequence("someText", "someCommand"))));
        assertThat(completer.apply("prefix so"), is(new CompletionResult("prefix so", 7, sequence("someText"))));
        assertThat(completer.apply("invalid"), is(new CompletionResult("invalid", 0, empty(String.class))));
    }
}
