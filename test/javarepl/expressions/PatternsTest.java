package javarepl.expressions;

import org.junit.Test;

import java.util.regex.MatchResult;

import static javarepl.expressions.Patterns.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PatternsTest {

    @Test
    public void shouldMatchValidIdentifier() {
        assertTrue(isValidIdentifier("Aa_$09"));
        assertTrue(isValidIdentifier("$Aa_09"));
        assertTrue(isValidIdentifier("_$Aa09"));
        assertTrue(isValidIdentifier("  aass"));
        assertFalse(isValidIdentifier("£Aaa"));
        assertFalse(isValidIdentifier("aa ss"));

        MatchResult result = identifierPattern.match("Aa_$09");
        assertThat(result.group(1), is("Aa_$09"));
    }

    @Test
    public void shouldMatchValidAssignment() {
        assertTrue(isValidAssignment("val=42"));
        assertTrue(isValidAssignment("val =42"));
        assertTrue(isValidAssignment("val= 42"));
        assertTrue(isValidAssignment("  val  =   42  42  "));
        assertFalse(isValidAssignment("val wrong=   42  42  "));

        MatchResult result = assignmentPattern.match("val = 42");
        assertThat(result.group(1), is("val"));
        assertThat(result.group(2), is(" 42"));
    }

    @Test
    public void shouldMatchValidAssignmentWithType() {
        assertTrue(isValidAssignmentWithType("java.lang.String[]   aaa = 12"));
        assertTrue(isValidAssignmentWithType("Sequence< Object[] > aaa = 12"));
        assertTrue(isValidAssignmentWithType("Pair< Integer, Integer> aaa  = pair(12, 22)"));
        assertTrue(isValidAssignmentWithType("Pair<? extends Object & Integer, Long> aaa  = pair(12, 22)"));
        assertFalse(isValidAssignmentWithType("Sequence< Object[] > aaa  12"));

        MatchResult result = assignmentWithTypeNamePattern.match("Integer val = 42");
        assertThat(result.group(1), is("Integer"));
        assertThat(result.group(2), is("val"));
        assertThat(result.group(3), is(" 42"));
    }

    @Test
    public void shouldMatchValidImport() {
        assertTrue(isValidImport("import xx.yy.zz"));
        assertTrue(isValidImport("  import     xx.yy.zz"));
        assertFalse(isValidImport("Import     xx.yy.zz"));
        assertFalse(isValidImport("importxx.yy.zz"));

        MatchResult result = importPattern.match("\"  import     xx.yy.zz    ;");
        assertThat(result.group(1), is("xx.yy.zz"));
    }

    @Test
    public void shouldMatchClass() {
        assertTrue(isValidType("package some.pack; public static final class SomeClass extends BaseClass implements Interface1,Interface2{  "));
        assertTrue(isValidType("public static final class SomeClass extends BaseClass implements Interface1,Interface2{  "));
        assertTrue(isValidType("static public final class SomeClass{}"));
        assertTrue(isValidType("final static public class SomeClass{"));
        assertTrue(isValidType("final static class SomeClass{"));
        assertTrue(isValidType("final class SomeClass{"));
        assertTrue(isValidType("  package some.pack; \n\n   class SomeClass { "));
        assertTrue(isValidType("class SomeClass { "));
        assertTrue(isValidType("class SomeClass{ "));
        assertTrue(isValidType("class SomeClass{\n}"));
        assertFalse(isValidType("class SomeClass "));
        assertFalse(isValidType("class "));

        MatchResult result = typePattern.match("package some.pack; public static final class SomeClass extends BaseClass{");
        assertThat(result.group(1), is("some.pack"));
        assertThat(result.group(2), is("SomeClass"));
    }

    @Test
    public void shouldMatchInterface() {
        assertTrue(isValidType("static public final interface SomeInterface{"));
        assertTrue(isValidType("final static public interface SomeInterface{"));
        assertTrue(isValidType("final static interface SomeInterface{"));
        assertTrue(isValidType("final interface SomeInterface{"));
        assertTrue(isValidType("interface SomeInterface { "));
        assertTrue(isValidType("interface SomeInterface{ "));
        assertTrue(isValidType("interface SomeInterface{\n}"));
        assertFalse(isValidType("interface SomeInterface "));
        assertFalse(isValidType("interface "));

        MatchResult result = typePattern.match("static public final interface SomeInterface {");
        assertThat(result.group(2), is("SomeInterface"));
    }

    @Test
    public void shouldMatchMethod() {
        assertTrue(isValidMethod("public final java.lang.String SomeMethod () { "));
        assertTrue(isValidMethod("static public final void SomeMethod () {"));
        assertTrue(isValidMethod("final static public void SomeMethod(){"));
        assertTrue(isValidMethod("final static void SomeMethod(){"));
        assertTrue(isValidMethod("final void SomeMethod(){"));
        assertTrue(isValidMethod("void SomeMethod(){"));
        assertFalse(isValidMethod("SomeMethod(){"));

        MatchResult result = methodPattern.match("public final java.lang.String SomeMethod () { ");
        assertThat(result.group(2), is("java.lang.String"));
        assertThat(result.group(3), is("SomeMethod"));
    }

}
