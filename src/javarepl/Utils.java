package javarepl;

import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import com.googlecode.totallylazy.functions.Function1;
import com.googlecode.totallylazy.reflection.Types;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import static com.googlecode.totallylazy.Characters.characters;
import static com.googlecode.totallylazy.Files.*;
import static com.googlecode.totallylazy.Randoms.takeFromValues;
import static com.googlecode.totallylazy.Sequences.memorise;
import static com.googlecode.totallylazy.Sequences.one;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.replace;
import static com.googlecode.totallylazy.io.URLs.url;
import static com.googlecode.totallylazy.predicates.Predicates.*;
import static com.googlecode.totallylazy.reflection.Types.classOf;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.isPublic;
import static java.net.URLDecoder.decode;

public class Utils {
    public static Boolean javaVersionAtLeast(String version) {
        return (System.getProperty("java.version").compareTo(version) >= 0);
    }

    public static String randomIdentifier(String prefix) {
        return prefix + "$" + takeFromValues(characters("abcdefghijklmnopqrstuvwxyz1234567890")).take(20).toString("");
    }

    public static Type extractType1(Type type) {
        if (type instanceof Class) {
            Class clazz = classOf(type);
            if (clazz.isAnonymousClass() || clazz.isSynthetic() || clazz.isMemberClass()) {
                if (clazz.getGenericSuperclass().equals(Object.class)) {
                    return extractType1(sequence(clazz.getGenericInterfaces())
                            .headOption()
                            .getOrElse(Object.class));
                } else {
                    return extractType1(clazz.getGenericSuperclass());
                }
            }

            if (!isPublic(clazz.getModifiers()))
                return extractType1(clazz.getGenericSuperclass());

            return clazz;
        }

        return type;
    }


    public static Sequence<Type> foo(Sequence<Type> typeSeq) {

        Sequence<Class> classes = typeSeq.flatMap(Types::classOption).filter(not(equalTo(Object.class))).unsafeCast();

        if (classes.isEmpty())
            return sequence();

        return classes.safeCast(Type.class).join(classes
                .flatMap(c ->
                        foo(one(c.getGenericSuperclass()))
                                .join(foo(sequence(c.getGenericInterfaces())))
                )).unique();


    }


    public static Type extractType(Type type) {

        Sequence<Class> foos = foo(one(type)).flatMap(Types::classOption).filter(x -> isPublic(x.getModifiers())).unsafeCast();



        System.out.println(foos.toString("\n"));


        return foos.headOption().getOrElse(Object.class);
    }

    public static Throwable unwrapException(Throwable e) {
        if (e instanceof InvocationTargetException)
            return unwrapException(((InvocationTargetException) e).getTargetException());

        return e;
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
        return powerSetPermutations(items).filter(where(Sequence::size, is(items.size())));
    }

    public static <T> Sequence<Sequence<T>> powerSetPermutations(Sequence<T> items) {
        return cartesianProductPower(items, items.size()).append(Sequences.<T>empty());
    }

    private static <T> Sequence<Sequence<T>> cartesianProductPower(Sequence<T> items, int times) {
        if (times == 0)
            return items.cartesianProduct().map(Pair.functions.values()).unsafeCast();

        return cartesianProductPower(items, times - 1)
                .cartesianProduct(items)
                .map(pair -> pair.first().append(pair.second()).unique())
                .unique();
    }

    public static Function1<Class<?>, String> canonicalName() {
        return Class::getCanonicalName;
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

    public static Function1<URL, String> urlAsFilePath() {
        return url -> new File(url.getFile()).getPath();
    }

    public static Sequence<String> entries(File file) {
        if (file.isDirectory()) {
            return recursiveFiles(file)
                    .map(path().then(replace(file.getPath() + File.separator, "")));
        } else {
            try {
                return memorise(new JarFile(new File(file.toURI())).entries())
                        .map(ZipEntry::getName);
            } catch (Exception e) {
                System.err.println("Couldn't load entries from jar " + file.toURI() + ". " + e.getLocalizedMessage());
                return Sequences.empty();
            }
        }
    }
}
