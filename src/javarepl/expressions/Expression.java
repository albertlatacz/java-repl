package javarepl.expressions;

import com.googlecode.totallylazy.Mapper;

public abstract class Expression {
    private final String source;

    protected Expression(String source) {
        this.source = source;
    }

    public abstract String key();

    public String source() {
        return source;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + source + ")";
    }

    @Override
    public int hashCode() {
        return (source != null ? source.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Expression &&
                other.getClass().equals(getClass()) &&
                (source != null && source.equals(((Expression) other).source));
    }

    public static final class functions {
        public static Mapper<Expression, String> source() {
            return new Mapper<Expression, String>() {
                public String call(Expression value) throws Exception {
                    return value.source;
                }
            };
        }

        public static Mapper<Expression, String> key() {
            return new Mapper<Expression, String>() {
                public String call(Expression value) throws Exception {
                    return value.key();
                }
            };
        }
    }


}
