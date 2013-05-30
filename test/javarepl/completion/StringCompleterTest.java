package javarepl.completion;

import com.googlecode.totallylazy.Sequences;
import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.sequence;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StringCompleterTest {
    @Test
    public void shouldCompleteStringFromGivenCandidates() {
        StringCompleter completer = new StringCompleter(sequence("some string", "some other", "another"));

        assertThat(completer.apply("str"), is(new CompletionResult("str", 0, Sequences.<String>empty())));
        assertThat(completer.apply("prefix some"), is(new CompletionResult("prefix some", 7, sequence("some string", "some other"))));
    }
}
