package javarepl;

import org.junit.Test;

import static javarepl.EvaluationTestHelper.*;
import static javarepl.Expression.*;
import static org.junit.Assert.assertThat;

public class EvaluatorTest{
    @Test
    public void shouldCreateExpressionOfCorrectType() {
        assertThat(evaluating("1 + 2"), hasExpressionOfType(Value.class));
        assertThat(evaluating("{}"), hasExpressionOfType(Statement.class));
        assertThat(evaluating("someVar = 12"), hasExpressionOfType(Assignment.class));
        assertThat(evaluating("int someVar = 12"), hasExpressionOfType(AssignmentWithType.class));
        assertThat(evaluating("import java.util.*"), hasExpressionOfType(Import.class));
        assertThat(evaluating("class SomeClass{}"), hasExpressionOfType(ClassOrInterface.class));
        assertThat(evaluating("interface SomeInterface{}"), hasExpressionOfType(ClassOrInterface.class));
    }


    @Test
    public void shouldEvaluateExpressions() {
        assertThat(evaluating("\"hello \" + \n \"there\""), hasResult("hello there"));
        assertThat(evaluating("1 + 2"), hasResult(3));
        assertThat(evaluating("new String(\"hello there\")"), hasResult("hello there"));
        assertThat(evaluating("class NewClass {public int field=20;}"), hasNoResult());
        assertThat(evaluating("class NewClass {public int field=20;}", "new NewClass().field"), hasResult(20));
    }

}
