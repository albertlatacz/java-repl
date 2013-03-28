package javarepl;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import static javarepl.Utils.isValidAssignment;
import static javarepl.Utils.isValidIdentifier;
import static org.hamcrest.MatcherAssert.assertThat;
import static javarepl.Utils.extractType;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void shouldMatchValidIdentifier() {
        assertTrue(isValidIdentifier("Aa_$09"));
        assertTrue(isValidIdentifier("$Aa_09"));
        assertTrue(isValidIdentifier("_$Aa09"));
        assertTrue(isValidIdentifier("  aass"));
        assertFalse(isValidIdentifier("£Aaa"));
        assertFalse(isValidIdentifier("aa ss"));
    }

    @Test
    public void shouldMatchValidAssignment() {
        assertTrue(isValidAssignment("val=42"));
        assertTrue(isValidAssignment("val =42"));
        assertTrue(isValidAssignment("val= 42"));
        assertTrue(isValidAssignment("  val  =   42  42  "));
        assertFalse(isValidAssignment("val wrong=   42  42  "));
    }

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
