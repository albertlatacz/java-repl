package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.String.format;
import static javarepl.Evaluation.*;
import static javarepl.EvaluationClassRenderer.ExpressionRenderer.renderExpression;
import static javarepl.EvaluationClassRenderer.MethodNameRenderer.renderMethodName;
import static javarepl.Expression.*;
import static javarepl.Utils.extractType;

class EvaluationClassRenderer {
    public static String renderEvaluationClass(EvaluationContext context, String className, Expression expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(renderDefaultImports());
        writer.println();

        writer.println(renderUserImports(context));
        writer.println();

        writer.println(renderImportExpression(expression));
        writer.println();

        writer.println(renderClassName(className));
        writer.println();

        writer.println(renderMethodName(expression));
        writer.println(renderPreviousEvaluations(context));

        writer.println();
        writer.println(renderExpression(expression));
        writer.println();

        writer.println(renderEndOfMethod());
        writer.println(renderEndOfFile());

        return outputStream.toString();
    }

    private static String renderPreviousEvaluations(EvaluationContext context) {
        return context.results().map(new Function1<Result, String>() {
            public String call(Result result) throws Exception {
                return format("    %s %s = valueOf(\"%s\");", extractType(result.value.getClass()).getCanonicalName(), result.key, result.key);
            }
        }).toString("\n");
    }

    private static String renderImportExpression(Expression expression) {
        return format("%s;", expression instanceof Import ? expression.source : "");
    }

    private static String renderUserImports(EvaluationContext context) {
        return context.imports()
                .map(expression().then(source()))
                .toString("", ";\n", ";");
    }

    private static String renderDefaultImports() {
        return sequence(
                "import java.util.*;",
                "import java.math.*;",
                "import static java.lang.Math.*;"
        ).toString("\n");
    }

    private static String renderClassName(String className) {
        return format("public class %s extends javarepl.EvaluationTemplate {", className);
    }

    private static String renderEndOfMethod() {
        return format("  }");
    }

    private static String renderEndOfFile() {
        return format("}");
    }

    public static class MethodNameRenderer {
        @multimethod
        public static String renderMethodName(Expression expression) {
            return new multi() {}.<String>methodOption(expression)
                    .getOrThrow(new IllegalArgumentException(expression + " not mapped"));
        }

        @multimethod
        private static String renderMethodName(ClassOrInterface expression) {
            return methodNameWithType("void");
        }

        @multimethod
        private static String renderMethodName(Statement expression) {
            return methodNameWithType("void");
        }

        @multimethod
        private static String renderMethodName(Import expression) {
            return methodNameWithType("void");
        }

        @multimethod
        private static String renderMethodName(Assignment expression) {
            return methodNameWithType("Object");
        }

        @multimethod
        private static String renderMethodName(AssignmentWithType expression) {
            return methodNameWithType(expression.type);
        }

        @multimethod
        private static String renderMethodName(Value expression) {
            return methodNameWithType("Object");
        }

        private static String methodNameWithType(String returnType) {
            return format("  public %s evaluate() throws Exception {", returnType);
        }
    }

    public static class ExpressionRenderer {
        @multimethod
        public static String renderExpression(Expression expression) {
            return new multi() {}.<String>methodOption(expression)
                    .getOrThrow(new IllegalArgumentException(expression + " not mapped"));
        }

        @multimethod
        private static String renderExpression(Import expression) {
            return "";
        }

        @multimethod
        private static String renderExpression(ClassOrInterface expression) {
            return "";
        }

        @multimethod
        private static String renderExpression(Statement expression) {
            return format("    %s;", expression.source);
        }

        @multimethod
        private static String renderExpression(Assignment expression) {
            return expressionWithValue(expression.value);
        }

        @multimethod
        private static String renderExpression(AssignmentWithType expression) {
            return expressionWithValue(expression.value);
        }

        @multimethod
        private static String renderExpression(Value expression) {
            return expressionWithValue(expression.source);
        }

        private static String expressionWithValue(String value) {
            return format("    return\n    %s;", value);
        }
    }

}
