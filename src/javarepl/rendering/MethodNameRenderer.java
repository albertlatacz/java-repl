package javarepl.rendering;

import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;
import javarepl.Expression;

import static java.lang.String.format;

public class MethodNameRenderer {
    @multimethod
    public static String renderMethodName(Expression expression) {
        return new multi() {}.<String>methodOption(expression)
                .getOrThrow(new IllegalArgumentException(expression + " not mapped"));
    }

    @multimethod
    private static String renderMethodName(Expression.ClassOrInterface expression) {
        return methodNameWithType("void");
    }

    @multimethod
    private static String renderMethodName(Expression.Statement expression) {
        return methodNameWithType("void");
    }

    @multimethod
    private static String renderMethodName(Expression.Import expression) {
        return methodNameWithType("void");
    }

    @multimethod
    private static String renderMethodName(Expression.Assignment expression) {
        return methodNameWithType("Object");
    }

    @multimethod
    private static String renderMethodName(Expression.AssignmentWithType expression) {
        return methodNameWithType(expression.type);
    }

    @multimethod
    private static String renderMethodName(Expression.Value expression) {
        return methodNameWithType("Object");
    }

    private static String methodNameWithType(String returnType) {
        return format("  public %s evaluate() throws Exception {", returnType);
    }
}
