package repler.java;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.String.format;
import static repler.java.Utils.extractType;

class EvaluationRenderer {

    public static String render(EvaluationContext context, String className, Expression expression) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(format("import java.util.*;"));
        writer.println(format("import java.math.*;"));
        writer.println(format("import static java.lang.Math.*;"));
        writer.println();

        for (Evaluation expr : context.imports()) {
            writer.println(format("%s;", expr.getExpression().getSource()));
        }

        if (expression.isImport()) {
            writer.println(format("%s;", expression.getSource()));
        }

        writer.println();
        writer.println(format("public class %s extends repler.java.EvaluationTemplate {", className));
        writer.println(format("  public %s evaluate() throws Exception {", expression.isValue() ? "Object" : "void"));

        for (Result result : context.results()) {
            writer.println(format("    %s %s = valueOf(\"%s\");",
                    extractType(result.getValue().getClass()), result.getKey(), result.getKey()));
        }

        if (!expression.isImport()) {
            writer.println();
            writer.println(format("%s    %s;", expression.isValue() ? "    return\n" : "", expression.getSource()));
            writer.println();
        }

        writer.println(format("  }"));
        writer.println(format("}"));

        return outputStream.toString();
    }

}
