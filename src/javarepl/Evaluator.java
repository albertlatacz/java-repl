package javarepl;

import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Files;
import com.googlecode.totallylazy.Option;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;

import static com.googlecode.totallylazy.Either.left;
import static com.googlecode.totallylazy.Either.right;
import static com.googlecode.totallylazy.Files.file;
import static com.googlecode.totallylazy.Files.temporaryDirectory;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.URLs.toURL;
import static javarepl.Evaluation.evaluation;
import static javarepl.EvaluationContext.emptyEvaluationContext;
import static javarepl.Expression.*;
import static javarepl.Utils.isValidAssignment;

public class Evaluator {
    private final File outputDirectory = temporaryDirectory(getClass().getSimpleName());

    private final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
    private final ClassLoader classLoader = new URLClassLoader(new URL[]{toURL().apply(outputDirectory)});

    private EvaluationContext context = emptyEvaluationContext();

    public Either<? extends Throwable, Evaluation> evaluate(String expr) {
        Option<Evaluation> evaluationOption = context.evaluationForResult(expr);
        if (!evaluationOption.isEmpty()) {
            return right(evaluationOption.get());
        }

        if (expr.startsWith("import ")) {
            return evaluate(new Import(expr));
        } else if (isValidAssignment(expr)) {
            return evaluate(new Assignment(expr));
        } else {
            Either<? extends Throwable, Evaluation> result = evaluate(new Value(expr));
            if (result.isLeft() && result.left() instanceof ExpressionCompilationException) {
                result = evaluate(new Statement(expr));
            }

            return result;
        }
    }


    private Either<? extends Throwable, Evaluation> evaluate(Expression expression) {
        String className = Utils.randomIdentifier(getClass().getSimpleName());
        String sources = EvaluationRenderer.render(context, className, expression);
        File outputJavaFile = file(outputDirectory, className + ".java");
        File outputClassFile = file(outputDirectory, className + ".class");

        try {
            Files.write(sources.getBytes(), outputJavaFile);

            compile(outputJavaFile.getCanonicalPath());

            Class<?> expressionClass = classLoader.loadClass(className);
            Object expressionInstance = expressionClass.newInstance();
            expressionClass.getMethod("init", EvaluationContext.class).invoke(expressionInstance, context);
            Object resultObject = expressionClass.getMethod("evaluate").invoke(expressionInstance);

            if (resultObject != null) {
                String key = (expression instanceof Assignment) ? ((Assignment)expression).key : context.nextResultKey();
                Result result = Result.result(key, resultObject);
                Evaluation evaluation = evaluation(className, sources, expression, some(result));
                context = context.addEvaluation(evaluation);
                return right(evaluation);
            } else {
                Evaluation evaluation = evaluation(className, sources, expression, Result.noResult());
                context = context.addEvaluation(evaluation);
                return right(evaluation);
            }
        } catch (Throwable e) {
            return left(Utils.unwrapException(e));
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

    public Option<Evaluation> lastEvaluation() {
        return context.lastEvaluation();
    }

    public void clear() {
        context = emptyEvaluationContext();
    }
}
