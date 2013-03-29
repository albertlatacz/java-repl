package javarepl;

import org.junit.Test;

import static javarepl.Evaluation.evaluation;
import static javarepl.EvaluationContext.emptyEvaluationContext;

import javarepl.expressions.Value;
import static javarepl.Result.someResult;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EvaluationContextTest {

    @Test
    public void shouldReturnResultByKey() {
        Evaluation evaluation = evaluation("", "", new Value(""), someResult("key1", "value1"));
        EvaluationContext context = emptyEvaluationContext().addEvaluation(evaluation);

        assertEquals(context.evaluationForResult("key1").get(), evaluation);
        assertTrue(context.evaluationForResult("invalidKey").isEmpty());
    }
}
