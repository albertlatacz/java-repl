package javarepl.completion;

import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.completion.CompleterTestHelper.*;
import static org.junit.Assert.assertThat;

public class StringCompleterTest {
    @Test
    public void shouldCompleteStringFromGivenCandidates() {
        StringCompleter completer = new StringCompleter(sequence("some string", "some other", "another"));

        assertThat(completer.apply(""), completesTo(candidatesValues("some string", "some other", "another"), position(0)));
        assertThat(completer.apply("str"), completesTo(candidatesValues(), position(0)));
        assertThat(completer.apply("prefix "), completesTo(candidatesValues("some string", "some other", "another"), position(7)));
        assertThat(completer.apply("prefix some"), completesTo(candidatesValues("some string", "some other"), position(7)));
    }
}
