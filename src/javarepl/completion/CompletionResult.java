package javarepl.completion;

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
}
