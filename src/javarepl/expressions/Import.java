package javarepl.expressions;

import com.googlecode.totallylazy.Function1;

import static java.util.UUID.randomUUID;

public final class Import extends Expression {
    private final String typePackage;

    public Import(String source, String typePackage) {
        super(source);
        this.typePackage = typePackage;
    }

    public String key() {
        return randomUUID().toString();
    }

    public String typePackage() {
        return typePackage;
    }

    public static enum functions {
        ;

        public static Function1<Import, String> typePackage() {
            return new Function1<Import, String>() {
                public String call(Import value) throws Exception {
                    return value.typePackage();
                }
            };
        }

    }
}
