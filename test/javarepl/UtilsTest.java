package javarepl;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static javarepl.Utils.extractSimpleType;
import static javarepl.Utils.extractType;

public class UtilsTest {

    @Test
    public void shouldExtractTypeFromClass() {
        assertThat(extractType("".getClass()), equalTo("java.lang.String"));
        assertThat(extractType(new Integer[][][]{{{1, 2}, {3, 4}}, {{5, 6}}}.getClass()), equalTo("java.lang.Integer[][][]"));
        assertThat(extractType(Arrays.asList(1, 2, 3).getClass()), equalTo("java.util.AbstractList"));
    }

    @Test
    public void shouldExtractSimpleTypeFromClass() {
        assertThat(extractSimpleType(new Integer[][][]{{{1, 2}, {3, 4}}, {{5, 6}}}.getClass()), equalTo("Integer[][][]"));
    }
}
