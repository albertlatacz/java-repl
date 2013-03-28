package javarepl;

import com.googlecode.totallylazy.Function1;

public class Expression {
    public final String source;

    private Expression(String source) {
        this.source = source;
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

    public static Function1<Expression, String> source() {
        return new Function1<Expression, String>() {
            public String call(Expression value) throws Exception {
                return value.source;
            }
        };
    }

    public static class Import extends Expression {
        public Import(String source) {
            super(source);
        }
    }

    public static class Value extends Expression {
        public Value(String source) {
            super(source);
        }
    }

    public static class Statement extends Expression {
        public Statement(String source) {
            super(source);
        }
    }

    public static class Assignment extends Expression {
        public final String key;
        public final String value;

        public Assignment(String source) {
            super(source);

            this.key = source.substring(0, source.indexOf("=")).trim();
            this.value = source.substring(source.indexOf("=") + 1).trim();
        }
    }

}
