package repler.java;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Pair;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.googlecode.totallylazy.Option.none;
import static java.lang.String.format;
import static repler.java.Utils.extractType;

public class EvaluationRenderer {

    public static String render(EvaluationContext context, String className, String expression, boolean isAssignment, boolean isImport) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(format("import java.util.*;"));
        writer.println(format("import java.math.*;"));
        writer.println(format("import static java.lang.Math.*;"));
        writer.println();

        for (Evaluation expr : context.imports()) {
            writer.println(format("%s;", expr.getExpression().getSource()));
        }

        if (isImport) {
            writer.println(format("%s;", expression));
        }

        writer.println();
        writer.println(format("public class %s extends repler.java.EvaluationTemplate {", className));
        writer.println(format("  public %s evaluate() throws Exception {", isAssignment && !isImport ? "Object" : "void"));

        for (Result result : context.results()) {
            writer.println(format("    %s %s = valueOf(\"%s\");",
                    extractType(result.getValue().getClass()), result.getKey(), result.getKey()));
        }

        if (!isImport) {
            writer.println();
            writer.println(format("%s    %s;", isAssignment ? "    return\n" : "", expression));
            writer.println();
        }

        writer.println(format("  }"));
        writer.println(format("}"));

        return outputStream.toString();
    }

}
