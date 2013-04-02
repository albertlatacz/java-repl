package javarepl;

import com.googlecode.totallylazy.Function1;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import static com.googlecode.totallylazy.Randoms.takeFromValues;
import static com.googlecode.totallylazy.Sequences.characters;
import static com.googlecode.totallylazy.Sequences.sequence;
import static java.lang.reflect.Modifier.isPrivate;
import static java.net.URLDecoder.decode;

public class Utils {
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

    public static Function1<Object, String> valueToString() {
        return new Function1<Object, String>() {
            public String call(Object value) throws Exception {
                if (value == null)
                    return "null";

                if (value instanceof String)
                    return "\"" + value + "\"";

                if (value.getClass().isArray())
                    return sequence((Object[]) value).map(valueToString()).toString("[", ", ", "]");

                return value.toString();
            }
        };
    }

    public static Throwable unwrapException(Throwable e) {
        if (e instanceof InvocationTargetException)
            return unwrapException(((InvocationTargetException) e).getTargetException());

        return e;
    }

    public static String applicationVersion() throws Exception {
        File path = new File(decode(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "ISO-8859-1"));

        if (!path.isDirectory()) {
            try {
                JarInputStream jarStream = new JarInputStream(new FileInputStream(path));
                Manifest manifest = jarStream.getManifest();
                return manifest.getMainAttributes().getValue("Implementation-Version");
            } catch (Exception e) {
                // ignore
            }
        }

        return "[unknown]";
    }
}
