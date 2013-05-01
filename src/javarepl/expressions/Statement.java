package javarepl.expressions;

public final class Statement extends Expression {
    public Statement(String source) {
        super(source);
    }

    public String key() {
        return source();
    }
}
