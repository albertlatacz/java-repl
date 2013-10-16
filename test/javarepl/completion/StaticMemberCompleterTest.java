package javarepl.completion;

import javarepl.completion.StaticMemberCompleterTest.TestOuterClass.TestInnerClass;
import org.junit.Test;

import static javarepl.completion.CompleterTestHelper.*;
import static org.junit.Assert.assertThat;

public class StaticMemberCompleterTest {

    @Test
    public void shouldCompleteStaticMethodsFromImports() {
        StaticMemberCompleter completer = new StaticMemberCompleter(evaluator(importMembersOf(TestOuterClass.class)));
        String expression = TestInnerClass.class.getSimpleName() + ".";

        assertThat(completer.apply(expression), completesTo(candidates("innerStaticMethod("), position(expression.length())));
    }

    @SuppressWarnings("unused")
    public static class TestOuterClass {
        public static class TestInnerClass {
            public static void innerStaticMethod() {
            }
        }

        public static void outerStaticMethod1() {
        }

        public static void outerStaticMethod2() {
        }
    }
}
