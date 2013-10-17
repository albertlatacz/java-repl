package javarepl.reflection;

import com.googlecode.totallylazy.Mapper;

public final class ClassReflections {

    public static ClassReflection reflectionOf(Class<?> aClass) {
        return new ClassReflection(aClass);
    }

    public static ClassReflection reflectionOf(Object object) {
        return new ClassReflection(object.getClass());
    }

    public static Mapper<Class<?>, ClassReflection> toClassReflection() {
        return new Mapper<Class<?>, ClassReflection>() {
            public ClassReflection call(Class<?> aClass) throws Exception {
                return new ClassReflection(aClass);
            }

            ;
        };
    }
}
