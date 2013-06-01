package javarepl.completion;

import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CommandCompleterTest {
    @Test
    public void shouldCompleteCommandWithGivenCandidates() {
        CommandCompleter completer = new CommandCompleter("command", sequence("first", "second"));

        assertThat(completer.apply("invalid"), is(new CompletionResult("invalid", 0, empty(String.class))));
        assertThat(completer.apply(""), is(new CompletionResult("", 0, sequence("command"))));
        assertThat(completer.apply("command "), is(new CompletionResult("command ", 8, sequence("first", "second"))));
    }

    @Test
    public void shouldCompleteCommandWithoutCandidates() {
        CommandCompleter completer = new CommandCompleter("command", empty(String.class));

        assertThat(completer.apply(""), is(new CompletionResult("", 0, sequence("command"))));
        assertThat(completer.apply("command "), is(new CompletionResult("command ", 0, empty(String.class))));
    }
}
