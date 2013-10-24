package javarepl.completion;

import org.junit.Test;

import static javarepl.completion.CompleterTestHelper.*;
import static javarepl.completion.TypeResolver.functions.defaultPackageResolver;
import static org.junit.Assert.assertThat;

public class TypeCompleterTest {
    @Test
    public void completesTypes() {
        TypeCompleter completer = new TypeCompleter(new TypeResolver(defaultPackageResolver()));

        assertThat(completer.apply("java.lang.Obj"), completesTo(candidatesValues("Object"), position(10)));
        assertThat(completer.apply("java.lang.Object"), completesTo(candidatesValues("Object"), position(10)));

    }
}
