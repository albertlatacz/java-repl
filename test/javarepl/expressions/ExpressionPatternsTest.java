package javarepl.expressions;

import org.junit.Test;

import static javarepl.expressions.ExpressionPatterns.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExpressionPatternsTest {

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
//        assertTrue(isValidClass("public static final class SomeClass extends BaseClass implements Interface1,Interface2{  "));
        assertTrue(isValidClass("static public final class SomeClass{"));
        assertTrue(isValidClass("final static public class SomeClass{"));
        assertTrue(isValidClass("final static class SomeClass{"));
        assertTrue(isValidClass("final class SomeClass{"));
        assertTrue(isValidClass("class SomeClass { "));
        assertTrue(isValidClass("class SomeClass{ "));
        assertTrue(isValidClass("class SomeClass{\n}"));
        assertFalse(isValidClass("class SomeClass "));
        assertFalse(isValidClass("class "));
    }

    @Test
    public void shouldMatchInterface() {
        assertTrue(isValidInterface("static public final interface SomeInterface{"));
        assertTrue(isValidInterface("final static public interface SomeInterface{"));
        assertTrue(isValidInterface("final static interface SomeInterface{"));
        assertTrue(isValidInterface("final interface SomeInterface{"));
        assertTrue(isValidInterface("interface SomeInterface { "));
        assertTrue(isValidInterface("interface SomeInterface{ "));
        assertTrue(isValidInterface("interface SomeInterface{\n}"));
        assertFalse(isValidInterface("interface SomeInterface "));
        assertFalse(isValidInterface("interface "));
    }
}
