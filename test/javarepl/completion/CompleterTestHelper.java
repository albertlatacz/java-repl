package javarepl.completion;

import com.googlecode.totallylazy.Sequence;
import javarepl.Evaluator;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.completion.CompletionCandidate.functions.asCompletionCandidate;

public class CompleterTestHelper {

    public static Matcher<CompletionResult> completesTo(final Sequence<CompletionCandidate> candidates, final int position) {
        return new TypeSafeMatcher<CompletionResult>() {
            private CompletionResult result;

            protected boolean matchesSafely(CompletionResult completionResult) {
                result = completionResult;
                return completionResult.candidates().equals(candidates) && completionResult.position() == position;
            }

            public void describeTo(Description description) {
                description.appendText(new CompletionResult(result.expression(), position, candidates).toString());
            }
        };
    }

    public static Sequence<CompletionCandidate> candidatesValues(String... candidates) {
        return sequence(candidates).map(asCompletionCandidate());
    }

    public static Sequence<CompletionCandidate> candidates(CompletionCandidate... candidates) {
        return sequence(candidates);
    }

    public static int position(int position) {
        return position;
    }

    public static Evaluator evaluator(String... expressions) {
        Evaluator evaluator = new Evaluator();
        for (String expression : expressions) {
            evaluator.evaluate(expression);
        }
        return evaluator;
    }

    public static String importMembersOf(Class<?> aClass) {
        return "import " + aClass.getCanonicalName() + ".*";
    }
}
