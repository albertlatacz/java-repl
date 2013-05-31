package javarepl;

import org.junit.Test;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static javarepl.ExpressionReader.lines;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExpressionReaderTest {

    @Test
    public void shouldTerminateCorrectlyWhenReadingTheExpression() {
        assertThat(new ExpressionReader(lines("int ", "x = 12;")).readExpression(), is(some("int")));
        assertThat(new ExpressionReader(lines("{", "int x = 12;", "}")).readExpression(), is(some("{\nint x = 12;\n}")));
        assertThat(new ExpressionReader(lines("{", "int x = 12;", "}")).readExpression(), is(some("{\nint x = 12;\n}")));
        assertThat(new ExpressionReader(lines("{", "int[]", "x = new int[]{12, 22};", "}")).readExpression(), is(some("{\nint[]\nx = new int[]{12, 22};\n}")));
        assertThat(new ExpressionReader(lines("{", "int", "", "", "}")).readExpression(), is(some("{\nint")));
        assertThat(new ExpressionReader(lines()).readExpression(), is(none(String.class)));
    }


}
