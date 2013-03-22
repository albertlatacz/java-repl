package repler.java;

import com.googlecode.totallylazy.Pair;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.googlecode.totallylazy.Randoms.takeFromValues;
import static com.googlecode.totallylazy.Sequences.characters;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.isPrivate;

public class EvaluationRenderer {

    public static String render(EvaluationContext context, String className, String expression, boolean isAssignment) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        writer.println(format("import java.util.*;"));
        writer.println(format("import java.math.*;"));
        writer.println(format("import static java.lang.Math.*;"));
        writer.println();
        writer.println(format("public class %s extends repler.java.EvaluationTemplate {", className));
        writer.println(format("  public %s $eval() {", isAssignment ? "Object" : "void"));

        for (Pair<Expression, Result> evaluation : context.evaluations()) {
            writer.println(format("    %s %s = $val(\"%s\"); // %s",
                    extractType(evaluation.second().getValue().getClass()), evaluation.second().getKey(), evaluation.second().getKey(), evaluation.second()));
        }

        writer.println();
        writer.println(format("%s    %s;", isAssignment ? "    return\n" : "", expression));
        writer.println();
        writer.println(format("  }"));
        writer.println(format("}"));

        return outputStream.toString();
    }

    public static String randomIdentifier(String prefix) {
        return prefix + "$" + takeFromValues(characters("abcdefghijklmnopqrstuvwxyz1234567890")).take(20).toString("");
    }

    public static String extractType(Class<?> clazz) {
        if (isPrivate(clazz.getModifiers()))
            return extractType(clazz.getSuperclass());

        return clazz.getCanonicalName();
    }
}
