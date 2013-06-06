package javarepl.completion;

import com.googlecode.totallylazy.Function;
import com.googlecode.totallylazy.Function2;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;

import java.io.File;

import static javarepl.completion.TypeResolver.functions.classResolver;

public class ResolvedPackage {
    private final String packageName;
    private final File file;
    private final Function<Sequence<ResolvedClass>> classResolver;

    public ResolvedPackage(File file, String packageName) {
        this.packageName = packageName;
        this.file = file;
        this.classResolver = classResolver(this);
    }

    public String packageName() {
        return packageName;
    }

    public File file() {
        return file;
    }

    public Sequence<ResolvedClass> classes() {
        return classResolver.apply();
    }

    @Override
    public String toString() {
        return packageName + " in " + file;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CompletionResult &&
                other.getClass().equals(getClass()) &&
                other.toString().equals(toString());
    }

    public static class functions {
        public static Mapper<ResolvedPackage, String> packageName() {
            return new Mapper<ResolvedPackage, String>() {
                public String call(ResolvedPackage resolvedPackage) throws Exception {
                    return resolvedPackage.packageName();
                }
            };
        }

        public static Mapper<ResolvedPackage, Sequence<ResolvedClass>> classes() {
            return new Mapper<ResolvedPackage, Sequence<ResolvedClass>>() {
                public Sequence<ResolvedClass> call(ResolvedPackage resolvedPackage) throws Exception {
                    return resolvedPackage.classes();
                }
            };
        }

        public static Function2<File, String, ResolvedPackage> resolvedPackage() {
            return new Function2<File, String, ResolvedPackage>() {
                @Override
                public ResolvedPackage call(File file, String packageName) throws Exception {
                    return new ResolvedPackage(file, packageName);
                }
            };
        }
    }

}
