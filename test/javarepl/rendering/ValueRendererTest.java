package javarepl.rendering;

import org.junit.Test;

import static javarepl.rendering.ValueRenderer.renderValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValueRendererTest {

    @Test
    public void shouldRenderValuesCorrectlyForArrays() {
        assertThat(renderValue(new byte[]{1, 2}), is("[1, 2]"));
        assertThat(renderValue(new int[]{1, 2}), is("[1, 2]"));
        assertThat(renderValue(new short[]{1, 2}), is("[1, 2]"));
        assertThat(renderValue(new long[]{1, 2}), is("[1, 2]"));
        assertThat(renderValue(new double[]{1.5, 2.5}), is("[1.5, 2.5]"));
        assertThat(renderValue(new float[]{1.5f, 2.5f}), is("[1.5, 2.5]"));
        assertThat(renderValue(new boolean[]{true, false}), is("[true, false]"));
        assertThat(renderValue(new char[]{'a', 'b'}), is("[a, b]"));
    }


    @Test
    public void shouldRenderValuesCorrectlyForObjectArrays() {
        assertThat(renderValue(new Integer[]{1, 2}), is("[1, 2]"));
        assertThat(renderValue(new Object[][]{{42, "str1"}, {42d, "str2"}}), is("[[42, \"str1\"], [42.0, \"str2\"]]"));
    }

    @Test
    public void shouldRenderValuesCorrectlyForObjects() {
        assertThat(renderValue("test"), is("\"test\""));
        assertThat(renderValue(42), is("42"));
        assertThat(renderValue(null), is("null"));
    }
}
