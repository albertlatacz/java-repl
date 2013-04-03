package javarepl.expressions;


import static javarepl.expressions.Patterns.typePattern;

public final class Type extends Expression implements WithKey {
    private final String type;

    public Type(String source) {
        super(source);

        type = typePattern.match(source).group(1);
    }

    public String key() {
        return type;
    }

    public String type() {
        return type;
    }
}
