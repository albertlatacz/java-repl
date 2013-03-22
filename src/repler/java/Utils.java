package repler.java;

import static com.googlecode.totallylazy.Randoms.takeFromValues;
import static com.googlecode.totallylazy.Sequences.characters;
import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.reflect.Modifier.isPrivate;

public class Utils {
    public static String randomIdentifier(String prefix) {
        return prefix + "$" + takeFromValues(characters("abcdefghijklmnopqrstuvwxyz1234567890")).take(20).toString("");
    }

    public static String extractType(Class<?> clazz) {
        if (isPrivate(clazz.getModifiers()))
            return extractType(clazz.getSuperclass());

        return clazz.getCanonicalName();
    }

    public static String extractSimpleType(Class<?> clazz) {
        return sequence(extractType(clazz).split("\\.")).last();
    }
}
