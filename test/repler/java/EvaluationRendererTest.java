package repler.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static repler.java.EvaluationRenderer.extractType;

public class EvaluationRendererTest {

    @Test
    public void shouldExtractTypeFromClass() {
        assertThat(extractType("".getClass()), equalTo("java.lang.String"));
        assertThat(extractType(new Integer[][][]{{{1, 2}, {3, 4}}, {{5,6}}}.getClass()), equalTo("java.lang.Integer[][][]"));
        assertThat(extractType(Arrays.asList(1,2,3).getClass()), equalTo("java.util.AbstractList"));
    }
}
