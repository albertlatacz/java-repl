package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import static com.googlecode.totallylazy.Callables.size;
import static com.googlecode.totallylazy.Predicates.is;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Randoms.takeFromValues;
import static com.googlecode.totallylazy.Sequences.characters;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.URLs.url;
import static java.lang.String.format;
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

    public static String valueToString(Object value) {
        return valueToString().apply(value);
    }

    public static Function1<Object, String> valueToString() {
        return new Function1<Object, String>() {
            public String call(Object value) throws Exception {
                if (value == null)
                    return "null";

                if (value instanceof String)
                    return "\"" + value + "\"";

                if (value.getClass().isArray()) {
										// Java array types have names such as:
										// [Ljava.lang.Object;
										// [I
										// [J
										// etc, if we look at the second character, we can determine its type.
										// If the second character is '[' (nested array), or 'L' (class or interface),
										// then the existing logic is fine
										char arrayType = value.getClass().getName().charAt(1);
										if (arrayType == '[' || arrayType == 'L') {
											return sequence((Object[]) value).map(valueToString()).toString("[", ", ", "]");
										} else if (arrayType == 'Z') { return java.util.Arrays.toString((boolean[])value); }
										else if (arrayType == 'B') { return java.util.Arrays.toString((byte[])value); }
										else if (arrayType == 'C') { return java.util.Arrays.toString((char[])value); }
										else if (arrayType == 'D') { return java.util.Arrays.toString((double[])value); }
										else if (arrayType == 'F') { return java.util.Arrays.toString((float[])value); }
										else if (arrayType == 'I') { return java.util.Arrays.toString((int[])value); }
										else if (arrayType == 'J') { return java.util.Arrays.toString((long[])value); }
										else if (arrayType == 'S') { return java.util.Arrays.toString((short[])value); }
								}

                return value.toString();
            }
        };
    }

    public static Throwable unwrapException(Throwable e) {
        if (e instanceof InvocationTargetException)
            return unwrapException(((InvocationTargetException) e).getTargetException());

        return e;
    }

    public static URL resolveClasspath(String path) {
        try {
            return url(path);
        } catch (Exception e) {
            return url("file:" + path);
        }
    }

    public static boolean isWebUrl(URL classpathUrl) {
        return sequence("http", "https").contains(classpathUrl.getProtocol());
    }

    public static String applicationVersion() {

        try {
            File path = new File(decode(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "ISO-8859-1"));
            if (!path.isDirectory()) {
                JarInputStream jarStream = new JarInputStream(new FileInputStream(path));
                Manifest manifest = jarStream.getManifest();
                return manifest.getMainAttributes().getValue("Implementation-Version");

            }
        } catch (Exception e) {
            // ignore
        }

        return "[unknown]";
    }

    public static <T> Sequence<Sequence<T>> permutations(Sequence<T> items) {
        return powerSetPermutations(items).filter(where(size(), is(items.size())));
    }

    public static <T> Sequence<Sequence<T>> powerSetPermutations(Sequence<T> items) {
        return cartesianProductPower(items, items.size()).add(Sequences.<T>empty());
    }

    private static <T> Sequence<Sequence<T>> cartesianProductPower(Sequence<T> items, int times) {
        if (times == 0)
            return items.cartesianProduct().map(Pair.functions.values()).unsafeCast();

        return cartesianProductPower(items, times - 1)
                .cartesianProduct(items)
                .map(new Function1<Pair<Sequence<T>, T>, Sequence<T>>() {
                    public Sequence<T> call(Pair<Sequence<T>, T> pair) {
                        return pair.first().add(pair.second()).unique();
                    }
                })
                .unique();
    }

    public static String listValues(String name, Iterable<?> list) {
        return format(name + ":\n    %s\n", sequence(list).toString("\n").replaceAll("\n", "\n    "));
    }

    public static int randomServerPort() {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            Integer serverPort = serverSocket.getLocalPort();
            serverSocket.close();
            return serverPort;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
