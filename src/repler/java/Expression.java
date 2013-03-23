package repler.java;

import com.googlecode.totallylazy.Function1;

public class Expression {
    public static enum Type {
        IMPORT, VALUE, STATEMENT
    }

    private final String source;
    private final Type type;

    private Expression(String source, Type type) {
        this.source = source;
        this.type = type;
    }

    public static Expression expression(String source, Type type) {
        return new Expression(source, type);
    }

    public String getSource() {
        return source;
    }

    public Type getType() {
        return type;
    }

    public boolean isImport() {
        return getType().equals(Type.IMPORT);
    }

    public boolean isStatement() {
        return getType().equals(Type.STATEMENT);
    }

    public boolean isValue() {
        return getType().equals(Type.VALUE);
    }

    @Override
    public String toString() {
        return type + "(" + source +")";
    }

    @Override
    public int hashCode() {
        return (source != null ? source.hashCode() : 0) +
                (type != null ? type.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Expression &&
                (source != null && source.equals(((Expression) other).source)) &&
                (type != null && type.equals(((Expression) other).type));
    }

    public static Function1<Expression, Type> expressionType() {
        return new Function1<Expression, Type>() {
            public Type call(Expression value) throws Exception {
                return value.getType();
            }
        };
    }
}
