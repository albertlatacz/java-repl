package javarepl.rendering;

import com.googlecode.totallylazy.Sequences;
import javarepl.EvaluationContext;
import javarepl.expressions.*;
import org.junit.Test;

import static javarepl.EvaluationContext.evaluationContext;
import static javarepl.Result.result;
import static javarepl.rendering.EvaluationClassRenderer.renderExpressionClass;
import static javarepl.rendering.EvaluationClassRenderer.renderMethodSignatureDetection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EvaluationClassRendererTest {

    @Test
    public void rendersTemplateForStatement() {
        assertThat(renderExpressionClass(evaluationContext(), "AClass", new Statement("someStatement")), is(new StringBuilder()
                .append("import java.lang.*;\n")
                .append("import java.util.*;\n")
                .append("import java.math.*;\n")
                .append("import static java.lang.Math.*;\n")
                .append("public final class AClass extends javarepl.EvaluationTemplate {\n")
                .append("  public AClass(javarepl.EvaluationContext context) { super(context); }\n")
                .append("\n")
                .append("\n")
                .append("  public void evaluate() throws Exception {\n")
                .append("    $JAVAREPL_EXPRESSION_TOKEN$;\n")
                .append("  }\n")
                .append("}\n")
                .toString()));
    }

    @Test
    public void rendersTemplateForValue() {
        assertThat(renderExpressionClass(evaluationContext(), "AClass", new Value("someValue")), is(new StringBuilder()
                .append("import java.lang.*;\n")
                .append("import java.util.*;\n")
                .append("import java.math.*;\n")
                .append("import static java.lang.Math.*;\n")
                .append("public final class AClass extends javarepl.EvaluationTemplate {\n")
                .append("  public AClass(javarepl.EvaluationContext context) { super(context); }\n")
                .append("\n")
                .append("\n")
                .append("  public Object evaluate() throws Exception {\n")
                .append("    Object $JAVAREPL_EXPRESSION_VALUE$ =\n")
                .append("\n")
                .append("    $JAVAREPL_EXPRESSION_TOKEN$;\n")
                .append("\n")
                .append("    return $JAVAREPL_EXPRESSION_VALUE$;\n")
                .append("  }\n")
                .append("}\n")
                .toString()));
    }

    @Test
    public void rendersTemplateForAssignment() {
        assertThat(renderExpressionClass(evaluationContext(), "AClass", new Assignment("someAssignment", "someKey", "someValue")), is(new StringBuilder()
                .append("import java.lang.*;\n")
                .append("import java.util.*;\n")
                .append("import java.math.*;\n")
                .append("import static java.lang.Math.*;\n")
                .append("public final class AClass extends javarepl.EvaluationTemplate {\n")
                .append("  public AClass(javarepl.EvaluationContext context) { super(context); }\n")
                .append("\n")
                .append("\n")
                .append("  public Object evaluate() throws Exception {\n")
                .append("    Object $JAVAREPL_EXPRESSION_VALUE$ =\n")
                .append("\n")
                .append("    $JAVAREPL_EXPRESSION_TOKEN$;\n")
                .append("\n")
                .append("    return $JAVAREPL_EXPRESSION_VALUE$;\n")
                .append("  }\n")
                .append("}\n")
                .toString()));
    }

    @Test
    public void rendersTemplateForAssignmentWithType() {
        assertThat(renderExpressionClass(evaluationContext(), "AClass", new AssignmentWithType("someAssignmentWithType", "SomeType", "someKey", "someValue")), is(new StringBuilder()
                .append("import java.lang.*;\n")
                .append("import java.util.*;\n")
                .append("import java.math.*;\n")
                .append("import static java.lang.Math.*;\n")
                .append("public final class AClass extends javarepl.EvaluationTemplate {\n")
                .append("  public AClass(javarepl.EvaluationContext context) { super(context); }\n")
                .append("\n")
                .append("\n")
                .append("  public SomeType evaluate() throws Exception {\n")
                .append("    SomeType $JAVAREPL_EXPRESSION_VALUE$ =\n")
                .append("\n")
                .append("    $JAVAREPL_EXPRESSION_TOKEN$;\n")
                .append("\n")
                .append("    return $JAVAREPL_EXPRESSION_VALUE$;\n")
                .append("  }\n")
                .append("}\n")
                .toString()));
    }

    @Test
    public void rendersTemplateForImport() {
        assertThat(renderExpressionClass(evaluationContext(), "AClass", new Import("someImport", "some.import")), is(new StringBuilder()
                .append("import java.lang.*;\n")
                .append("import java.util.*;\n")
                .append("import java.math.*;\n")
                .append("import static java.lang.Math.*;\n")
                .append("$JAVAREPL_EXPRESSION_TOKEN$;\n")
                .append("public final class AClass extends javarepl.EvaluationTemplate {\n")
                .append("  public AClass(javarepl.EvaluationContext context) { super(context); }\n")
                .append("\n")
                .append("  public void evaluate() throws Exception {\n")
                .append("  }\n")
                .append("}\n")
                .toString()));
    }

    @Test
    public void rendersTemplateForMethod() {
        assertThat(renderExpressionClass(evaluationContext(), "AClass", new Method("someMethod", Object.class, "someMethodName", Sequences.<Class<?>>empty())), is(new StringBuilder()
                .append("import java.lang.*;\n")
                .append("import java.util.*;\n")
                .append("import java.math.*;\n")
                .append("import static java.lang.Math.*;\n")
                .append("public final class AClass extends javarepl.EvaluationTemplate {\n")
                .append("  public AClass(javarepl.EvaluationContext context) { super(context); }\n")
                .append("\n")
                .append("\n")
                .append("  $JAVAREPL_EXPRESSION_TOKEN$\n")
                .append("\n")
                .append("  public void evaluate() throws Exception {\n")
                .append("  }\n")
                .append("}\n")
                .toString()));
    }

    @Test
    public void rendersPreviousResultsMethodsAndImports() {
        EvaluationContext context = evaluationContext()
                .addResult(result("result1", "value1"))
                .addResult(result("result2", "value2"))
                .addExpression(new Import("import java.net.URL", "java.net.URL"))
                .addExpression(new Import("import java.io.File;", "java.io.File"))
                .addExpression(new Method("int method1(int i){\nreturn i;\n}", int.class, "method1", Sequences.<Class<?>>sequence(int.class)))
                .addExpression(new Method("char method1(char i){\nreturn i;\n}", char.class, "method2", Sequences.<Class<?>>sequence(char.class)));

        assertThat(renderExpressionClass(context, "AClass", new Statement("someStatement")), is(new StringBuilder()
                .append("import java.lang.*;\n")
                .append("import java.util.*;\n")
                .append("import java.math.*;\n")
                .append("import static java.lang.Math.*;\n")
                .append("import java.net.URL;\n")
                .append("import java.io.File;;\n")
                .append("public final class AClass extends javarepl.EvaluationTemplate {\n")
                .append("  public AClass(javarepl.EvaluationContext context) { super(context); }\n")
                .append("  public java.lang.String result1 = valueOf(\"result1\");\n")
                .append("  public java.lang.String result2 = valueOf(\"result2\");\n")
                .append("  int method1(int i){\n")
                .append("  return i;\n")
                .append("  }\n")
                .append("\n")
                .append("  char method1(char i){\n")
                .append("  return i;\n")
                .append("  }\n")
                .append("  public void evaluate() throws Exception {\n")
                .append("    $JAVAREPL_EXPRESSION_TOKEN$;\n")
                .append("  }\n")
                .append("}\n")
                .toString()));
    }

    @Test
    public void rendersMethodSignatureDetection() {
        assertThat(renderMethodSignatureDetection(evaluationContext(), "AClass", "int someMethod(int param1, String param2){return param;}"), is(new StringBuilder()
                .append("import java.lang.*;\n")
                .append("import java.util.*;\n")
                .append("import java.math.*;\n")
                .append("import static java.lang.Math.*;\n")
                .append("public interface AClass {\n")
                .append("  int someMethod(int param1, String param2);\n")
                .append("}\n")
                .toString()));
    }

}
