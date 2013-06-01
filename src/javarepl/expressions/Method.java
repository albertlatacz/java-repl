package javarepl.expressions;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;

import static javarepl.Utils.canonicalName;

public final class Method extends Expression {
    private final Class<?> type;
    private final String name;
    private final Sequence<Class<?>> arguments;

    public Method(String source, Class<?> type, String name, Sequence<Class<?>> arguments) {
        super(source);

        this.type = type;
        this.name = name;
        this.arguments = arguments;
    }

    public String name() {
        return name;
    }

    public Class<?> type() {
        return type;
    }

    public Sequence<Class<?>> arguments() {
        return arguments;
    }

    public String signature() {
        return type.getCanonicalName() + " " + name + arguments.map(canonicalName()).toString("(", ", ", ")");
    }

    public String key() {
        return name + arguments.map(canonicalName()).toString(", ");
    }

    public static final class functions {
        public static Mapper<Method, String> signature() {
            return new Mapper<Method, String>() {
                public String call(Method value) throws Exception {
                    return value.signature();
                }
            };
        }

        public static Mapper<Method, String> methodName() {
            return new Mapper<Method, String>() {
                public String call(Method value) throws Exception {
                    return value.name();
                }
            };
        }

    }
}
