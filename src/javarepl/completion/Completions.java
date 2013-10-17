package javarepl.completion;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.match;
import javarepl.reflection.ClassReflection;
import javarepl.reflection.MemberReflection;
import javarepl.reflection.MethodReflection;

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

    public static int lastIndexOfSeparator(Sequence<Character> characters, String expression) {
        return characters
                .map(lastIndexOf(expression))
                .reduce(maximum())
                .intValue();
    }

}
