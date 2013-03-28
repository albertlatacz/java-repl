package javarepl;

import com.googlecode.totallylazy.Function;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.sequence;
import static java.util.Arrays.asList;
import static javarepl.ExpressionReader.emptyPrompt;
import static javarepl.ExpressionReader.lines;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExpressionReaderTest {

    @Test
    public void shouldTerminateCorrectlyWhenReadingTheExpression() {
        assertThat(new ExpressionReader(lines("int ", "x = 12;")).readExpression().source, is("int "));
        assertThat(new ExpressionReader(lines("{", "int x = 12;")).readExpression().source, is("{\nint x = 12;"));
        assertThat(new ExpressionReader(lines("{", "int x = 12;",")")).readExpression().source, is("{\nint x = 12;\n)"));
        assertThat(new ExpressionReader(lines("{", "int x = 12;", "}")).readExpression().source, is("{\nint x = 12;\n}"));
        assertThat(new ExpressionReader(lines("{", "int[]", "x = new int[]{12, 22};", "}")).readExpression().source, is("{\nint[]\nx = new int[]{12, 22};\n}"));
        assertThat(new ExpressionReader(lines("{", "int", "", "", "}")).readExpression().source, is("{\nint\n\n"));
    }


}
