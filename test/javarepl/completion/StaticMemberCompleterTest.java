package javarepl.completion;

import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.completion.CompleterTestHelper.*;
import static org.junit.Assert.assertThat;

public class StaticMemberCompleterTest {

    @Test
    public void shouldCompleteStaticMembers() {
        StaticMemberCompleter completer = new StaticMemberCompleter(evaluator());
        String expression = TestOuterClass.class.getCanonicalName() + ".";

        assertThat(completer.apply(expression),
                completesTo(candidatesValues(TestOuterClass.StaticInnerClass.class.getSimpleName(), "staticField"), position(expression.length())));
    }

    @Test
    public void shouldCompleteStaticMembersFromImports() {
        StaticMemberCompleter completer = new StaticMemberCompleter(evaluator(importMembersOf(TestOuterClass.class)));
        String expression = TestOuterClass.StaticInnerClass.class.getSimpleName() + ".";

        assertThat(completer.apply(expression), completesTo(candidates(new CompletionCandidate("innerStaticMethod(", sequence("void innerStaticMethod(int)"))), position(expression.length())));
    }

    @SuppressWarnings("unused")
    public static class TestOuterClass {

        public static class StaticInnerClass {
            public static void innerStaticMethod(int param) {
            }
        }

        public static Integer staticField;

        private static Integer privateStaticMember;
        protected static Integer protectedStaticMember;

        public class InstanceClass {
        }

        public Integer instanceField;

        public void instanceMethod() {
        }


    }
}
