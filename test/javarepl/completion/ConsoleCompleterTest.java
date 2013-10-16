package javarepl.completion;

import javarepl.console.Console;
import javarepl.console.SimpleConsole;
import org.junit.Test;

import static com.googlecode.totallylazy.Functions.returns;
import static com.googlecode.totallylazy.Sequences.empty;
import static javarepl.completion.CompleterTestHelper.*;
import static javarepl.console.ConsoleConfig.consoleConfig;
import static org.junit.Assert.assertThat;

public class ConsoleCompleterTest {

    private TypeResolver typeResolver = new TypeResolver(returns(empty(ResolvedPackage.class)));
    private Console console;

    @Test
    public void completesResults() {
        givenConsoleThatExecuted("42", "42", "roo=12");

        assertThat(completing("res1"), completesTo(candidates("res1"), position(0)));
        assertThat(completing("re"), completesTo(candidates("res0", "res1"), position(0)));
        assertThat(completing("some prefix r"), completesTo(candidates("res0", "res1", "roo"), position(12)));
    }

    @Test
    public void completesMethods() {
        givenConsoleThatExecuted("void method1(){}", "int method2(){return 42;}", "void myMethod(){}");

        assertThat(completing("method1"), completesTo(candidates("method1("), position(0)));
        assertThat(completing("me"), completesTo(candidates("method1(", "method2("), position(0)));
        assertThat(completing("some prefix m"), completesTo(candidates("method1(", "method2(", "myMethod("), position(12)));
    }

    @Test
    public void completesTypes() {
        givenConsoleThatExecuted("class Type1{}", "class Type2{}", "interface TheType{}");

        assertThat(completing("Type1"), completesTo(candidates("Type1"), position(0)));
        assertThat(completing("Ty"), completesTo(candidates("Type1", "Type2"), position(0)));
        assertThat(completing("some prefix T"), completesTo(candidates("Type1", "Type2", "TheType"), position(12)));
    }

    @Test
    public void combinesAllCompletions() {
        givenConsoleThatExecuted("class myType{}", "void myMethod(){}", "myRes=42");

        assertThat(completing("some prefix my"), completesTo(candidates("myRes", "myMethod(", "myType"), position(12)));
    }

    private CompletionResult completing(String expression) {
        return new ConsoleCompleter(console, typeResolver).apply(expression);
    }

    private void givenConsoleThatExecuted(String... expressions) {
        console = new SimpleConsole(consoleConfig());

        for (String expression : expressions) {
            console.execute(expression);
        }
    }
}
