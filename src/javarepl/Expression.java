package javarepl;

import com.googlecode.totallylazy.Function1;

public class Expression {
    public static enum Type {
        IMPORT, VALUE, STATEMENT
    }

    public final String source;
    public final Type type;

    private Expression(String source, Type type) {
        this.source = source;
        this.type = type;
    }

    public static Expression expression(String source, Type type) {
        return new Expression(source, type);
    }


    public boolean isImport() {
        return Type.IMPORT.equals(type);
    }

    public boolean isStatement() {
        return Type.STATEMENT.equals(type);
    }

    public boolean isValue() {
        return Type.VALUE.equals(type);
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

    public static Function1<Expression, Type> type() {
        return new Function1<Expression, Type>() {
            public Type call(Expression value) throws Exception {
                return value.type;
            }
        };
    }
}
