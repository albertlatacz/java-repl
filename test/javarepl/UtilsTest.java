package javarepl;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.io.URLs.url;
import static java.util.Arrays.asList;
import static javarepl.Utils.*;
import static javarepl.testclasses.Constructors.*;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class UtilsTest {
    @Test
    public void checksJavaVersionCorrectly() {
        String javaVersion = System.getProperty("java.version");
        System.setProperty("java.version", "1.6.0");
        assertTrue(javaVersionAtLeast("1.5.0"));
        assertTrue(javaVersionAtLeast("1.6.0"));
        assertTrue(javaVersionAtLeast("1.6"));
        assertFalse(javaVersionAtLeast("1.6.1"));
        assertFalse(javaVersionAtLeast("1.7.0"));
        assertFalse(javaVersionAtLeast("1.7"));
        System.setProperty("java.version", javaVersion);
    }

    @Test
    public void extractsTypeFromClass() {
        assertThat(extractType("".getClass()), hasFormOf("class java.lang.String"));
        assertThat(extractType(asList(1, 2, 3).getClass()), hasFormOf("java.util.AbstractList<E>"));
        assertThat(extractType(anonymousInnerGenericTestClass().getClass()), hasFormOf("javarepl.testclasses.GenericTestClass<javarepl.testclasses.BaseTestInterface>"));
        assertThat(extractType(anonymousInnerBaseTestInterface().getClass()), hasFormOf("interface javarepl.testclasses.BaseTestInterface"));
        assertThat(extractType(protectedAccessTestClass().getClass()), hasFormOf("class javarepl.testclasses.ProtectedAccessTestClass"));
        assertThat(extractType(privateAccessTestClass().getClass()), hasFormOf("class javarepl.testclasses.PublicBaseTestClass"));
        assertThat(extractType(defaultAccessTestClass().getClass()), hasFormOf("class javarepl.testclasses.PublicBaseTestClass"));
    }

    @Test
    public void returnsResolvedUrl() {
        assertThat(resolveURL("file:/aaa/bbb").toString(), equalTo("file:/aaa/bbb"));
        assertThat(resolveURL("http://aaa/bbb").toString(), equalTo("http://aaa/bbb"));
        assertThat(resolveURL("aaa/bbb").toString(), equalTo("file:aaa/bbb"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void returnsPowerSetPermutations() {
        assertThat(powerSetPermutations(sequence(1, 2)),
                containsInAnyOrder(sequence(1), sequence(2), sequence(1, 2), sequence(2, 1), empty(Integer.class)));
    }

    @Test
    @SuppressWarnings("unchecked")
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

    private Matcher<? super Type> hasFormOf(final String form) {
        return new TypeSafeMatcher<Type>() {
            protected boolean matchesSafely(Type type) {
                return form.equals(type.toString());
            }

            public void describeTo(Description description) {
                description.appendText(form);
            }
        };
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
