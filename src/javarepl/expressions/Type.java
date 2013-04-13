package javarepl.expressions;


import static javarepl.expressions.Patterns.typePattern;

public final class Type extends Expression implements WithKey {
    private final String typePackage;
    private final String type;

    public Type(String source) {
        super(source);

        typePackage = typePattern.match(source).group(1);
        type = typePattern.match(source).group(2);
    }

    public String key() {
        return type;
    }

    public String typePackage() {
        return typePackage;
    }

    public String type() {
        return type;
    }
}
