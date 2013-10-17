package javarepl.reflection;

import com.googlecode.totallylazy.Mapper;

import java.lang.reflect.Constructor;

public final class ConstructorReflections {

    public static Mapper<Constructor<?>, ConstructorReflection> toConstructorReflection() {
        return new Mapper<Constructor<?>, ConstructorReflection>() {
            public ConstructorReflection call(Constructor<?> constructor) throws Exception {
                return new ConstructorReflection(constructor);
            }

            ;
        };
    }

}
