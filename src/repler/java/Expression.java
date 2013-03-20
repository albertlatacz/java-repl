package repler.java;

public class Expression {
    private final String source;
    private final String className;
    private final String classSource;

    private Expression(String source, String className, String classSource) {
        this.source = source;
        this.className = className;
        this.classSource = classSource;
    }

    public static Expression expression(String source, String className, String classSource) {
        return new Expression(source, className, classSource);
    }

    public String getSource() {
        return source;
    }

    public String getClassName() {
        return className;
    }

    public String getClassSource() {
        return classSource;
    }

    @Override
    public String toString() {
        return className + "(" + source + ")";
    }

    @Override
    public int hashCode() {
        return (source != null ? source.hashCode() : 0) +
                (className != null ? className.hashCode() : 0) +
                (classSource != null ? classSource.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Expression &&
                (source != null && source.equals(((Expression) other).source)) &&
                (className != null && className.equals(((Expression) other).className)) &&
                (classSource != null && classSource.equals(((Expression) other).classSource));
    }
}
