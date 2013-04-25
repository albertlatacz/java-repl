package javarepl;

import org.junit.Test;

import static javarepl.ValueFormatter.valueToString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValueFormatterTest {

    @Test
    public void shouldFormatValuesCorrectlyForArrays() {
        assertThat(valueToString(new byte[]{1, 2}), is("[1, 2]"));
        assertThat(valueToString(new int[]{1, 2}), is("[1, 2]"));
        assertThat(valueToString(new short[]{1, 2}), is("[1, 2]"));
        assertThat(valueToString(new long[]{1, 2}), is("[1, 2]"));
        assertThat(valueToString(new double[]{1.5, 2.5}), is("[1.5, 2.5]"));
        assertThat(valueToString(new float[]{1.5f, 2.5f}), is("[1.5, 2.5]"));
        assertThat(valueToString(new boolean[]{true, false}), is("[true, false]"));
        assertThat(valueToString(new char[]{'a', 'b'}), is("[a, b]"));
    }

    @Test
    public void shouldFormatValuesCorrectlyForObjectArrays() {
        assertThat(valueToString(new Integer[]{1, 2}), is("[1, 2]"));
        assertThat(valueToString(new Object[][]{{42, "str1"}, {42d, "str2"}}), is("[[42, \"str1\"], [42.0, \"str2\"]]"));
    }

    @Test
    public void shouldFormatValuesCorrectlyForObjects() {
        assertThat(valueToString("test"), is("\"test\""));
        assertThat(valueToString(42), is("42"));
        assertThat(valueToString(null), is("null"));
    }
}
