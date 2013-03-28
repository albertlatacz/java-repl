package javarepl;

import org.junit.Test;

import static javarepl.ExpressionValidators.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExpressionValidatorsTest {

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
}
