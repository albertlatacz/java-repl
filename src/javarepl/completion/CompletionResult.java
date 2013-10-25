package javarepl.completion;

import com.googlecode.funclate.Model;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;

import static com.googlecode.funclate.Model.persistent.model;
import static com.googlecode.funclate.Model.persistent.parse;
import static com.googlecode.totallylazy.Sequences.sequence;

public class CompletionResult {
    private final String expression;
    private final Integer position;
    private final Sequence<CompletionCandidate> candidates;

    public CompletionResult(String expression, Integer position, Sequence<CompletionCandidate> candidates) {
        this.expression = expression;
        this.position = position;
        this.candidates = candidates;
    }

    public Integer position() {
        return position;
    }

    public String expression() {
        return expression;
    }

    public Sequence<CompletionCandidate> candidates() {
        return candidates;
    }

    @Override
    public String toString() {
        return expression + " -> " + candidates.toString("[", ",", "]") + " @ " + position;
    }

    @Override
    public int hashCode() {
        return (expression != null ? expression.hashCode() : 0) +
                (candidates != null ? candidates.hashCode() : 0) +
                (position != null ? position.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CompletionResult &&
                other.getClass().equals(getClass()) &&
                (expression != null && expression.equals(((CompletionResult) other).expression)) &&
                (candidates != null && candidates.equals(((CompletionResult) other).candidates)) &&
                (position != null && position.equals(((CompletionResult) other).position));
    }

    public static final class functions {
        public static Mapper<CompletionResult, Integer> position() {
            return new Mapper<CompletionResult, Integer>() {
                public Integer call(CompletionResult result) throws Exception {
                    return result.position();
                }
            };
        }

        public static Mapper<CompletionResult, Sequence<CompletionCandidate>> candidates() {
            return new Mapper<CompletionResult, Sequence<CompletionCandidate>>() {
                public Sequence<CompletionCandidate> call(CompletionResult result) throws Exception {
                    return result.candidates();
                }
            };
        }
    }

    public static final class methods {
        public static String toJson(CompletionResult result) {
            return model()
                    .add("expression", result.expression())
                    .add("position", result.position().toString())
                    .add("candidates", result.candidates().map(new Mapper<CompletionCandidate, Model>() {
                        public Model call(CompletionCandidate completionCandidate) throws Exception {
                            return model()
                                    .add("value", completionCandidate.value())
                                    .add("forms", completionCandidate.forms().toList());
                        }
                    }).toList()).toString();
        }

        public static CompletionResult fromJson(String json) {
            Model model = parse(json);
            return new CompletionResult(model.get("expression", String.class),
                    Integer.valueOf(model.get("position", String.class)),
                    sequence(model.getValues("candidates", Model.class))
                            .map(new Mapper<Model, CompletionCandidate>() {
                                public CompletionCandidate call(Model model) throws Exception {
                                    return new CompletionCandidate(model.get("value", String.class), sequence(model.getValues("forms", String.class)));
                                }
                            }));
        }
    }
}
