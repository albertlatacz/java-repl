package repler.java;

import com.googlecode.totallylazy.Option;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static repler.java.EvaluationContext.emptyContext;
import static repler.java.Expression.expression;
import static repler.java.Result.result;

public class EvaluationContextTest {

    @Test
    public void shouldReturnResultByKey() {
        EvaluationContext context = emptyContext().addEvaluation(expression("", "", ""), result("key1", "value1"));
        assertEquals(context.resultByKey("key1").get(), result("key1", "value1"));
        assertTrue(context.resultByKey("invalidKey").isEmpty());
    }
}
