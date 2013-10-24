package javarepl.completion;

import com.googlecode.totallylazy.Group;
import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.comparators.Comparators;
import com.googlecode.totallylazy.match;
import javarepl.reflection.ClassReflection;
import javarepl.reflection.MemberReflection;
import javarepl.reflection.MethodReflection;

import java.lang.reflect.*;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.numbers.Numbers.maximum;

public class Completions {

    public static Mapper<Character, Integer> lastIndexOf(final String string) {
        return new Mapper<Character, Integer>() {
            public Integer call(Character character) throws Exception {
                return string.lastIndexOf(character);
            }
        };
    }

    public static Mapper<MemberReflection, String> candidateName() {
        return new Mapper<MemberReflection, String>() {
            public String call(MemberReflection memberReflection) throws Exception {
                return new match<MemberReflection, String>() {
                    String value(MethodReflection expr) {
                        return expr.name() + "(";
                    }

                    String value(ClassReflection expr) {
                        return expr.member().getSimpleName();
                    }

                    String value(MemberReflection expr) {
                        return expr.name();
                    }
                }.apply(memberReflection).get();
            }
        };
    }


    public static Mapper<MemberReflection, String> candidateForm() {
        return new Mapper<MemberReflection, String>() {
            public String call(MemberReflection memberReflection) throws Exception {
                return new match<MemberReflection, String>() {
                    String value(MethodReflection expr) {
                        return genericMethodSignature(expr.member());
                    }

                    String value(ClassReflection expr) {
                        return expr.member().getSimpleName();
                    }

                    String value(MemberReflection expr) {
                        return expr.name();
                    }
                }.apply(memberReflection).get();
            }
        };
    }


    public static Mapper<Group<String, MemberReflection>, CompletionCandidate> candidate() {
        return new Mapper<Group<String, MemberReflection>, CompletionCandidate>() {
            public CompletionCandidate call(final Group<String, MemberReflection> group) throws Exception {
                return new CompletionCandidate(group.key(), group.map(candidateForm()).sort(Comparators.ascending(String.class)));
            }
        };
    }

    private static Mapper<Type, String> asSimpleGenericTypeSignature() {
        return new Mapper<Type, String>() {
            public String call(Type type) throws Exception {
                return renderSimpleGenericType(type);
            }
        };
    }


    private static String renderSimpleGenericType(Type type) {
        return new match<Type, String>() {
            String value(ParameterizedType t) {
                return renderSimpleGenericType(t.getRawType()) + sequence(t.getActualTypeArguments()).map(asSimpleGenericTypeSignature()).toString("<", ", ", ">");
            }

            String value(TypeVariable t) {
                return t.getName();
            }

            String value(GenericArrayType t) {
                return renderSimpleGenericType(t.getGenericComponentType()) + "[]";
            }

            String value(Class t) {
                return t.getSimpleName();
            }

            String value(WildcardType t) {
                return t.toString();
            }
        }.apply(type).getOrThrow(new IllegalArgumentException("Generic type not matched (" + type.getClass() + ")"));
    }

    private static String genericMethodSignature(Method method) {
        return renderSimpleGenericType(method.getGenericReturnType()) + " " +

                method.getName() +

                sequence(method.getGenericParameterTypes())
                        .map(asSimpleGenericTypeSignature())
                        .toString("(", ", ", ")") +

                sequence(method.getGenericExceptionTypes())
                        .map(asSimpleGenericTypeSignature())
                        .toString(method.getGenericExceptionTypes().length > 0 ? " throws " : "", ", ", "");
    }

    public static int lastIndexOfSeparator(Sequence<Character> characters, String expression) {
        return characters
                .map(lastIndexOf(expression))
                .reduce(maximum())
                .intValue();
    }

}
