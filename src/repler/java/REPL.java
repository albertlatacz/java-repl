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
import static repler.java.EvaluationRenderer.randomIdentifier;
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

    public Either<? extends Throwable, Option<Result>> evaluate(String expr) {
        Option<Result> result = context.resultByKey(expr);
        if(!result.isEmpty()) {
            return right(some(result.get()));
        }

        Either<? extends Throwable, Option<Result>> resultEither = evaluate(expr, true);
        if(resultEither.isLeft() && resultEither.left() instanceof ExpressionCompilationException) {
            resultEither = evaluate(expr, false);
        }

        return resultEither;
    }

    private Either<? extends Throwable, Option<Result>> evaluate(String expr, boolean asAssignment) {
        String className = randomIdentifier(getClass().getSimpleName());
        String sources = EvaluationRenderer.render(context, className, expr, asAssignment);
        File outputJavaFile = file(outputDirectory, className + ".java");
        File outputClassFile = file(outputDirectory, className + ".class");

        try {
            Files.write(sources.getBytes(), outputJavaFile);

            compile(outputJavaFile.getCanonicalPath());

            Class<?> expressionClass = classLoader.loadClass(className);
            Object expressionInstance = expressionClass.newInstance();

            expressionClass.getMethod("$init", EvaluationContext.class).invoke(expressionInstance, context);

            Object resultObject = expressionClass.getMethod("$eval").invoke(expressionInstance);

            String nextVar = context.nextVal();
            if (resultObject != null) {
                Result result = result(nextVar, resultObject);
                context = context.addEvaluation(expression(expr, className, sources), result);
                return right(some(result));
            } else {
                context = context.addEvaluation(expression(expr, className, sources), Result.empty(nextVar));
                return right(emptyResult());
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

    public static Option<Result> emptyResult() {
        return none();
    }

}
