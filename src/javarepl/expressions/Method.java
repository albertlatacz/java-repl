package javarepl.expressions;

import com.googlecode.totallylazy.Function1;
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

    public static enum functions {
        ;

        public static Function1<Method, String> signature() {
            return new Function1<Method, String>() {
                public String call(Method value) throws Exception {
                    return value.signature();
                }
            };
        }

    }
}
