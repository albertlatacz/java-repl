package javarepl.expressions;

public final class Import extends Expression {
    public Import(String source) {
        super(source);
    }

    public String key() {
        return source();
    }
}
