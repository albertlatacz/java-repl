package javarepl.expressions;

import org.junit.Test;

import static javarepl.expressions.Patterns.*;
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
    }

    @Test
    public void shouldMatchValidAssignment() {
        assertTrue(isValidAssignment("val=42"));
        assertTrue(isValidAssignment("val =42"));
        assertTrue(isValidAssignment("val= 42"));
        assertTrue(isValidAssignment("  val  =   42  42  "));
        assertFalse(isValidAssignment("val wrong=   42  42  "));
    }

    @Test
    public void shouldMatchValidAssignmentWithType() {
        assertTrue(isValidAssignmentWithType("java.lang.String[]   aaa = 12"));
        assertTrue(isValidAssignmentWithType("Sequence< Object[] > aaa = 12"));
        assertFalse(isValidAssignmentWithType("Sequence< Object[] > aaa  12"));
    }

    @Test
    public void shouldMatchValidImport() {
        assertTrue(isValidImport("import xx.yy.zz"));
        assertTrue(isValidImport("  import     xx.yy.zz"));
        assertFalse(isValidImport("Import     xx.yy.zz"));
        assertFalse(isValidImport("importxx.yy.zz"));
    }

    @Test
    public void shouldMatchClass() {
        assertTrue(isValidType("public static final class SomeClass extends BaseClass implements Interface1,Interface2{  "));
        assertTrue(isValidType("static public final class SomeClass{"));
        assertTrue(isValidType("final static public class SomeClass{"));
        assertTrue(isValidType("final static class SomeClass{"));
        assertTrue(isValidType("final class SomeClass{"));
        assertTrue(isValidType("class SomeClass { "));
        assertTrue(isValidType("class SomeClass{ "));
        assertTrue(isValidType("class SomeClass{\n}"));
        assertFalse(isValidType("class SomeClass "));
        assertFalse(isValidType("class "));
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
    }
}
