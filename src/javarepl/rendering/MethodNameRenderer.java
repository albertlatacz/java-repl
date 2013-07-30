package javarepl.rendering;

import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;
import javarepl.expressions.*;

import static java.lang.String.format;

public class MethodNameRenderer {
    @multimethod
    public static String renderMethodName(Expression expression) {
        return new multi() {}.<String>methodOption(expression)
                .getOrThrow(new IllegalArgumentException(expression + " not mapped"));
    }

    @multimethod
    private static String renderMethodName(Statement expression) {
        return methodNameWithType(void.class);
    }

    @multimethod
    private static String renderMethodName(Import expression) {
        return methodNameWithType(void.class);
    }

    @multimethod
    private static String renderMethodName(Method expression) {
        return methodNameWithType(void.class);
    }

    @multimethod
    private static String renderMethodName(Assignment expression) {
        return methodNameWithType(Object.class);
    }

    @multimethod
    private static String renderMethodName(AssignmentWithType expression) {
        return methodNameWithType(expression.type());
    }

    @multimethod
    private static String renderMethodName(Value expression) {
        return methodNameWithType(Object.class);
    }

    private static String methodNameWithType(Class<?> returnType) {
        return format("  public %s evaluate() throws Exception {", returnType.getCanonicalName());
    }
}
