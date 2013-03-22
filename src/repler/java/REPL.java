package repler.java;

import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Files;
import com.googlecode.totallylazy.Option;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import static com.googlecode.totallylazy.Either.left;
import static com.googlecode.totallylazy.Either.right;
import static com.googlecode.totallylazy.Files.file;
import static com.googlecode.totallylazy.Files.temporaryDirectory;
import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.URLs.toURL;
import static repler.java.Evaluation.evaluation;
import static repler.java.EvaluationContext.emptyEvaluationContext;
import static repler.java.Expression.Type.EVALUATION;
import static repler.java.Expression.Type.IMPORT;
import static repler.java.Expression.expression;
import static repler.java.Result.noResult;
import static repler.java.Result.result;
import static repler.java.Utils.randomIdentifier;

public class REPL {

    private final File outputDirectory = temporaryDirectory(getClass().getSimpleName());

    private final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
    private final ClassLoader classLoader = new URLClassLoader(new URL[]{toURL().apply(outputDirectory)});
    private EvaluationContext context = emptyEvaluationContext();

    public EvaluationContext context() {
        return context;
    }

    public void clear() {
        context = emptyEvaluationContext();
    }

    public Either<? extends Throwable, Evaluation> evaluate(String expr) {
        Option<Evaluation> evaluationOption = context.evaluationForResult(expr);
        if (!evaluationOption.isEmpty()) {
            return right(evaluationOption.get());
        }

        Boolean isImport = expr.startsWith("import ");

        if (isImport) {
            return evaluate(expr, false, true);
        } else {
            Either<? extends Throwable, Evaluation> result = evaluate(expr, true, false);
            if (result.isLeft() && result.left() instanceof ExpressionCompilationException) {
                result = evaluate(expr, false, false);
            }

            return result;
        }
    }

    private Either<? extends Throwable, Evaluation> evaluate(String expr, boolean asAssignment, boolean asImport) {
        String className = randomIdentifier(getClass().getSimpleName());
        String sources = EvaluationRenderer.render(context, className, expr, asAssignment, asImport);
        File outputJavaFile = file(outputDirectory, className + ".java");
        File outputClassFile = file(outputDirectory, className + ".class");

        try {
            Files.write(sources.getBytes(), outputJavaFile);

            compile(outputJavaFile.getCanonicalPath());

            Class<?> expressionClass = classLoader.loadClass(className);
            Object expressionInstance = expressionClass.newInstance();

            expressionClass.getMethod("init", EvaluationContext.class).invoke(expressionInstance, context);

            Object resultObject = expressionClass.getMethod("evaluate").invoke(expressionInstance);

            String nextVar = context.nextResultKey();

            if (asImport) {
                Evaluation evaluation = evaluation(className, sources, expression(expr, IMPORT), noResult());
                context = context.addEvaluation(evaluation);
                return right(evaluation);
            } else {
                if (resultObject != null) {
                    Result result = result(nextVar, resultObject);
                    Evaluation evaluation = evaluation(className, sources, expression(expr, EVALUATION), some(result));
                    context = context.addEvaluation(evaluation);
                    return right(evaluation);
                } else {
                    Evaluation evaluation = evaluation(className, sources, expression(expr, EVALUATION), noResult());
                    context = context.addEvaluation(evaluation);
                    return right(evaluation);
                }
            }
        } catch (Throwable e) {
            return left(unwrapException(e));
        } finally {
            outputJavaFile.delete();
            outputClassFile.delete();
        }
    }

    private void compile(String path) throws ExpressionCompilationException {
        OutputStream errorStream = new ByteArrayOutputStream();

        int errorCode = javaCompiler.run(null, null, errorStream, path);

        if (errorCode != 0)
            throw new ExpressionCompilationException(errorCode, errorStream.toString());
    }

    public static Throwable unwrapException(Throwable e) {
        if (e instanceof InvocationTargetException)
            return unwrapException(((InvocationTargetException) e).getTargetException());

        return e;
    }

}
