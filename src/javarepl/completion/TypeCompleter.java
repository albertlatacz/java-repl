package javarepl.completion;


import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Strings;

import static com.googlecode.totallylazy.Predicates.*;
import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Strings.startsWith;
import static com.googlecode.totallylazy.comparators.Comparators.ascending;
import static javarepl.completion.ResolvedClass.functions.canonicalClassName;
import static javarepl.completion.ResolvedPackage.functions.packageName;

public final class TypeCompleter extends Completer {
    private final TypeResolver typeResolver;

    public TypeCompleter(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    public CompletionResult call(String expression) throws Exception {
        final int lastSpace = expression.lastIndexOf(" ") + 1;
        final String packagePart = expression.substring(lastSpace);
        final int beginIndex = packagePart.lastIndexOf('.') + 1;

        Sequence<ResolvedPackage> resolvedPackages = typeResolver.packages().filter(where(packageName(), startsWith(packagePart)));

        Sequence<String> classesInPackage = beginIndex > 0 ?
                typeResolver.packages().filter(where(packageName(), equalTo(packagePart.substring(0, beginIndex - 1))))
                        .flatMap(ResolvedPackage.functions.classes())
                        .map(canonicalClassName())
                        .filter(startsWith(packagePart)) :
                empty(String.class);

        Sequence<String> candidates = resolvedPackages.map(packageName()).join(classesInPackage)
                .map(candidatePackagePrefix(beginIndex))
                .filter(not(Strings.empty()))
                .unique()
                .sort(ascending(String.class));

        return new CompletionResult(expression, lastSpace + beginIndex, candidates);
    }

    private Mapper<String, String> candidatePackagePrefix(final int fromIndex) {
        return new Mapper<String, String>() {
            public String call(String item) throws Exception {
                int toIndex = item.indexOf('.', fromIndex);

                if (toIndex < fromIndex)
                    toIndex = item.length();

                return item.substring(fromIndex, toIndex);
            }
        };
    }
}
