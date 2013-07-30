package javarepl.rendering;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Strings;
import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;
import javarepl.EvaluationContext;
import javarepl.EvaluationTemplate;
import javarepl.Result;
import javarepl.expressions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.googlecode.totallylazy.Strings.replaceAll;
import static java.lang.String.format;
import static javarepl.Utils.extractType;
import static javarepl.expressions.Expression.functions.source;
import static javarepl.expressions.Patterns.methodPattern;
import static javarepl.rendering.ExpressionTokenRenderer.renderExpressionToken;
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

        writer.println(renderPreviousImports(context));
        writer.println(renderExpressionToken(expression));
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

        writer.println(renderPreviousImports(context));
        writer.println(renderClassName(className));
        writer.println(renderClassConstructor(className));
        writer.println(renderPreviousEvaluations(context));
        writer.println(renderPreviousMethods(context));
        writer.println(renderExpressionToken(expression));
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
            writer.println(renderPreviousImports(context));
        }
        writer.println(expression.source());

        return outputStream.toString();
    }

    public static String renderMethodSignatureDetection(EvaluationContext context, String className, String expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(renderPreviousImports(context));
        writer.println(renderInterfaceName(className));
        writer.println(renderInterfaceMethod(expression));
        writer.println(renderEndOfFile());

        return outputStream.toString();
    }

    private static String renderInterfaceName(String className) {
        return format("public interface %s {", className);
    }

    private static String renderInterfaceMethod(String expression) {
        String signature = methodPattern.match(expression).group(1);
        String type = methodPattern.match(expression).group(2);
        return format("  %s;", signature.substring(signature.indexOf(type)));
    }


    private static String render(EvaluationContext context, String className, Expression expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(renderPreviousImports(context));
        writer.println(renderClassName(className));
        writer.println(renderClassConstructor(className));
        writer.println(renderPreviousEvaluations(context));
        writer.println(renderPreviousMethods(context));
        writer.println(renderMethodName(expression));
        writer.println(renderExpressionToken(expression));
        writer.println(renderEndOfMethod());
        writer.println(renderEndOfFile());

        return outputStream.toString();
    }

    private static String renderPreviousEvaluations(EvaluationContext context) {
        return context.results().map(new Mapper<Result, String>() {
            public String call(Result result) throws Exception {
                return format("  public %s %s = valueOf(\"%s\");", result.type().getCanonicalName(), result.key(), result.key());
            }
        }).toString("\n");
    }

    private static String renderPreviousMethods(EvaluationContext context) {
        return context.expressionsOfType(Method.class)
                .map(source().then(replaceAll("\n", "\n  ")).then(Strings.format("  %s")))
                .toString("\n\n");
    }

    private static String renderPreviousImports(EvaluationContext context) {
        return context.expressionsOfType(Import.class)
                .map(source().then(Strings.format("%s;")))
                .toString("\n");
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
