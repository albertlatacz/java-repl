package javarepl.expressions;


import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Option;

public final class Type extends Expression {
    private final Option<String> typePackage;
    private final String type;

    public Type(String source, Option<String> typePackage, String type) {
        super(source);

        this.typePackage = typePackage;
        this.type = type;
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

    public static final class functions {
        public static Mapper<Type, String> type() {
            return new Mapper<Type, String>() {
                public String call(Type value) throws Exception {
                    return value.type;
                }
            };
        }
    }
}