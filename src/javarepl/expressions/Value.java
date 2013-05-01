package javarepl.expressions;

public final class Value extends Expression {
    public Value(String source) {
        super(source);
    }

    @Override
    public String key() {
        return source();
    }
}
