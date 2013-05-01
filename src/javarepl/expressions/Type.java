package javarepl.expressions;


import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Option;

import static com.googlecode.totallylazy.Option.option;
import static javarepl.expressions.Patterns.typePattern;

public final class Type extends Expression {
    private final Option<String> typePackage;
    private final String type;

    public Type(String source) {
        super(source);

        typePackage = option(typePattern.match(source).group(1));
        type = typePattern.match(source).group(2);
    }

    public String key() {
        return type;
    }

    public Option<String> typePackage() {
        return typePackage;
    }

    public String type() {
        return type;
    }

    public String canonicalName() {
        return (!typePackage.isEmpty() ? typePackage.get() + "." : "") + type;
    }

    public static enum functions {
        ;

        public static Function1<Type, String> type() {
            return new Function1<Type, String>() {
                public String call(Type value) throws Exception {
                    return value.type;
                }
            };
        }
    }
}