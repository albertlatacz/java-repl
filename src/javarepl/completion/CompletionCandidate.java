package javarepl.completion;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;

import static com.googlecode.totallylazy.Sequences.sequence;

public class CompletionCandidate {
    private final String value;
    private final Sequence<String> forms;

    public CompletionCandidate(String value, Sequence<String> forms) {
        this.value = value;
        this.forms = forms;
    }

    public String value() {
        return value;
    }

    public Sequence<String> forms() {
        return forms;
    }

    public static final class functions {
        public static Mapper<String, CompletionCandidate> asCompletionCandidate() {
            return new Mapper<String, CompletionCandidate>() {
                public CompletionCandidate call(String candidate) throws Exception {
                    return new CompletionCandidate(candidate, sequence(candidate));
                }
            };
        }

        public static Mapper<CompletionCandidate, String> candidateValue() {
            return new Mapper<CompletionCandidate, String>() {
                public String call(CompletionCandidate completionCandidate) throws Exception {
                    return completionCandidate.value;
                }
            };
        }

        public static Mapper<CompletionCandidate, Sequence<String>> candidateForms() {
            return new Mapper<CompletionCandidate, Sequence<String>>() {
                public Sequence<String> call(CompletionCandidate completionCandidate) throws Exception {
                    return completionCandidate.forms;
                }
            };
        }
    }

    @Override
    public String toString() {
        return value + forms.toString(" (", ", ", ")");
    }


    @Override
    public int hashCode() {
        return (value != null ? value.hashCode() : 0) +
                (forms != null ? forms.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CompletionCandidate &&
                other.getClass().equals(getClass()) &&
                (value != null && value.equals(((CompletionCandidate) other).value)) &&
                (forms != null && forms.equals(((CompletionCandidate) other).forms));
    }
}
