package javarepl.completion;

import javarepl.completion.helpers.SimpleCompletionInstance;
import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.completion.CompleterTestHelper.*;
import static org.junit.Assert.assertThat;

public class InstanceMemberCompleterTest {

    @Test
    public void shouldCompleteInstance() {
        String expression = "ci.over";
        String instanceClassName = SimpleCompletionInstance.class.getCanonicalName();
        InstanceMemberCompleter completer = new InstanceMemberCompleter(evaluator("ci = new "+instanceClassName+"()"));

        CompletionResult result = completer.apply(expression);

        assertThat(result,
                completesTo(candidates(new CompletionCandidate("overloadedMethod(", sequence("int overloadedMethod()", "int overloadedMethod(int)", "int overloadedMethod(int, int)"))),
                position(3)));

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
