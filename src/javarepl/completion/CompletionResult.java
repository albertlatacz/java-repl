package javarepl.completion;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;

public class CompletionResult {
    private final String expression;
    private final Integer position;
    private final Sequence<String> candidates;

    public CompletionResult(String expression, Integer position, Sequence<String> candidates) {
        this.expression = expression;
        this.position = position;
        this.candidates = candidates;
    }

    public Integer position() {
        return position;
    }

    public String expression() {
        return expression;
    }

    public Sequence<String> candidates() {
        return candidates;
    }

    @Override
    public String toString() {
        return expression + " -> " + candidates.toString("[", ",", "]") + " @ " + position;
    }

    @Override
    public int hashCode() {
        return (expression != null ? expression.hashCode() : 0) +
                (candidates != null ? candidates.hashCode() : 0) +
                (position != null ? position.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CompletionResult &&
                other.getClass().equals(getClass()) &&
                (expression != null && expression.equals(((CompletionResult) other).expression)) &&
                (candidates != null && candidates.equals(((CompletionResult) other).candidates)) &&
                (position != null && position.equals(((CompletionResult) other).position));
    }

    public static final class functions {
        public static Mapper<CompletionResult, Integer> position() {
            return new Mapper<CompletionResult, Integer>() {
                public Integer call(CompletionResult result) throws Exception {
                    return result.position();
                }
            };
        }

        public static Mapper<CompletionResult, Sequence<String>> candidates() {
            return new Mapper<CompletionResult, Sequence<String>>() {
                public Sequence<String> call(CompletionResult result) throws Exception {
                    return result.candidates();
                }
            };
        }
    }
}
