package repler.java;

import com.googlecode.funclate.Model;
import com.googlecode.funclate.stringtemplate.StringTemplateFunclate;
import com.googlecode.totallylazy.Files;
import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Pair;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;

import static com.googlecode.funclate.Model.persistent.model;
import static com.googlecode.totallylazy.Files.file;
import static com.googlecode.totallylazy.Files.temporaryDirectory;
import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Pair.pair;
import static com.googlecode.totallylazy.Randoms.takeFromValues;
import static com.googlecode.totallylazy.Sequences.characters;
import static com.googlecode.totallylazy.URLs.toURL;
import static repler.java.Expression.expression;
import static repler.java.Result.result;

public class REPL {
    private final File outputDirectory = temporaryDirectory(getClass().getSimpleName());
    private final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
    private final ClassLoader classLoader = new URLClassLoader(new URL[]{toURL().apply(outputDirectory)});

    private EvaluationContext context = EvaluationContext.emptyContext();

    public EvaluationContext context() {
        return context;
    }

    public void evaluate(String expr) {
        Option<Result> results = context.resultByKey(expr);
        if(!results.isEmpty()) {
            System.out.println(results.get().getValue());
            return;
        }

        Option<Pair<Integer, String>> error = compileAndRun(expr, true);
        if(!error.equals(none())) {
            error = compileAndRun(expr, false);
        }

        if(!error.equals(none())) {
            System.err.println(error.get().second());
        }
    }

    private Option<Pair<Integer, String>> compileAndRun(String expr, boolean asAssignment) {
        String className = randomIdentifier(getClass().getSimpleName());
        String sources = renderClass(model()
                .add("isAssignment", asAssignment)
                .add("className", className)
                .add("context", contextModel())
                .add("expression", expr));
        File outputJavaFile = file(outputDirectory, className + ".java");

        try {
            Files.write(sources.getBytes(), outputJavaFile);

            OutputStream errors = new ByteArrayOutputStream();
            int errorCode = javaCompiler.run(null, null, errors, outputJavaFile.getCanonicalPath());

            if (errorCode != 0)
                return some(pair(errorCode, errors.toString()));

            Class<?> expressionClass = classLoader.loadClass(className);
            Object expressionInstance = expressionClass.newInstance();

            expressionClass.getMethod("$init", EvaluationContext.class).invoke(expressionInstance, context);

            Object result = expressionClass.getMethod("$eval").invoke(expressionInstance);

            if (result != null) {
                String nextVar = context.nextVal();
                System.out.println(nextVar + " = " + result);
                context = context.addEvaluation(expression(expr, className, sources), result(nextVar, result));
            }
        } catch (Exception e) {
            context = context.addEvaluation(expression(expr, className, sources), result(context.nextVal()));
            return some(pair(-1000, e.getMessage()));
        }

        return none();
    }

    private Object contextModel() {
        return model()
                .add("evaluations",
                        context.getEvaluations().map(new Function1<Pair<Expression, Result>, Object>() {
                            public Object call(Pair<Expression, Result> expressionResultPair) throws Exception {
                                return model()
                                        .add("expression", expressionResultPair.first())
                                        .add("result", expressionResultPair.second());
                            }
                        }));
    }


    private static String randomIdentifier(String prefix) {
        return prefix + "$" + takeFromValues(characters("abcdefghijklmnopqrstuvwxyz1234567890")).take(20).toString("");
    }

    private static String renderClass(Model model) {
        try {
            return new StringTemplateFunclate(REPL.class)
                    .get("EvaluationTemplate")
                    .render(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
