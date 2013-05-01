package javarepl.expressions;

import com.googlecode.totallylazy.Function1;

import java.util.regex.MatchResult;

import static javarepl.expressions.Patterns.methodPattern;

public final class Method extends Expression {
    private final String signature;
    private final String type;
    private final String name;

    public Method(String source) {
        super(source);

        MatchResult methodMatch = methodPattern.match(source);
        signature = methodMatch.group(1);
        type = methodMatch.group(2);
        name = methodMatch.group(3);
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String signature() {
        return signature;
    }

    public String key() {
        return signature.replace(" ", "");
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
