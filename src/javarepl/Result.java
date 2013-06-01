package javarepl;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;

import static com.googlecode.totallylazy.Option.none;
import static javarepl.Utils.extractType;
import static javarepl.rendering.ValueRenderer.renderValue;

public class Result {
    private final String key;
    private final Object value;

    private Result(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public static Result result(String key, Object value) {
        return new Result(key, value);
    }

    public String key() {
        return key;
    }

    public Object value() {
        return value;
    }

    public static Option<Result> noResult() {
        return none();
    }

    public String toString(boolean canonical) {
        Class<?> type = extractType(value.getClass());
        return (canonical ? type.getCanonicalName() : type.getSimpleName()) + " " + key + " = " + renderValue(value);
    }

    @Override
    public String toString() {
        return toString(false);
    }

    @Override
    public int hashCode() {
        return (key != null ? key.hashCode() : 0) +
                (value != null ? value.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Result &&
                (key != null && key.equals(((Result) other).key)) &&
                (value != null && value.equals(((Result) other).value));
    }

    public static final class functions {
        public static Mapper<Result, String> key() {
            return new Mapper<Result, String>() {
                public String call(Result result) throws Exception {
                    return result.key;
                }
            };
        }

        public static Mapper<Result, Object> value() {
            return new Mapper<Result, Object>() {
                public Object call(Result result) throws Exception {
                    return result.value;
                }
            };
        }
    }
}
