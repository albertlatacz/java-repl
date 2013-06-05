package javarepl.completion;


import com.googlecode.totallylazy.Callables;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;

import java.io.File;

import static com.googlecode.totallylazy.Callables.asString;
import static com.googlecode.totallylazy.Callables.last;
import static com.googlecode.totallylazy.Files.asFile;
import static com.googlecode.totallylazy.Predicates.not;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Sequences.memorise;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.*;
import static java.lang.ClassLoader.getSystemClassLoader;
import static javarepl.Utils.entries;
import static javarepl.completion.ResolvedPackage.functions.packageName;
import static javarepl.completion.ResolvedPackage.functions.resolvedPackage;

public class TypeResolver {
    public static Sequence<ResolvedPackage> packages() {
        return sequence(System.getProperty("java.class.path").split(File.pathSeparator))
                .map(asFile())
                .join(runtimeJars())
                .flatMap(packagesFromFile())
                .unique()
                .sortBy(packageName());
    }


    public static Sequence<String> classes(File file, String packageName) {
        return entries(file)
                .map(replace("/", "."))
                .filter(startsWith(packageName)
                        .and(endsWith(".class"))
                        .and(not(contains("$"))))
                .map(replaceFirst("\\.class$", ""))
                .filter(where(replace(packageName + ".", ""), not(contains("."))))
                .unique();
    }

    private static Mapper<File, Sequence<ResolvedPackage>> packagesFromFile() {
        return new Mapper<File, Sequence<ResolvedPackage>>() {
            public Sequence<ResolvedPackage> call(final File file) throws Exception {
                return entries(file).filter(endsWith(".class").and(not(contains("$"))))
                        .map(replaceFirst("[a-zA-Z0-9_]*.class$", "").then(replaceFirst("/$", "")).then(replace("/", ".")))
                        .unique()
                        .map(resolvedPackage().apply(file));
            }
        };
    }

    private static Sequence<File> runtimeJars() {
        try {
            return memorise(getSystemClassLoader().getResources("java/lang/Object.class"))
                    .map(asString().then(split("!/")).then(Callables.<String>first()).then(split(":")).then(last(String.class)))
                    .unique()
                    .map(asFile());
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load runtime jars", e);
        }
    }
}
