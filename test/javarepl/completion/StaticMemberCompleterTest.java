package javarepl.completion;

import org.junit.Test;

import static javarepl.completion.CompleterTestHelper.*;
import static org.junit.Assert.assertThat;

public class StaticMemberCompleterTest {

    @Test
    public void shouldCompleteStaticMembers() {
        StaticMemberCompleter completer = new StaticMemberCompleter(evaluator());
        String expression = TestOuterClass.class.getCanonicalName() + ".";

        assertThat(completer.apply(expression),
                completesTo(candidates(TestOuterClass.StaticInnerClass.class.getSimpleName(), "staticField", "staticMethod("), position(expression.length())));
    }

    @Test
    public void shouldCompleteStaticMembersFromImports() {
        StaticMemberCompleter completer = new StaticMemberCompleter(evaluator(importMembersOf(TestOuterClass.class)));
        String expression = TestOuterClass.StaticInnerClass.class.getSimpleName() + ".";

        assertThat(completer.apply(expression), completesTo(candidates("innerStaticMethod("), position(expression.length())));
    }

    @SuppressWarnings("unused")
    public static class TestOuterClass {

        public static class StaticInnerClass {
            public static void innerStaticMethod() {
            }
        }

        public static Integer staticField;

        public static void staticMethod() {
        }

        private static Integer privateStaticMember;
        protected static Integer protectedStaticMember;

        public class InstanceClass {
        }

        public Integer instanceField;

        public void instanceMethod() {
        }


    }
}
