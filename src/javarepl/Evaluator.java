package javarepl;

import com.googlecode.totallylazy.*;
import com.googlecode.totallylazy.annotations.multimethod;
import javarepl.expressions.*;
import javarepl.expressions.Method;
import javarepl.expressions.Type;
import javarepl.expressions.Value;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.net.URL;
import java.util.regex.MatchResult;

import static com.googlecode.totallylazy.Callables.toString;
import static com.googlecode.totallylazy.Either.left;
import static com.googlecode.totallylazy.Either.right;
import static com.googlecode.totallylazy.Files.*;
import static com.googlecode.totallylazy.Option.*;
import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.predicates.Not.not;
import static java.io.File.pathSeparator;
import static javarepl.Evaluation.evaluation;
import static javarepl.EvaluationClassLoader.evaluationClassLoader;
import static javarepl.EvaluationContext.evaluationContext;
import static javarepl.Result.functions.value;
import static javarepl.Result.noResult;
import static javarepl.Utils.randomIdentifier;
import static javarepl.Utils.randomOutputDirectory;
import static javarepl.expressions.Patterns.*;
import static javarepl.rendering.EvaluationClassRenderer.renderExpressionClass;
import static javarepl.rendering.EvaluationClassRenderer.renderMethodSignatureDetection;
import static javarepl.rendering.ExpressionSourceRenderer.renderExpressionSource;
import static javarepl.rendering.ExpressionTokenRenderer.EXPRESSION_TOKEN;
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
        Expression expression = parseExpression(expr);
        Either<? extends Throwable, Evaluation> result = evaluate(expression);
        if (result.isLeft() && result.left() instanceof ExpressionCompilationException && expression instanceof Value) {
            Either<? extends Throwable, Evaluation> resultForStatement = evaluate(new Statement(expr));
            return resultForStatement.isLeft() && resultForStatement.left() instanceof ExpressionCompilationException
                    ? result
                    : resultForStatement;
        }

        return result;
    }

    public Expression parseExpression(String expression) {
        if (isValidImport(expression))
            return createImport(expression);

        if (isValidType(expression))
            return createTypeExpression(expression);

        if (isValidMethod(expression))
            return createMethodExpression(expression);

        if (isValidAssignmentWithType(expression))
            return createAssignmentWithType(expression);

        if (isValidAssignment(expression))
            return createAssignmentExpression(expression);

        return new Value(expression);
    }

    public void addResults(Sequence<Result> result) {
        context = context.addResults(result);
    }

    private Import createImport(String expression) {
        return new Import(expression, importPattern.match(expression).group(1));
    }

    private AssignmentWithType createAssignmentWithType(String expression) {
        try {
            MatchResult match = Patterns.assignmentWithTypeNamePattern.match(expression);
            java.lang.reflect.Method declaredMethod = detectMethod(match.group(1) + " " + randomIdentifier("method") + "(){}");
            return new AssignmentWithType(expression, declaredMethod.getReturnType(), match.group(2), match.group(3));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Assignment createAssignmentExpression(String expression) {
        MatchResult match = Patterns.assignmentPattern.match(expression);
        return new Assignment(expression, match.group(1), match.group(2));
    }

    private Type createTypeExpression(String expression) {
        MatchResult match = typePattern.match(expression);
        return new Type(expression, option(match.group(1)), match.group(2));
    }

    public Option<String> lastSource() {
        return context.lastSource();
    }

    public Sequence<Result> results() {
        return context.results();
    }

    public Option<Result> result(String name) {
        return context.result(name);
    }

    public <T extends Expression> Sequence<T> expressionsOfType(Class<T> type) {
        return context.expressionsOfType(type);
    }

    public Sequence<Expression> expressions() {
        return context.expressions();
    }

    public EvaluationContext context() {
        return context;
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
                expressionType = some((Class) result.get().type());
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

    @multimethod
    private Either<? extends Throwable, Evaluation> evaluate(Expression expression) {
        return new multi() {
        }.<Either<? extends Throwable, Evaluation>>methodOption(expression).getOrElse(evaluateExpression(expression));
    }

    @multimethod
    private Either<? extends Throwable, Evaluation> evaluate(Type expression) {
        if (classLoader.isClassLoaded(expression.canonicalName())) {
            return left(new UnsupportedOperationException("Redefining classes not supported"));
        }

        if (getSystemJavaCompiler() == null) {
            return left(new FileNotFoundException("Java compiler not found." +
                    "This can occur when JavaREPL was run with JRE instead of JDK or JDK is not configured correctly."));
        }

        try {
            File outputPath = directory(outputDirectory, expression.typePackage().getOrElse("").replace('.', File.separatorChar));
            File outputJavaFile = file(outputPath, expression.type() + ".java");

            String sources = renderExpressionClass(context, expression.type(), expression)
                    .replace(EXPRESSION_TOKEN, renderExpressionSource(expression));

            Files.write(sources.getBytes(), outputJavaFile);
            compile(outputJavaFile);

            classLoader.loadClass(expression.canonicalName());

            context = context.addExpression(expression).lastSource(sources);

            return right(evaluation(expression, noResult()));
        } catch (Exception e) {
            return left(Utils.unwrapException(e));
        }
    }

    private Method createMethodExpression(String expression) {
        try {
            java.lang.reflect.Method declaredMethod = detectMethod(expression);
            return new Method(expression, declaredMethod.getReturnType(), declaredMethod.getName(), sequence(declaredMethod.getParameterTypes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private java.lang.reflect.Method detectMethod(String expression) throws Exception {
        final String className = randomIdentifier("Method");
        final File outputJavaFile = file(outputDirectory, className + ".java");

        final String sources = renderMethodSignatureDetection(context, className, expression);
        Files.write(sources.getBytes(), outputJavaFile);

        compile(outputJavaFile);

        Class<?> expressionClass = classLoader.loadClass(className);

        return expressionClass.getDeclaredMethods()[0];
    }

    private Either<? extends Throwable, Evaluation> evaluateExpression(final Expression expression) {
        final String className = randomIdentifier("Evaluation");

        try {
            EvaluationContext newContext = context.removeExpressionWithKey(expression.key());

            File outputJavaFile = file(outputDirectory, className + ".java");
            final String sources = renderExpressionClass(newContext, className, expression)
                    .replace(EXPRESSION_TOKEN, renderExpressionSource(expression));

            Files.write(sources.getBytes(), outputJavaFile);

            compile(outputJavaFile);

            Class<?> expressionClass = classLoader.loadClass(className);
            Constructor<?> constructor = expressionClass.getDeclaredConstructor(EvaluationContext.class);

            final Object expressionInstance = constructor.newInstance(newContext);

            java.lang.reflect.Method method = expressionClass.getMethod("evaluate");
            Object resultObject = method.invoke(expressionInstance);

            Sequence<Result> modifiedResults = modifiedResults(expressionInstance);

            if (resultObject != null || !method.getReturnType().equals(void.class)) {
                Result result = Result.result(nextResultKeyFor(expression), resultObject, typeFor(expression));
                context = newContext.addExpression(expression).addResults(modifiedResults.add(result)).lastSource(sources);
                return right(evaluation(expression, some(result)));
            } else {
                context = newContext.addExpression(expression).addResults(modifiedResults).lastSource(sources);
                return right(evaluation(expression, noResult()));
            }
        } catch (Throwable e) {
            return left(Utils.unwrapException(e));
        }
    }

    private Sequence<Result> modifiedResults(final Object expressionInstance) {
        return sequence(expressionInstance.getClass().getDeclaredFields())
                .reduceLeft(new Reducer<Field, Sequence<Result>>() {
                    public Sequence<Result> call(Sequence<Result> results, Field field) throws Exception {
                        Option<Result> result = result(field.getName()).filter(where(value(), not(equalTo(field.get(expressionInstance)))));

                        if (result.isEmpty())
                            return results;

                        return results.add(Result.result(field.getName(), field.get(expressionInstance)));

                    }

                    public Sequence<Result> identity() {
                        return empty(Result.class);
                    }
                });
    }

    private String nextResultKeyFor(Expression expression) {
        return (expression instanceof Value)
                ? context.nextResultKey()
                : expression.key();
    }

    private Option<Class<?>> typeFor(Expression expression) {
        return (expression instanceof AssignmentWithType)
            ? Option.<Class<?>>some(((AssignmentWithType) expression).type())
            : Option.<Class<?>>none();
    }

    private void compile(File file) throws Exception {
        String classpath = sequence(System.getProperty("java.class.path")).join(sequence(classLoader.getURLs()).map(toString)).toString(pathSeparator);
        JavaCompiler compiler = getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sequence(file));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, sequence("-cp", classpath), null, compilationUnits);

        try {
            if (!task.call())
                throw new ExpressionCompilationException(file, diagnostics.getDiagnostics());
        } finally {
            fileManager.close();
        }
    }

}
