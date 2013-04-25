package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;

import static com.googlecode.totallylazy.Sequences.sequence;

public class ValueFormatter {

    @multimethod
    public static String valueToString(Object value) {
        return new multi() {
        }.<String>methodOption(value)
                .getOrElse(String.valueOf(value));
    }

    @multimethod
    private static String valueToString(String value) {
        return "\"" + value + "\"";
    }

    @multimethod
    private static String valueToString(Object[] value) {
        return sequence(value).map(valueToString()).toString("[", ", ", "]");
    }

    @multimethod
    private static String valueToString(boolean[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String valueToString(byte[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String valueToString(char[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String valueToString(double[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String valueToString(float[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String valueToString(int[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String valueToString(short[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String valueToString(long[] value) {
        return java.util.Arrays.toString(value);
    }

    public static Function1<Object, String> valueToString() {
        return new Function1<Object, String>() {
            public String call(Object value) throws Exception {
                return valueToString(value);
            }
        };
    }
}
