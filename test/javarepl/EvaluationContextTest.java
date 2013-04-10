package javarepl;

import javarepl.expressions.Value;
import org.junit.Test;

import static javarepl.Evaluation.evaluation;
import static javarepl.EvaluationContext.evaluationContext;
import static javarepl.Result.someResult;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EvaluationContextTest {

    @Test
    public void shouldReturnResultByKey() {
        Evaluation evaluation = evaluation("", "", new Value(""), someResult("key1", "value1"));
        EvaluationContext context = evaluationContext().addEvaluation(evaluation);

        assertEquals(context.evaluationForResult("key1").get(), evaluation);
        assertTrue(context.evaluationForResult("invalidKey").isEmpty());
    }
}
