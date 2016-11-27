package javarepl.console;

import com.googlecode.totallylazy.Files;
import org.junit.Test;

import java.io.File;

import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Strings.replaceAll;
import static com.googlecode.totallylazy.predicates.Predicates.never;
import static javarepl.console.ConsoleHistory.emptyHistory;
import static javarepl.console.ConsoleHistory.historyFromFile;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleHistoryTest {
    @Test
    public void savesAndLoadsMultilineHistory() {
        File historyFile = Files.temporaryFile();
        ConsoleHistory history = emptyHistory(never(), some(historyFile))
                .add("multiline\nexpression")
                .add("single line expression")
                .add("another\nmultiline\nexpression");

        history.save();

        assertThat(historyFromFile(never(), some(historyFile)).items(), is(history.items().map(replaceAll("\n", " "))));
    }
}
