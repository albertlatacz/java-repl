package javarepl;

import com.googlecode.totallylazy.Either;
import com.googlecode.totallylazy.Files;
import com.googlecode.totallylazy.Option;

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
import static javarepl.EvaluationClassRenderer.renderEvaluationClass;
import static javarepl.EvaluationContext.emptyEvaluationContext;
import static javarepl.Expression.*;
import static javarepl.ExpressionValidators.*;
import static javarepl.Utils.randomIdentifier;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class Evaluator {
    private final File outputDirectory = temporaryDirectory("JavaREPL");
    private final ClassLoader classLoader = new URLClassLoader(new URL[]{toURL().apply(outputDirectory)});

    private EvaluationContext context = emptyEvaluationContext();

    public Either<? extends Throwable, Evaluation> evaluate(String expr) {
        Option<Evaluation> evaluationOption = context.evaluationForResult(expr);
        if (!evaluationOption.isEmpty()) {
            return right(evaluationOption.get());
        }

        Either<? extends Throwable, Evaluation> result = evaluate(createExpression(expr));
        if (result.isLeft() && result.left() instanceof ExpressionCompilationException) {
            result = evaluate(new Statement(expr));
        }

        return result;

    }

    private Expression createExpression(String expr) {
        if (isValidImport(expr))
            return new Import(expr);

        if (isValidClassOrInterface(expr))
            return new ClassOrInterface(expr);

        if (isValidAssignmentWithType(expr)) {
            return new AssignmentWithType(expr);
        }

        if (isValidAssignment(expr)) {
            return new Assignment(expr);
        }

        return new Value(expr);
    }


    private Either<? extends Throwable, Evaluation> evaluate(Expression expression) {
        String className = randomIdentifier("Evaluation");
        File outputJavaFile = file(outputDirectory, className + ".java");
        File outputClassFile = file(outputDirectory, className + ".class");

        try {
            String sources = renderEvaluationClass(context, className, expression);
            Files.write(sources.getBytes(), outputJavaFile);

            compile(outputJavaFile);

            Class<?> expressionClass = classLoader.loadClass(className);
            Object expressionInstance = expressionClass.newInstance();
            expressionClass.getMethod("init", EvaluationContext.class).invoke(expressionInstance, context);
            Object resultObject = expressionClass.getMethod("evaluate").invoke(expressionInstance);

            if (resultObject != null) {
                Evaluation evaluation = evaluation(className, sources, expression, some(Result.result(nextResultKeyFor(expression), resultObject)));
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

    private String nextResultKeyFor(Expression expression) {
        if (expression instanceof Assignment)
            return ((Assignment)expression).key;

        if (expression instanceof AssignmentWithType)
            return ((AssignmentWithType)expression).key;

        if (expression instanceof ClassOrInterface)
            return ((ClassOrInterface)expression).type;

        return context.nextResultKey();
    }

    private void compile(File file) throws Exception {
        OutputStream errorStream = new ByteArrayOutputStream();

        int errorCode = getSystemJavaCompiler().run(null, null, errorStream, file.getCanonicalPath());

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
