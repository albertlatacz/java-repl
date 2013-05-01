package javarepl.expressions;

import static java.util.UUID.randomUUID;

public final class Import extends Expression {
    public Import(String source) {
        super(source);
    }

    public String key() {
        return randomUUID().toString();
    }
}
