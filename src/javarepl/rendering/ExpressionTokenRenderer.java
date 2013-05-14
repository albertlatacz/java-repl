package javarepl.rendering;

import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;
import javarepl.expressions.*;

import static java.lang.String.format;
import static javarepl.Utils.randomIdentifier;

public class ExpressionTokenRenderer {
    public static final String EXPRESSION_TOKEN = "%JAVAREPL_EXPR%";

    @multimethod
    public static String renderExpressionToken(Expression expression) {
        return new multi() {
        }.<String>methodOption(expression)
                .getOrThrow(new IllegalArgumentException(expression + " not mapped"));
    }

    @multimethod
    private static String renderExpressionToken(Statement expression) {
        return "    " + EXPRESSION_TOKEN + ";";
    }

    @multimethod
    private static String renderExpressionToken(Assignment expression) {
        return expressionWithValue(expression.value());
    }

    @multimethod
    private static String renderExpressionToken(AssignmentWithType expression) {
        return expressionWithValue(expression.value(), expression.type());
    }

    @multimethod
    private static String renderExpressionToken(Value expression) {
        return expressionWithValue(expression.source());
    }

    @multimethod
    private static String renderExpressionToken(Method expression) {
        return "  " + EXPRESSION_TOKEN + "\n";
    }

    @multimethod
    private static String renderExpressionToken(Import expression) {
        return EXPRESSION_TOKEN + ";";
    }

    private static String expressionWithValue(String value) {
        return expressionWithValue(value, "Object");
    }

    private static String expressionWithValue(String value, String returnType) {
        String identifier = randomIdentifier("expr");
        return format("    %s %s =\n\n   %s;\n\n    return %s;", returnType, identifier, EXPRESSION_TOKEN, identifier);
    }
}
