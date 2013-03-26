package javarepl;

import java.lang.reflect.InvocationTargetException;

import static com.googlecode.totallylazy.Randoms.takeFromValues;
import static com.googlecode.totallylazy.Sequences.characters;
import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.reflect.Modifier.isPrivate;

class Utils {
    public static String randomIdentifier(String prefix) {
        return prefix + "$" + takeFromValues(characters("abcdefghijklmnopqrstuvwxyz1234567890")).take(20).toString("");
    }

    public static Class<?> extractType(Class<?> clazz) {
        if (clazz.isAnonymousClass()) {
            if (clazz.getSuperclass().equals(Object.class)) {
                return extractType(clazz.getInterfaces()[0]);
            } else {
                return extractType(clazz.getSuperclass());
            }
        }

        if (isPrivate(clazz.getModifiers()))
            return extractType(clazz.getSuperclass());

        return clazz;
    }

    public static Throwable unwrapException(Throwable e) {
        if (e instanceof InvocationTargetException)
            return unwrapException(((InvocationTargetException) e).getTargetException());

        return e;
    }
}
