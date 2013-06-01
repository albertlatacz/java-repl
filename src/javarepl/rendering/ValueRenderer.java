package javarepl.rendering;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.annotations.multimethod;
import com.googlecode.totallylazy.multi;

import java.util.Arrays;

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
        return Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(byte[] value) {
        return Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(char[] value) {
        return Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(double[] value) {
        return Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(float[] value) {
        return Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(int[] value) {
        return Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(short[] value) {
        return Arrays.toString(value);
    }

    @multimethod
    private static String renderValue(long[] value) {
        return Arrays.toString(value);
    }

    public static Mapper<Object, String> renderValue() {
        return new Mapper<Object, String>() {
            public String call(Object value) throws Exception {
                return renderValue(value);
            }
        };
    }
}
