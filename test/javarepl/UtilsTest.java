package javarepl;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static javarepl.Utils.extractType;
import static org.hamcrest.Matchers.is;

public class UtilsTest {

    @Test
    public void shouldExtractTypeFromClass() {
        assertThat(extractType("".getClass()), equalToClass(String.class));
        assertThat(extractType(Arrays.asList(1, 2, 3).getClass()), equalToClass(AbstractList.class));
        assertThat(extractType(anonymousInnerRunnable().getClass()), equalToClass(Runnable.class));
        assertThat(extractType(anonymousInnerArrayList().getClass()), equalToClass(ArrayList.class));
    }

    private Matcher<? super Class<?>> equalToClass(Class clazz) {
        return Matchers.equalTo(clazz);
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
