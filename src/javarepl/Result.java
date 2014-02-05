package javarepl;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;

import java.lang.reflect.Type;

import static com.googlecode.totallylazy.Option.none;
import static java.lang.String.format;
import static javarepl.Utils.extractType;
import static javarepl.rendering.TypeRenderer.renderType;
import static javarepl.rendering.ValueRenderer.renderValue;

public class Result {
    private final String key;
    private final Object value;
    private final Option<java.lang.reflect.Type> type;

    private Result(String key, Object value, Option<java.lang.reflect.Type> type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public static Result result(String key, Object value, Option<java.lang.reflect.Type> type) {
        return new Result(key, value, type);
    }

    public static Result result(String key, Object value) {
        return result(key, value, Option.<java.lang.reflect.Type>none());
    }

    public String key() {
        return key;
    }

    public Object value() {
        return value;
    }

    public Type type() {
        return extractType(type.getOrElse(value != null ? value.getClass() : Object.class));
    }

    public static Option<Result> noResult() {
        return none();
    }

    @Override
    public String toString() {
        return format("%s %s = %s", renderType(type()), key(), renderValue(value()));
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
                    return result.key();
                }
            };
        }

        public static Mapper<Result, Object> value() {
            return new Mapper<Result, Object>() {
                public Object call(Result result) throws Exception {
                    return result.value();
                }
            };
        }

        public static Mapper<Result, Type> type() {
            return new Mapper<Result, Type>() {
                public Type call(Result result) throws Exception {
                    return result.type();
                }
            };
        }
    }
}
