package javarepl.completion;


import com.googlecode.totallylazy.Callables;
import com.googlecode.totallylazy.Function;
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
import static javarepl.completion.TypeResolver.methods.*;

public final class TypeResolver {
    private final Function<Sequence<ResolvedPackage>> packageResolver;

    public TypeResolver(Function<Sequence<ResolvedPackage>> packageResolver) {
        this.packageResolver = packageResolver;
    }

    public Sequence<ResolvedPackage> packages() {
        return packageResolver.apply();
    }


    public static class functions {
        public static Function<Sequence<ResolvedPackage>> defaultPackageResolver() {
            return packagesResolver(classpathJars().join(runtimeJars()));
        }

        public static Function<Sequence<ResolvedPackage>> packagesResolver(final Sequence<File> files) {
            return new Function<Sequence<ResolvedPackage>>() {
                public Sequence<ResolvedPackage> call() throws Exception {
                    return packagesFromFiles(files);
                }
            }.lazy();
        }

        public static Function<Sequence<ResolvedClass>> classResolver(final ResolvedPackage resolvedPackage) {
            return new Function<Sequence<ResolvedClass>>() {
                public Sequence<ResolvedClass> call() throws Exception {
                    return classes(resolvedPackage);
                }
            };
        }


        public static Mapper<String, String> simpleClassName() {
            return new Mapper<String, String>() {
                public String call(String className) throws Exception {
                    return className.substring(className.lastIndexOf('.') + 1);
                }
            };
        }
    }


    public static class methods {
        public static Sequence<ResolvedPackage> packagesFromFiles(Sequence<File> files) {
            return files
                    .flatMap(packagesFromFile())
                    .unique()
                    .sortBy(packageName());
        }

        public static Sequence<ResolvedClass> classes(final ResolvedPackage resolvedPackage) {
            return entries(resolvedPackage.file())
                    .map(replace("/", "."))
                    .filter(startsWith(resolvedPackage.packageName())
                            .and(endsWith(".class"))
                            .and(not(contains("$"))))
                    .map(replaceFirst("\\.class$", ""))
                    .filter(where(replace(resolvedPackage.packageName() + ".", ""), not(contains("."))))
                    .unique()
                    .map(new Mapper<String, ResolvedClass>() {
                        public ResolvedClass call(String s) throws Exception {
                            return new ResolvedClass(resolvedPackage, s.substring(s.lastIndexOf('.') + 1));
                        }
                    });
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

        public static Sequence<File> classpathJars() {
            return sequence(System.getProperty("java.class.path").split(File.pathSeparator))
                    .map(asFile());
        }

        public static Sequence<File> runtimeJars() {
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
}
