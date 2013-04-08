package javarepl;

import com.googlecode.totallylazy.*;
import com.googlecode.totallylazy.annotations.multimethod;
import javarepl.expressions.*;
import javarepl.expressions.Value;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.List;

import static com.googlecode.totallylazy.Callables.toString;
import static com.googlecode.totallylazy.Either.left;
import static com.googlecode.totallylazy.Either.right;
import static com.googlecode.totallylazy.Files.file;
import static com.googlecode.totallylazy.Files.temporaryDirectory;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Predicates.instanceOf;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.URLs.toURL;
import static java.io.File.pathSeparator;
import static javarepl.Evaluation.evaluation;
import static javarepl.Evaluation.functions.expression;
import static javarepl.EvaluationContext.emptyEvaluationContext;
import static javarepl.Utils.randomIdentifier;
import static javarepl.expressions.Patterns.*;
import static javarepl.rendering.EvaluationClassRenderer.renderExpressionClass;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class Evaluator {
    private final File outputDirectory = temporaryDirectory("JavaREPL");
    private final EvaluationClassLoader classLoader = new EvaluationClassLoader(new URL[]{toURL().apply(outputDirectory)});

    private EvaluationContext context = emptyEvaluationContext();

    public Either<? extends Throwable, Evaluation> evaluate(String expr) {
        Option<Evaluation> evaluationOption = context.evaluationForResult(expr);
        if (!evaluationOption.isEmpty()) {
            return right(evaluationOption.get());
        }

        Expression expression = createExpression(expr);
        Either<? extends Throwable, Evaluation> result = evaluate(expression);
        if (result.isLeft() && result.left() instanceof ExpressionCompilationException && expression instanceof  Value) {
            result = evaluate(new Statement(expr));
        }

        return result;
    }

    public Option<Evaluation> lastEvaluation() {
        return context.lastEvaluation();
    }

    public List<Result> results() {
        return context.results().toList();
    }

    public <T extends Expression> Sequence<T> expressionsOfType(Class<T> type) {
        return context.expressionsOfType(type);
    }

    public Sequence<Evaluation> evaluations() {
        return context.evaluations();
    }

    public void clear() {
        context = emptyEvaluationContext();
    }

    public void addClasspathUrl(URL classpathUrl) {
        classLoader.addURL(classpathUrl);
    }

    private Expression createExpression(String expr) {
        if (isValidImport(expr))
            return new Import(expr);

        if (isValidType(expr))
            return new Type(expr);

        if (isValidMethod(expr))
            return new Method(expr);

        if (isValidAssignmentWithType(expr))
            return new AssignmentWithType(expr);


        if (isValidAssignment(expr))
            return new Assignment(expr);


        return new Value(expr);
    }

    @multimethod
    private Either<? extends Throwable, Evaluation> evaluate(Expression expression) {
        return new multi() {}.<Either<? extends Throwable, Evaluation>>
                methodOption(expression).getOrElse(evaluateExpression(expression));
    }

    @multimethod
    private Either<? extends Throwable, Evaluation> evaluate(Type expression) {
        if (classLoader.isClassLoaded(expression.type())) {
            return left(new UnsupportedOperationException("Redefining classes not supported"));
        }

        try {
            File outputJavaFile = file(outputDirectory, expression.type() + ".java");

            String sources = renderExpressionClass(context, expression.type(), expression);
            Files.write(sources.getBytes(), outputJavaFile);
            compile(outputJavaFile);
            classLoader.loadClass(expression.type());

            Evaluation evaluation = evaluation(expression.type(), sources, expression, Result.noResult());
            context = context.addEvaluation(evaluation);

            return right(evaluation);
        } catch (Exception e) {
            return left(Utils.unwrapException(e));
        }
    }

    private Either<? extends Throwable, Evaluation> evaluateExpression(Expression expression) {
        String className = randomIdentifier("Evaluation");
        File outputJavaFile = file(outputDirectory, className + ".java");
        File outputClassFile = file(outputDirectory, className + ".class");

        try {
            String sources = renderExpressionClass(context, className, expression);
            Files.write(sources.getBytes(), outputJavaFile);

            compile(outputJavaFile);

            Class<?> expressionClass = classLoader.loadClass(className);
            Constructor<?> constructor = expressionClass.getDeclaredConstructor(EvaluationContext.class);
            Object expressionInstance = constructor.newInstance(context);

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
        return (expression instanceof WithKey)
                ? ((WithKey) expression).key()
                : context.nextResultKey();
    }

    private void compile(File file) throws Exception {
        OutputStream errorStream = new ByteArrayOutputStream();
        String classpath = sequence(System.getProperty("java.class.path"))
                .join(sequence(classLoader.getURLs()).map(toString)).toString(pathSeparator);

        int errorCode = getSystemJavaCompiler().run(null, null, errorStream, "-cp", classpath, file.getCanonicalPath());

        if (errorCode != 0)
            throw new ExpressionCompilationException(errorCode, errorStream.toString());
    }
}
