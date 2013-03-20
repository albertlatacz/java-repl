package repler.java;

import com.googlecode.totallylazy.Option;
import org.junit.Test;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static repler.java.EvaluationContext.emptyContext;
import static repler.java.Expression.expression;
import static repler.java.Result.result;

public class EvaluationContextTest {

    @Test
    public void shouldReturnResultByKey() {
        Result result = result("key1", "value1");
        EvaluationContext context = emptyContext()
                .addEvaluation(expression("", "", ""), result);

        assertThat(context.resultByKey("key1"), equalTo(some(result("key1", "value1"))));
        assertThat(context.resultByKey("invalidKey"), equalTo(Option.<Result>none()));
    }
}
