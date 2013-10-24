package javarepl.completion;

import javarepl.Evaluator;
import org.junit.Test;

import static com.googlecode.totallylazy.Functions.returns;
import static com.googlecode.totallylazy.Sequences.empty;
import static javarepl.completion.CompleterTestHelper.*;
import static org.junit.Assert.assertThat;

public class ConsoleCompleterTest {

    private TypeResolver typeResolver = new TypeResolver(returns(empty(ResolvedPackage.class)));
    private Evaluator evaluator;

    @Test
    public void completesResults() {
        givenConsoleThatExecuted("42", "42", "roo=12");

        assertThat(completing("res1"), completesTo(candidatesValues("res1"), position(0)));
        assertThat(completing("re"), completesTo(candidatesValues("res0", "res1"), position(0)));
        assertThat(completing("some prefix r"), completesTo(candidatesValues("res0", "res1", "roo"), position(12)));
    }

    @Test
    public void completesMethods() {
        givenConsoleThatExecuted("void method1(){}", "int method2(){return 42;}", "void myMethod(){}");

        assertThat(completing("method1"), completesTo(candidatesValues("method1("), position(0)));
        assertThat(completing("me"), completesTo(candidatesValues("method1(", "method2("), position(0)));
        assertThat(completing("some prefix m"), completesTo(candidatesValues("method1(", "method2(", "myMethod("), position(12)));
    }

    @Test
    public void completesTypes() {
        givenConsoleThatExecuted("class Type1{}", "class Type2{}", "interface TheType{}");

        assertThat(completing("Type1"), completesTo(candidatesValues("Type1"), position(0)));
        assertThat(completing("Ty"), completesTo(candidatesValues("Type1", "Type2"), position(0)));
        assertThat(completing("some prefix T"), completesTo(candidatesValues("Type1", "Type2", "TheType"), position(12)));
    }

    @Test
    public void combinesAllCompletions() {
        givenConsoleThatExecuted("class myType{}", "void myMethod(){}", "myRes=42");

        assertThat(completing("some prefix my"), completesTo(candidatesValues("myRes", "myMethod(", "myType"), position(12)));
    }

    private CompletionResult completing(String expression) {
        return new ConsoleCompleter(evaluator, typeResolver).apply(expression);
    }

    private void givenConsoleThatExecuted(String... expressions) {
        evaluator = new Evaluator();

        for (String expression : expressions) {
            evaluator.evaluate(expression);
        }
    }
}
