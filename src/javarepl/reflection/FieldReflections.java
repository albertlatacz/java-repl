package javarepl.reflection;

import com.googlecode.totallylazy.Mapper;

import java.lang.reflect.Field;

public final class FieldReflections {

    public static Mapper<Field, FieldReflection> toFieldReflection() {
        return new Mapper<Field, FieldReflection>() {
            public FieldReflection call(Field field) throws Exception {
                return new FieldReflection(field);
            }

            ;
        };
    }

}
