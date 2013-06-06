package javarepl.completion;

import com.googlecode.totallylazy.Mapper;

public final class ResolvedClass {
    private final ResolvedPackage resolvedPackage;
    private final String className;

    public ResolvedClass(ResolvedPackage resolvedPackage, String className) {
        this.resolvedPackage = resolvedPackage;
        this.className = className;
    }


    public ResolvedPackage resolvedPackage() {
        return resolvedPackage;
    }

    public String className() {
        return className;
    }

    public String canonicalClassName() {
        return (resolvedPackage.packageName().isEmpty() ? "" : resolvedPackage.packageName() + ".") + className;
    }

    @Override
    public String toString() {
        return resolvedPackage.packageName() + "." + className + " in " + resolvedPackage.file();
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
        public static Mapper<ResolvedClass, String> className() {
            return new Mapper<ResolvedClass, String>() {
                public String call(ResolvedClass resolvedPackage) throws Exception {
                    return resolvedPackage.className();
                }
            };
        }

        public static Mapper<ResolvedClass, String> canonicalClassName() {
            return new Mapper<ResolvedClass, String>() {
                public String call(ResolvedClass resolvedPackage) throws Exception {
                    return resolvedPackage.canonicalClassName();
                }
            };
        }
    }

}
