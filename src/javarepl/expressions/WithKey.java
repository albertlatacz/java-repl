package javarepl.expressions;

import com.googlecode.totallylazy.Function1;

public interface WithKey {
    String key();

    public static enum functions {;
        public static Function1<? super WithKey, String> key() {
            return new Function1<WithKey, String>() {
                public String call(WithKey value) throws Exception {
                    return value.key();
                }
            };
        }

    }
}
