package repler.java;

import com.googlecode.totallylazy.Function1;

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

    public static Result empty(String name) {
        return new Result(name, null);
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + " = " + value;
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


    public static Function1<Result, String> key() {
        return new Function1<Result, String>() {
            public String call(Result result) throws Exception {
                return result.key;
            }
        };
    }
}
