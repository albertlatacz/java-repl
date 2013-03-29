package javarepl.expressions;

import static javarepl.expressions.ExpressionPatterns.classPattern;
import static javarepl.expressions.ExpressionPatterns.interfacePattern;
import static javarepl.expressions.ExpressionPatterns.isValidClass;

public final class ClassOrInterface extends Expression implements WithKey {
    private final String type;

    public ClassOrInterface(String source) {
        super(source);

        type = (isValidClass(source) ? classPattern : interfacePattern).match(source).group(1);
    }

    public String key() {
        return type;
    }

    public String type() {
        return type;
    }
}
