package javarepl;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

import static javarepl.Utils.extractType;
import static javarepl.Utils.resolveClasspath;
import static javarepl.Utils.valueToString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UtilsTest {
    @Test
    public void extractsTypeFromClass() {
        assertThat(extractType("".getClass()), equalToClass(String.class));
        assertThat(extractType(Arrays.asList(1, 2, 3).getClass()), equalToClass(AbstractList.class));
        assertThat(extractType(anonymousInnerRunnable().getClass()), equalToClass(Runnable.class));
        assertThat(extractType(anonymousInnerArrayList().getClass()), equalToClass(ArrayList.class));
    }

    @Test
    public void returnFormattedValueAsString() {
        assertThat(valueToString().apply(null), equalTo("null"));
        assertThat(valueToString().apply("some string"), equalTo("\"some string\""));
        assertThat(valueToString().apply(new Object[][]{{42,"str1"},{42d,"str2"}}), equalTo("[[42, \"str1\"], [42.0, \"str2\"]]"));
    }

    @Test
    public void returnsResolvedClasspath() {
        assertThat(resolveClasspath("file:/aaa/bbb").toString(), equalTo("file:/aaa/bbb"));
        assertThat(resolveClasspath("http://aaa/bbb").toString(), equalTo("http://aaa/bbb"));
        assertThat(resolveClasspath("aaa/bbb").toString(), equalTo("file:aaa/bbb"));
    }

    private Matcher<? super Class<?>> equalToClass(Class clazz) {
        return equalTo(clazz);
    }

    private Object anonymousInnerArrayList() {
        return new ArrayList<Object>(){};
    }

    private Object anonymousInnerRunnable() {
        return new Runnable() {
            public void run() {
            }
        };
    }
}
