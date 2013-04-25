package javarepl;

import com.googlecode.totallylazy.Sequences;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.URLs.url;
import static javarepl.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UtilsTest {
    @Test
    public void extractsTypeFromClass() {
        assertThat(extractType("".getClass()), equalToClass(String.class));
        assertThat(extractType(Arrays.asList(1, 2, 3).getClass()), equalToClass(AbstractList.class));
        assertThat(extractType(anonymousInnerRunnable().getClass()), equalToClass(Runnable.class));
        assertThat(extractType(anonymousInnerArrayList().getClass()), equalToClass(ArrayList.class));
    }

    @Test
    public void returnsResolvedClasspath() {
        assertThat(resolveClasspath("file:/aaa/bbb").toString(), equalTo("file:/aaa/bbb"));
        assertThat(resolveClasspath("http://aaa/bbb").toString(), equalTo("http://aaa/bbb"));
        assertThat(resolveClasspath("aaa/bbb").toString(), equalTo("file:aaa/bbb"));
    }

    @Test
    public void returnsPowerSetPermutations() {
        assertThat(powerSetPermutations(sequence(1, 2)),
                containsInAnyOrder(sequence(1), sequence(2), sequence(1, 2), sequence(2, 1), Sequences.<Integer>empty()));
    }

    @Test
    public void returnsAllPermutationsOfTheSet() {
        assertThat(permutations(sequence(1, 2, 3)),
                containsInAnyOrder(
                        sequence(1, 2, 3), sequence(1, 3, 2), sequence(2, 1, 3),
                        sequence(2, 3, 1), sequence(3, 1, 2), sequence(3, 2, 1)));
    }

    @Test
    public void checkIfUrlIsAWebOne() {
        assertThat(isWebUrl(url("http://www.google.com")), is(true));
        assertThat(isWebUrl(url("https://www.google.com")), is(true));
        assertThat(isWebUrl(url("file:/some/file")), is(false));
    }

    private Matcher<? super Class<?>> equalToClass(Class clazz) {
        return equalTo(clazz);
    }

    private Object anonymousInnerArrayList() {
        return new ArrayList<Object>() {
        };
    }

    private Object anonymousInnerRunnable() {
        return new Runnable() {
            public void run() {
            }
        };
    }
}
