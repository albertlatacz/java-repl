package javarepl.console.commands;

import org.junit.Test;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Pair.pair;
import static javarepl.console.commands.Command.parseNumericCommand;
import static javarepl.console.commands.Command.parseStringCommand;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CommandTest {

    @Test
    public void shouldParseNumericCommand() {
        assertThat(parseNumericCommand(":cmd 12"), is(pair(":cmd", some(12))));
        assertThat(parseNumericCommand(":cmd hello"), is(pair(":cmd", none(Integer.class))));
        assertThat(parseNumericCommand(":cmd"), is(pair(":cmd", none(Integer.class))));
    }

    @Test
    public void shouldParseStringCommand() {
        assertThat(parseStringCommand(":cmd 12"), is(pair(":cmd", some("12"))));
        assertThat(parseStringCommand(":cmd some params  "), is(pair(":cmd", some("some params"))));
        assertThat(parseStringCommand(":cmd"), is(pair(":cmd", none(String.class))));
    }
}
