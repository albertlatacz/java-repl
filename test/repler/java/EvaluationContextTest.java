package repler.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static repler.java.EvaluationContext.emptyEvaluationContext;
import static repler.java.Evaluation.evaluation;
import static repler.java.Expression.expression;
import static repler.java.Result.someResult;

public class EvaluationContextTest {

    @Test
    public void shouldReturnResultByKey() {
        Evaluation evaluation = evaluation("", "", expression("", Expression.Type.VALUE), someResult("key1", "value1"));
        EvaluationContext context = emptyEvaluationContext().addEvaluation(evaluation);

        assertEquals(context.evaluationForResult("key1").get(), evaluation);
        assertTrue(context.evaluationForResult("invalidKey").isEmpty());
    }
}
