package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static javarepl.Utils.extractSimpleType;

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

    public static Option<Result> someResult(String key, Object value) {
        return some(new Result(key, value));
    }

    public static Option<Result> noResult() {
        return none();
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return (value != null ? Utils.extractSimpleType(value.getClass()) + " " : "") + key + " = " + value;
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


    public static Function1<Result, String> resultKey() {
        return new Function1<Result, String>() {
            public String call(Result result) throws Exception {
                return result.key;
            }
        };
    }

    public static Function1<Result, Object> resultValue() {
        return new Function1<Result, Object>() {
            public Object call(Result result) throws Exception {
                return result.value;
            }
        };
    }

}
