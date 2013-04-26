package javarepl;

import com.googlecode.totallylazy.*;
import com.googlecode.totallylazy.annotations.multimethod;
import javarepl.expressions.*;
import javarepl.expressions.Value;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;

import static com.googlecode.totallylazy.Callables.toString;
import static com.googlecode.totallylazy.Either.left;
import static com.googlecode.totallylazy.Either.right;
import static com.googlecode.totallylazy.Files.*;
import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.predicates.Not.not;
import static java.io.File.pathSeparator;
import static java.util.Arrays.asList;
import static javarepl.Evaluation.evaluation;
import static javarepl.EvaluationClassLoader.evaluationClassLoader;
import static javarepl.EvaluationContext.evaluationContext;
import static javarepl.Result.functions.value;
import static javarepl.Utils.randomIdentifier;
import static javarepl.expressions.Patterns.*;
import static javarepl.rendering.EvaluationClassRenderer.renderExpressionClass;
import static javax.tools.ToolProvider.getSystemJavaCompiler;

public class Evaluator {

    private EvaluationClassLoader classLoader;
    private EvaluationContext context;

    private File outputDirectory;

    public Evaluator() {
        initializeEvaluator(evaluationContext());
    }

    private Evaluator(EvaluationContext context) {
        initializeEvaluator(context);
    }

    public Either<? extends Throwable, Evaluation> evaluate(String expr) {
        Expression expression = createExpression(expr);
        Either<? extends Throwable, Evaluation> result = evaluate(expression);
        if (result.isLeft() && result.left() instanceof ExpressionCompilationException && expression instanceof Value) {
            Either<? extends Throwable, Evaluation> resultForStatement = evaluate(new Statement(expr));
            return resultForStatement.isLeft() && resultForStatement.left() instanceof ExpressionCompilationException
                    ? result
                    : resultForStatement;
        }

        return result;
    }

    public Option<Evaluation> lastEvaluation() {
        return context.lastEvaluation();
    }

    public List<Result> results() {
        return context.results().toList();
    }

    public Option<Result> result(String name) {
        return context.result(name);
    }

    public <T extends Expression> Sequence<T> expressionsOfType(Class<T> type) {
        return context.expressionsOfType(type);
    }

    public Sequence<Evaluation> evaluations() {
        return context.evaluations();
    }

    public void reset() {
        clearOutputDirectory();
        initializeEvaluator(evaluationContext());
    }

    private void initializeEvaluator(EvaluationContext evaluationContext) {
        context = evaluationContext;
        outputDirectory = randomOutputDirectory();
        classLoader = evaluationClassLoader(outputDirectory);
    }

    public final Option<Class> typeOfExpression(String expression) {
        Evaluator localEvaluator = new Evaluator(context);
        Either<? extends Throwable, Evaluation> evaluation = localEvaluator.evaluate(expression);

        Option<Class> expressionType = none();
        if (evaluation.isRight()) {
            Option<Result> result = evaluation.right().result();
            if (!result.isEmpty()) {
                expressionType = some((Class) result.get().value().getClass());
            }
        }

        localEvaluator.clearOutputDirectory();
        return expressionType;
    }

    public void clearOutputDirectory() {
        deleteFiles(outputDirectory);
        delete(outputDirectory);
    }

    public void addClasspathUrl(URL classpathUrl) {
        classLoader.addURL(classpathUrl);
    }

    public File outputDirectory() {
        return outputDirectory;
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
        return new multi() {
        }.<Either<? extends Throwable, Evaluation>>
                methodOption(expression).getOrElse(evaluateExpression(expression));
    }

    @multimethod
    private Either<? extends Throwable, Evaluation> evaluate(Type expression) {
        if (classLoader.isClassLoaded(expression.canonicalName())) {
            return left(new UnsupportedOperationException("Redefining classes not supported"));
        }

        try {
            File outputPath = directory(outputDirectory, expression.typePackage().getOrElse("").replace('.', File.separatorChar));
            File outputJavaFile = file(outputPath, expression.type() + ".java");

            String sources = renderExpressionClass(context, expression.type(), expression);
            Files.write(sources.getBytes(), outputJavaFile);
            compile(outputJavaFile);

            classLoader.loadClass(expression.canonicalName());

            Evaluation evaluation = evaluation(expression.type(), sources, expression, Result.noResult());
            context = context.addEvaluation(evaluation);

            return right(evaluation);
        } catch (Exception e) {
            return left(Utils.unwrapException(e));
        }
    }

    private Either<? extends Throwable, Evaluation> evaluateExpression(final Expression expression) {
        final String className = randomIdentifier("Evaluation");

        try {
            File outputJavaFile = file(outputDirectory, className + ".java");
            final String sources = renderExpressionClass(context, className, expression);
            Files.write(sources.getBytes(), outputJavaFile);

            compile(outputJavaFile);

            Class<?> expressionClass = classLoader.loadClass(className);
            Constructor<?> constructor = expressionClass.getDeclaredConstructor(EvaluationContext.class);
            final Object expressionInstance = constructor.newInstance(context);

            Object resultObject = expressionClass.getMethod("evaluate").invoke(expressionInstance);

            Sequence<Evaluation> modifiedResults = sequence(expressionInstance.getClass().getDeclaredFields())
                    .foldLeft(Sequences.<Evaluation>empty(), new Function2<Sequence<Evaluation>, Field, Sequence<Evaluation>>() {
                        public Sequence<Evaluation> call(Sequence<Evaluation> evaluations, Field field) throws Exception {
                            Option<Result> result = result(field.getName()).filter(where(value(), not(equalTo(field.get(expressionInstance)))));

                            if (result.isEmpty())
                                return evaluations;

                            return evaluations.add(evaluation(className, sources,
                                    new ExternalAssignment(expression.source(), field.getName(), result.value(), field.get(expressionInstance)),
                                    some(Result.result(field.getName(), field.get(expressionInstance)))));

                        }
                    });


            if (resultObject != null) {
                Evaluation evaluation = evaluation(className, sources, expression, some(Result.result(nextResultKeyFor(expression), resultObject)));
                context = context.addEvaluations(modifiedResults.add(evaluation));
                return right(evaluation);
            } else {
                Evaluation evaluation = evaluation(className, sources, expression, Result.noResult());

                context = context.addEvaluations(modifiedResults.add(evaluation));
                return right(evaluation);
            }
        } catch (Throwable e) {
            return left(Utils.unwrapException(e));
        }
    }

    private String nextResultKeyFor(Expression expression) {
        return (expression instanceof WithKey)
                ? ((WithKey) expression).key()
                : context.nextResultKey();
    }

    private void compile(File file) throws Exception {
        String classpath = sequence(System.getProperty("java.class.path")).join(sequence(classLoader.getURLs()).map(toString)).toString(pathSeparator);
        JavaCompiler compiler = getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(asList(file));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, asList("-cp", classpath), null, compilationUnits);

        try {
            if (!task.call())
                throw new ExpressionCompilationException(file, diagnostics.getDiagnostics());
        } finally {
            fileManager.close();
        }
    }

    private static File randomOutputDirectory() {
        File file = temporaryDirectory("JavaREPL/" + randomFilename());
        file.deleteOnExit();
        return file;
    }
}
