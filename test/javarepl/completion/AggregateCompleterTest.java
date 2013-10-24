package javarepl.completion;

import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.completion.CompleterTestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AggregateCompleterTest {

    @Test
    public void shouldAggregateCompletions() {
        AggregateCompleter completer = new AggregateCompleter(sequence(
                new StringCompleter(sequence("text", "someText", "otherText")),
                new CommandCompleter("someCommand", sequence("param1", "param2"))));

        assertThat(completer.apply("so"), completesTo(candidatesValues("someText", "someCommand"), position(0)));
        assertThat(completer.apply("prefix so"), completesTo(candidatesValues("someText"), position(7)));
        assertThat(completer.apply("invalid"), completesTo(candidatesValues(), position(0)));
    }
}
