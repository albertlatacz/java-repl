package javarepl.completion;

import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StringCompleterTest {
    @Test
    public void shouldCompleteStringFromGivenCandidates() {
        StringCompleter completer = new StringCompleter(sequence("some string", "some other", "another"));

        assertThat(completer.apply(""), is(new CompletionResult("", 0, sequence("some string", "some other", "another"))));
        assertThat(completer.apply("str"), is(new CompletionResult("str", 0, empty(String.class))));
        assertThat(completer.apply("prefix "), is(new CompletionResult("prefix ", 7, sequence("some string", "some other", "another"))));
        assertThat(completer.apply("prefix some"), is(new CompletionResult("prefix some", 7, sequence("some string", "some other"))));
    }
}
