package javarepl.expressions;

import java.util.regex.MatchResult;

import static javarepl.expressions.Patterns.assignmentWithTypeNamePattern;

public final class AssignmentWithType extends Expression {
    private final String type;
    private final String key;
    private final String value;

    public AssignmentWithType(String source) {
        super(source);

        MatchResult matches = assignmentWithTypeNamePattern.match(source);
        type = matches.group(1).trim();
        key = matches.group(2).trim();
        value = matches.group(3).trim();
    }

    public String key() {
        return key;
    }

    public String type() {
        return type;
    }

    public String value() {
        return value;
    }
}
