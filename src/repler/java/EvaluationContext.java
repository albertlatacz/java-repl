package repler.java;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import java.util.List;

import static com.googlecode.totallylazy.Predicates.equalTo;
import static com.googlecode.totallylazy.Predicates.is;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Sequences.sequence;

public final class EvaluationContext {
    private final Sequence<Result> results;

    private EvaluationContext(Sequence<Result> results) {
        this.results = sequence(results);
    }

    public static EvaluationContext empty() {
        return new EvaluationContext(Sequences.<Result>empty());
    }

    public List<Result> getResults() {
        return results.toList();
    }

    public Result resultByKey(final String key) {
        return results.find(where(Result.key(), equalTo(key))).get();
    }

    public EvaluationContext evaluationContext(Result result) {
        return new EvaluationContext(results.cons(result));
    }

    public static class Result {
        private final Task task;
        private final String key;
        private final Object value;

        Result(Task task, String key, Object value) {
            this.task = task;
            this.key = key;
            this.value = value;
        }

        public Task getTask() {
            return task;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public static Function1<Result, String> key() {
            return new Function1<Result, String>() {
                public String call(Result result) throws Exception {
                    return result.key;
                }
            };
        }
    }

    public static class Task {
        private final String expression;
        private final String className;
        private final String source;

        Task(String expression, String className, String source) {
            this.expression = expression;
            this.className = className;
            this.source = source;
        }

        public String getExpression() {
            return expression;
        }

        public String getClassName() {
            return className;
        }

        public String getSource() {
            return source;
        }
    }
}
