package javarepl.rendering;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;

import static com.googlecode.totallylazy.Sequences.sequence;

public class ValueRenderer {

    @multimethod
    public static String renderValue(Object value) {
        return new multi() {
        }.<String>methodOption(value)
                .getOrElse(String.valueOf(value));
    }

    @multimethod
    private static String renderValue(String value) {
        return "\"" + value + "\"";
    }

    @multimethod
    private static String renderValue(Object[] value) {
        return sequence(value).map(renderValue()).toString("[", ", ", "]");
    }

    @multimethod
    private static String renderValue(boolean[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(byte[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(char[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(double[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(float[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(int[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(short[] value) {
        return java.util.Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(long[] value) {
        return java.util.Arrays.toString(value);
    }

    public static Function1<Object, String> renderValue() {
        return new Function1<Object, String>() {
            public String call(Object value) throws Exception {
                return renderValue(value);
            }
        };
    }
}
