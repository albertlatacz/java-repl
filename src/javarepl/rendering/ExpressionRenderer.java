package javarepl.rendering;

import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;
import javarepl.Expression;

import static java.lang.String.format;

public class ExpressionRenderer {
    @multimethod
    public static String renderExpression(Expression expression) {
        return new multi() {}.<String>methodOption(expression)
                .getOrThrow(new IllegalArgumentException(expression + " not mapped"));
    }

    @multimethod
    private static String renderExpression(Expression.Import expression) {
        return "";
    }

    @multimethod
    private static String renderExpression(Expression.ClassOrInterface expression) {
        return "";
    }

    @multimethod
    private static String renderExpression(Expression.Statement expression) {
        return format("    %s;", expression.source);
    }

    @multimethod
    private static String renderExpression(Expression.Assignment expression) {
        return expressionWithValue(expression.value);
    }

    @multimethod
    private static String renderExpression(Expression.AssignmentWithType expression) {
        return expressionWithValue(expression.value);
    }

    @multimethod
    private static String renderExpression(Expression.Value expression) {
        return expressionWithValue(expression.source);
    }

    private static String expressionWithValue(String value) {
        return format("    return\n    %s;", value);
    }
}
