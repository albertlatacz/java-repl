package javarepl;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Pair;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import com.googlecode.totallylazy.regex.Regex;

import static com.googlecode.totallylazy.Callables.size;
import static com.googlecode.totallylazy.Predicates.is;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.regex.Regex.regex;
import static java.util.regex.Pattern.DOTALL;

public class ExpressionValidators {

    public static final Regex identifierPattern = regex("([a-zA-Z\\$_][a-zA-Z0-9\\$_]*)");
    public static final Regex assignmentPattern = regex(identifierPattern + " *=[^=](.*)$");
    public static final Regex typePattern = regex("([a-zA-Z\\$_][a-zA-Z0-9\\.\\$_\\[\\]<> ]*)");
    public static final Regex assignmentWithTypePattern = regex(typePattern + " +" + assignmentPattern);
    public static final Regex importPattern = regex("import .*");

    public static final Regex typeVisibilityModifiersPattern = regex("(?:private +|public +|protected +|)");
    public static final Regex typeExtensibilityModifiersPattern = regex("(?:final +|abstract +|)");
    public static final Regex typeStaticModifierPattern = regex("(?:static +|)");
    public static final Regex typePrefixPattern = permutations(sequence(typeExtensibilityModifiersPattern, typeStaticModifierPattern, typeVisibilityModifiersPattern));

    public static final Regex classPattern = regex(typePrefixPattern + "class +" + identifierPattern + ".*\\{.*", DOTALL);
    public static final Regex interfacePattern = regex(typePrefixPattern + "interface +" + identifierPattern + ".*\\{.*", DOTALL);

    public static boolean isValidImport(String string) {
        return importPattern.matches(string.trim());
    }

    public static boolean isValidClass(String string) {
        return classPattern.matches(string.trim());
    }

    public static boolean isValidInterface(String string) {
        return interfacePattern.matches(string.trim());
    }

    public static boolean isValidIdentifier(String string) {
        return identifierPattern.matches(string.trim());
    }

    public static boolean isValidAssignment(String string) {
        return assignmentPattern.matches(string.trim());
    }

    public static boolean isValidAssignmentWithType(String string) {
        return assignmentWithTypePattern.matches(string.trim());
    }

    private static Regex permutations(Sequence<Regex> patterns) {
        return regex(patterns.cartesianProduct(patterns.cartesianProduct(patterns))
                .map(flattenOptions())
                .filter(where(size(), is(patterns.size())))
                .map(Sequences.toString("(?:", "", ")"))
                .toString("(?:", "|", ")"));
    }

    @SuppressWarnings("unchecked")
    private static <T> Function1<Pair, Sequence<T>> flattenOptions() {
        return new Function1<Pair, Sequence<T>>() {
            public Sequence<T> call(Pair pair) throws Exception {
                Sequence<T> result = Sequences.empty();
                if (pair.first() instanceof Pair)
                    result = result.join(call((Pair) pair.first()));
                else
                    result = result.add((T) pair.first());

                if (pair.second() instanceof Pair)
                    result = result.join(call((Pair) pair.second()));
                else
                    result = result.add((T) pair.second());


                return result.unique();
            }
        };
    }

}
