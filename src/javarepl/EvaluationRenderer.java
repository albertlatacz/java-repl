package javarepl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.String.format;
import static javarepl.Utils.extractType;

class EvaluationRenderer {

    public static String render(EvaluationContext context, String className, Expression expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(format("import java.util.*;"));
        writer.println(format("import java.math.*;"));
        writer.println(format("import static java.lang.Math.*;"));
        writer.println();

        for (Evaluation evaluation : context.imports()) {
            writer.println(format("%s;", evaluation.expression.source));
        }

        if (expression.isImport()) {
            writer.println(format("%s;", expression.source));
        }

        writer.println();
        writer.println(format("public class %s extends javarepl.EvaluationTemplate {", className));
        writer.println(format("  public %s evaluate() throws Exception {", expression.isValue() ? "Object" : "void"));

        for (Result result : context.results()) {
            writer.println(String.format("    %s %s = valueOf(\"%s\");",
                    extractType(result.value.getClass()).getCanonicalName(), result.key, result.key));
        }

        if (!expression.isImport()) {
            writer.println();
            writer.println(format("%s    %s;", expression.isValue() ? "    return\n" : "", expression.source));
            writer.println();
        }

        writer.println(format("  }"));
        writer.println(format("}"));

        return outputStream.toString();
    }

}
