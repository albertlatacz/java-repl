package javarepl.rendering;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;
import javarepl.EvaluationContext;
import javarepl.EvaluationTemplate;
import javarepl.Result;
import javarepl.expressions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.String.format;
import static javarepl.Utils.extractType;
import static javarepl.expressions.Expression.functions.source;
import static javarepl.rendering.ExpressionRenderer.renderExpression;
import static javarepl.rendering.MethodNameRenderer.renderMethodName;

public class EvaluationClassRenderer {

    @multimethod
    public static String renderExpressionClass(EvaluationContext context, String className, Expression expression) {
        return new multi() {
        }.<String>methodOption(context, className, expression)
                .getOrThrow(new IllegalArgumentException(expression + " not mapped"));
    }

    @multimethod
    private static String renderExpressionClass(EvaluationContext context, String className, Value expression) {
        return render(context, className, expression);
    }

    @multimethod
    private static String renderExpressionClass(EvaluationContext context, String className, Statement expression) {
        return render(context, className, expression);
    }

    @multimethod
    private static String renderExpressionClass(EvaluationContext context, String className, Assignment expression) {
        return render(context, className, expression);
    }

    @multimethod
    private static String renderExpressionClass(EvaluationContext context, String className, AssignmentWithType expression) {
        return render(context, className, expression);
    }

    @multimethod
    private static String renderExpressionClass(EvaluationContext context, String className, Import expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(renderDefaultImports());
        writer.println(renderUserImports(context));
        writer.println(renderImportExpression(expression));
        writer.println(renderClassName(className));
        writer.println(renderClassConstructor(className));
        writer.println(renderPreviousEvaluations(context));
        writer.println(renderMethodName(expression));
        writer.println(renderEndOfMethod());
        writer.println(renderEndOfFile());

        return outputStream.toString();
    }

    @multimethod
    private static String renderExpressionClass(EvaluationContext context, String className, Method expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(renderDefaultImports());
        writer.println(renderUserImports(context));
        writer.println(renderClassName(className));
        writer.println(renderClassConstructor(className));
        writer.println(renderPreviousEvaluations(context));
        writer.println(renderPreviousMethods(context));
        writer.println(renderMethodExpression(expression));
        writer.println(renderMethodName(expression));
        writer.println(renderEndOfMethod());
        writer.println(renderEndOfFile());

        return outputStream.toString();
    }

    @multimethod
    private static String renderExpressionClass(EvaluationContext context, String className, Type expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        if (expression.typePackage().isEmpty()) {
            writer.println(renderDefaultImports());
            writer.println(renderUserImports(context));
        }
        writer.println(expression.source());

        return outputStream.toString();
    }


    private static String render(EvaluationContext context, String className, Expression expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(renderDefaultImports());
        writer.println(renderUserImports(context));
        writer.println(renderClassName(className));
        writer.println(renderClassConstructor(className));
        writer.println(renderPreviousEvaluations(context));
        writer.println(renderPreviousMethods(context));
        writer.println(renderMethodName(expression));
        writer.println(renderExpression(expression));
        writer.println(renderEndOfMethod());
        writer.println(renderEndOfFile());

        return outputStream.toString();
    }

    private static String renderPreviousEvaluations(EvaluationContext context) {
        return context.results().map(new Function1<Result, String>() {
            public String call(Result result) throws Exception {
                return format("  private final %s %s = valueOf(\"%s\");", extractType(result.value().getClass()).getCanonicalName(), result.key(), result.key());
            }
        }).toString("\n", "\n", "\n");
    }

    private static String renderPreviousMethods(EvaluationContext context) {
        return context.expressionsOfType(Method.class)
                .map(source())
                .toString("\n", "\n\n", "\n")
                .replaceAll("\n", "\n  ");
    }

    private static String renderImportExpression(Import expression) {
        return format("%s;", expression.source());
    }

    private static String renderMethodExpression(Method expression) {
        return format("  %s\n", expression.source().replaceAll("\n", "\n  "));
    }

    private static String renderUserImports(EvaluationContext context) {
        return context.expressionsOfType(Import.class)
                .map(source())
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
        return format("public final class %s extends %s {", className, EvaluationTemplate.class.getCanonicalName());
    }

    private static String renderClassConstructor(String className) {
        return format("  public %s(%s context) { super(context); }", className, EvaluationContext.class.getCanonicalName());
    }

    private static String renderEndOfMethod() {
        return format("  }");
    }

    private static String renderEndOfFile() {
        return format("}");
    }

}
