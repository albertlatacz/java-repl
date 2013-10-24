package javarepl.completion;

import org.junit.Test;

import static javarepl.completion.CompleterTestHelper.*;
import static org.junit.Assert.assertThat;

public class InstanceMemberCompleterTest {

    @Test
    public void shouldCompleteStaticMembers() {
        InstanceMemberCompleter completer = new InstanceMemberCompleter(evaluator());
        String expression = TestOuterClass.class.getCanonicalName() + ".staticField.";

//        CompletionResult result = completer.apply(expression);
//        assertThat(result,
//                completesTo(candidates("staticField", "staticMethod("), position(expression.length())));

        assertThat(completer.apply("invalid"), completesTo(candidatesValues(), position(0)));
    }

    @SuppressWarnings("unused")
    public static class TestOuterClass {
        static StaticInnerClass x = new StaticInnerClass();

        public static class StaticInnerClass {
            static Integer y = 12;

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
