package javarepl.expressions;

import static javarepl.expressions.Patterns.methodPattern;

public final class Method extends Expression implements WithKey {
    private final String name;

    public Method(String source) {
        super(source);

        name = methodPattern.match(source).group(2);
    }

    public String name() {
        return name;
    }

    public String key() {
        return name;
    }
}
