package javarepl;

import org.junit.Test;

import static com.googlecode.totallylazy.Option.some;
import static javarepl.EvaluationContext.evaluationContext;
import static javarepl.Result.noResult;
import static javarepl.Result.result;
import static org.junit.Assert.assertEquals;

public class EvaluationContextTest {

    @Test
    public void shouldReturnResultByKey() {
        EvaluationContext context = evaluationContext().addResult(result("key1", "value1"));

        assertEquals(context.result("key1"), some(result("key1", "value1")));
        assertEquals(context.result("invalidKey"), noResult());
    }
}
