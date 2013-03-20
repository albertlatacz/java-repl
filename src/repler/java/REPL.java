package repler.java;

import com.googlecode.funclate.Model;
import com.googlecode.funclate.stringtemplate.StringTemplateFunclate;
import com.googlecode.totallylazy.Files;
import com.googlecode.totallylazy.Function1;
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
        String className = randomIdentifier(getClass().getSimpleName());
        File outputJavaFile = file(outputDirectory, className + ".java");

        String sources = renderClass(model()
                .add("className", className)
                .add("context", contextModel())
                .add("expression", "$ = " + expr));
        Files.write(sources.getBytes(), outputJavaFile);

        try {
            OutputStream errors = new ByteArrayOutputStream();

            int error = javaCompiler.run(null, null, errors, outputJavaFile.getCanonicalPath());
            if (error != 0) {
                className = randomIdentifier(getClass().getSimpleName());
                outputJavaFile = file(outputDirectory, className + ".java");
                sources = renderClass(model()
                        .add("className", className)
                        .add("context", contextModel())
                        .add("expression", expr));
                Files.write(sources.getBytes(), outputJavaFile);

                javaCompiler.run(null, null, errors, outputJavaFile.getCanonicalPath());
            }

            Class<?> expressionClass = classLoader.loadClass(className);
            Object expressionInstance = expressionClass.newInstance();

            expressionClass.getMethod("$init", EvaluationContext.class).invoke(expressionInstance, context);

            Object result = expressionClass.getMethod("$eval").invoke(expressionInstance);

            if (result != null) {
                String nextVar = context.nextVal();
                System.out.println(nextVar + " = " + result);
                context = context.add(expression(expr, className, sources), result(nextVar, result));
            }
        } catch (Exception e) {
            context = context.add(expression(expr, className, sources), result(context.nextVal()));
            e.printStackTrace();
        }
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
                    .get("template")
                    .render(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static abstract class $Context {
        private EvaluationContext context;

        public final void $init(EvaluationContext context) {
            this.context = context;
        }

        @SuppressWarnings("unchecked")
        public final <T> T $val(final String key) {
            return (T) context.resultByKey(key).get().getValue();
        }

        public abstract Object $eval();
    }


}
