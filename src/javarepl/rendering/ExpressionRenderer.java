package javarepl.rendering;

import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;
import javarepl.expressions.*;

import static java.lang.String.format;

public class ExpressionRenderer {
    @multimethod
    public static String renderExpression(Expression expression) {
        return new multi() {}.<String>methodOption(expression)
                .getOrThrow(new IllegalArgumentException(expression + " not mapped"));
    }

    @multimethod
    private static String renderExpression(Statement expression) {
        return format("    %s;", expression.source());
    }

    @multimethod
    private static String renderExpression(Assignment expression) {
        return expressionWithValue(expression.value());
    }

    @multimethod
    private static String renderExpression(AssignmentWithType expression) {
        return expressionWithValue(expression.value());
    }

    @multimethod
    private static String renderExpression(Value expression) {
        return expressionWithValue(expression.source());
    }

    private static String expressionWithValue(String value) {
        return format("    return\n    %s;", value);
    }
}
