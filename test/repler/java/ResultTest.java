package repler.java;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static repler.java.Result.methods.extractType;

public class ResultTest {

    @Test
    public void shouldExtractTypeFromClass() {
        assertThat(extractType("".getClass()), equalTo("java.lang.String"));
        assertThat(extractType(new Integer[][][]{{{1, 2}, {3, 4}}, {{5,6}}}.getClass()), equalTo("java.lang.Integer[][][]"));

    }
}
