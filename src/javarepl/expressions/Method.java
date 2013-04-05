package javarepl.expressions;

import static javarepl.expressions.Patterns.methodPattern;

public final class Method extends Expression implements WithKey {
    private final String type;
    private final String name;

    public Method(String source) {
        super(source);

        type = methodPattern.match(source).group(1);
        name = methodPattern.match(source).group(2);
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String key() {
        return type + " " + name + "()";
    }
}
