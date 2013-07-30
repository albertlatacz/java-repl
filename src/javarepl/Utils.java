package javarepl;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import static com.googlecode.totallylazy.Callables.size;
import static com.googlecode.totallylazy.Files.*;
import static com.googlecode.totallylazy.Predicates.is;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Randoms.takeFromValues;
import static com.googlecode.totallylazy.Sequences.*;
import static com.googlecode.totallylazy.Strings.replace;
import static com.googlecode.totallylazy.URLs.url;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.isPrivate;
import static java.net.URLDecoder.decode;

public class Utils {
    public static String randomIdentifier(String prefix) {
        return prefix + "$" + takeFromValues(characters("abcdefghijklmnopqrstuvwxyz1234567890")).take(20).toString("");
    }

    public static Class<?> extractType(Object object) {
        if (object == null) {
            return Object.class;
        } else {
            return extractType(object.getClass());
        }
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

    public static Mapper<String, URL> resolveURL() {
        return new Mapper<String, URL>() {
            public URL call(String path) throws Exception {
                return resolveURL(path);
            }
        };
    }

    public static URL resolveURL(String path) {
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
                .map(new Mapper<Pair<Sequence<T>, T>, Sequence<T>>() {
                    public Sequence<T> call(Pair<Sequence<T>, T> pair) {
                        return pair.first().add(pair.second()).unique();
                    }
                })
                .unique();
    }

    public static Mapper<Class<?>, String> canonicalName() {
        return new Mapper<Class<?>, String>() {
            public String call(Class<?> aClass) throws Exception {
                return aClass.getCanonicalName();
            }
        };
    }

    public static String listValues(String name, Sequence<?> list) {
        return format(name + ":\n    %s\n", list.toString("\n").replaceAll("\n", "\n    "));
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

    public static File randomOutputDirectory() {
        File file = temporaryDirectory("JavaREPL/" + randomFilename());
        file.deleteOnExit();
        return file;
    }


    public static JarFile jarFile(URI path) {
        try {
            return new JarFile(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't create jar file for path " + path, e);
        }
    }

    public static Mapper<JarEntry, String> jarEntryName() {
        return new Mapper<JarEntry, String>() {
            public String call(JarEntry jarEntry) throws Exception {
                return jarEntry.getName();
            }
        };
    }


    public static Sequence<String> entries(File file) {
        if (file.isDirectory()) {
            return recursiveFiles(file)
                    .map(path().then(replace(file.getPath() + File.separator, "")));
        } else {
            try {
                return memorise(new JarFile(new File(file.toURI())).entries())
                        .map(jarEntryName());
            } catch (Exception e) {
                System.err.println("Couldn't load entries from jar " + file.toURI() + ". " + e.getLocalizedMessage());
                return empty();
            }
        }
    }
}
