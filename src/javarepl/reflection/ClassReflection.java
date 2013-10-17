package javarepl.reflection;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;

import java.lang.annotation.Annotation;

import static com.googlecode.totallylazy.Option.option;
import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.reflection.ClassReflections.toClassReflection;
import static javarepl.reflection.ConstructorReflections.toConstructorReflection;
import static javarepl.reflection.FieldReflections.toFieldReflection;
import static javarepl.reflection.MethodReflections.toMethodReflection;

public final class ClassReflection extends MemberReflection<Class<?>> {
    public ClassReflection(Class<?> aClass) {
        super(aClass);
    }

    public Integer modifiers() {
        return member().getModifiers();
    }

    public String name() {
        return member().getName();
    }

    public Option<MethodReflection> enclosingMethod() {
        return option(member().getEnclosingMethod()).map(toMethodReflection());
    }

    public Sequence<FieldReflection> declaredFields() {
        return sequence(member().getDeclaredFields()).map(toFieldReflection());
    }

    public Sequence<FieldReflection> fields() {
        return sequence(member().getFields()).map(toFieldReflection());
    }

    public Sequence<MethodReflection> declaredMethods() {
        return sequence(member().getDeclaredMethods()).map(toMethodReflection());
    }

    public Sequence<MethodReflection> methods() {
        return sequence(member().getMethods()).map(toMethodReflection());
    }

    public Sequence<ConstructorReflection> declaredConstructors() {
        return sequence(member().getDeclaredConstructors()).map(toConstructorReflection());
    }

    public Sequence<ConstructorReflection> constructors() {
        return sequence(member().getConstructors()).map(toConstructorReflection());
    }

    public Sequence<Annotation> declaredAnnotations() {
        return sequence(member().getDeclaredAnnotations());
    }

    public Sequence<Annotation> annotations() {
        return sequence(member().getAnnotations());
    }

    private Sequence<ClassReflection> interfaces() {
        return sequence(member().getInterfaces()).map(toClassReflection());
    }

    private Sequence<ClassReflection> declaredClasses() {
        return sequence(member().getDeclaredClasses()).map(toClassReflection());
    }

    private Sequence<ClassReflection> classes() {
        return sequence(member().getClasses()).map(toClassReflection());
    }

    public Sequence<MemberReflection> declaredMembers() {
        return empty(MemberReflection.class)
                .join(declaredClasses())
                .join(declaredConstructors())
                .join(declaredFields())
                .join(declaredMethods())
                .unique();
    }

    public Sequence<MemberReflection> members() {
        return empty(MemberReflection.class)
                .join(classes())
                .join(constructors())
                .join(fields())
                .join(methods())
                .unique();
    }
}
