package javarepl;

import javarepl.Evaluation;
import javarepl.EvaluationContext;
import javarepl.Expression;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static javarepl.EvaluationContext.emptyEvaluationContext;
import static javarepl.Evaluation.evaluation;
import static javarepl.Expression.expression;
import static javarepl.Result.someResult;

public class EvaluationContextTest {

    @Test
    public void shouldReturnResultByKey() {
        Evaluation evaluation = evaluation("", "", expression("", Expression.Type.VALUE), someResult("key1", "value1"));
        EvaluationContext context = emptyEvaluationContext().addEvaluation(evaluation);

        assertEquals(context.evaluationForResult("key1").get(), evaluation);
        assertTrue(context.evaluationForResult("invalidKey").isEmpty());
    }
}
