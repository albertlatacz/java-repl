package javarepl.expressions;

import java.util.regex.MatchResult;

public final class Assignment extends Expression {
    private final String key;
    private final String value;

    public Assignment(String source) {
        super(source);
        MatchResult match = Patterns.assignmentPattern.match(source);
        this.key = match.group(1).trim();
        this.value = match.group(2).trim();
    }

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }
}
