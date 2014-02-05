package javarepl.completion;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import javarepl.Evaluator;
import javarepl.reflection.ClassReflection;
import javarepl.reflection.MemberReflection;

import java.lang.reflect.Type;

import static com.googlecode.totallylazy.Characters.characters;
import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Predicates.not;
import static com.googlecode.totallylazy.Predicates.where;
import static com.googlecode.totallylazy.Strings.startsWith;
import static javarepl.completion.CompletionCandidate.functions.candidateValue;
import static javarepl.completion.Completions.*;
import static javarepl.reflection.ClassReflections.reflectionOf;
import static javarepl.reflection.MemberReflections.isPublic;
import static javarepl.reflection.MemberReflections.isStatic;

public class InstanceMemberCompleter extends Completer {

    private final Evaluator evaluator;

    public InstanceMemberCompleter(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public CompletionResult call(String expression) throws Exception {
        final int lastSeparator = lastIndexOfSeparator(characters(" "), expression) + 1;
        final String packagePart = expression.substring(lastSeparator);
        final Boolean canComplete = packagePart.matches("[a-zA-Z0-9\\$_\\\\.\\(\\[\\]]*") && packagePart.contains(".");

        final int beginIndex = packagePart.lastIndexOf('.') + 1;
        Option<Type> aClass = canComplete
                ? evaluator.typeOfExpression(packagePart.substring(0, beginIndex - 1))
                : none(Type.class);

        if (aClass.isDefined()) {
            ClassReflection classReflection = reflectionOf(aClass.get());

            Sequence<MemberReflection> join = Sequences.empty(MemberReflection.class)
                    .join(classReflection.declaredFields())
                    .join(classReflection.declaredMethods());

            Sequence<CompletionCandidate> candidates = join
                    .filter(isPublic().and(not(isStatic())))
                    .groupBy(candidateName())
                    .map(candidate())
                    .filter(where(candidateValue(), startsWith(packagePart.substring(beginIndex))));

            return new CompletionResult(expression, lastSeparator + beginIndex, candidates);
        } else {
            return new CompletionResult(expression, 0, Sequences.empty(CompletionCandidate.class));
        }
    }
}
