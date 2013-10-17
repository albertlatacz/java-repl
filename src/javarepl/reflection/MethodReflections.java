package javarepl.reflection;

import com.googlecode.totallylazy.Mapper;

import java.lang.reflect.Method;

public final class MethodReflections {

    public static Mapper<Method, MethodReflection> toMethodReflection() {
        return new Mapper<Method, MethodReflection>() {
            public MethodReflection call(Method field) throws Exception {
                return new MethodReflection(field);
            }

            ;
        };
    }
}
