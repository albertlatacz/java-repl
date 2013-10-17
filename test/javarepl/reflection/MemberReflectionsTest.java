package javarepl.reflection;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.predicates.LogicalPredicate;
import org.junit.Test;

import static javarepl.reflection.ClassReflections.reflectionOf;
import static javarepl.reflection.MemberReflections.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class MemberReflectionsTest {
    @Test
    public void shouldMatchModifier() {
        assertThat(membersMatching(isPublic()), hasItem("publicMember"));
        assertThat(membersMatching(isProtected()), hasItem("protectedMember"));
        assertThat(membersMatching(isPrivate()), hasItem("privateMember"));
        assertThat(membersMatching(isFinal()), hasItem("finalMember"));
        assertThat(membersMatching(isVolatile()), hasItem("volatileMember"));
        assertThat(membersMatching(isStatic()), hasItem("staticMember"));
        assertThat(membersMatching(isTransient()), hasItem("transientMember"));
        assertThat(membersMatching(isSynchronized()), hasItem("synchronizedMember"));
        assertThat(membersMatching(isStrict()), hasItem("strictMember"));
        assertThat(membersMatching(isAbstract()), hasItem("abstractMember"));
        assertThat(membersMatching(isSynthetic()), hasItem(ModifiersTestClass.class.getName()));
        assertThat(membersMatching(isInterface()), hasItem(ModifiersTestClass.InterfaceModifiers.class.getName()));
    }

    @Test
    public void shouldMatchReflectionType() {
        assertThat(membersMatching(isType()), hasItem(ModifiersTestClass.InterfaceModifiers.class.getName()));
        assertThat(membersMatching(isMethod()), hasItem("methodMember"));
        assertThat(membersMatching(isField()), hasItem("fieldMember"));
        assertThat(membersMatching(isConstructor()), hasItem(ModifiersTestClass.class.getName()));
    }

    private Sequence<String> membersMatching(LogicalPredicate<? super MemberReflection> predicate) {
        return reflectionOf(new ModifiersTestClass()).declaredMembers()
                .join(reflectionOf(ModifiersTestClass.InterfaceModifiers.class).declaredMembers())
                .filter(predicate)
                .map(memberName());
    }

    @SuppressWarnings("unused")
    private static final class ModifiersTestClass {
        public Object publicMember;
        protected Object protectedMember;
        private Object privateMember;
        volatile Object volatileMember;
        transient Object transientMember;

        static void staticMember() {
        }

        final void finalMember() {
        }

        synchronized void synchronizedMember() {
        }

        strictfp void strictMember() {
        }

        void methodMember() {
        }

        Object fieldMember;

        public interface InterfaceModifiers {
            void abstractMember();
        }
    }

}
