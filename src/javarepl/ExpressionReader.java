package javarepl;

import com.googlecode.totallylazy.*;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.googlecode.totallylazy.Pair.pair;
import static com.googlecode.totallylazy.Predicates.not;
import static com.googlecode.totallylazy.Predicates.nullValue;
import static com.googlecode.totallylazy.Sequences.characters;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.join;
import static com.googlecode.totallylazy.Strings.lines;
import static javarepl.Expression.Type.STATEMENT;
import static javarepl.Expression.expression;

public class ExpressionReader {
    private final Function<String> lineReader;
    private final Function1<Sequence<String>, Void> setPrompt;

    public ExpressionReader(Function<String> lineReader, Function1<Sequence<String>, Void> setPrompt) {
        this.lineReader = lineReader;
        this.setPrompt = setPrompt;
    }

    public Expression readExpression() {
        Sequence<String> lines = Sequences.empty();

        do {
            setPrompt.apply(lines);
            lines = lines.add(lineReader.apply());
        } while (!expressionIsTerminated(lines));

        return expression(lines.filter(not(nullValue())).toString("\n"), STATEMENT);
    }

    private boolean expressionIsTerminated(Sequence<String> lines) {
        final Sequence<Character> openBrackets = sequence('[', '{', '(');
        final Sequence<Character> closedBrackets = sequence(']', '}', ')');
        final Map<Character, Character> matchingBrackets = Maps.map(closedBrackets.zip(openBrackets));
        final String line = lines.reduce(join);
        final Sequence<Character> characters = characters(line);

        if (lines.zip(lines.drop(1)).contains(pair("", "")))
            return true;

        if (lines.contains(null))
            return true;

        ArrayDeque<Character> brackets = new ArrayDeque<Character>();
        for (Character character : characters) {
            if (openBrackets.contains(character)) {
                brackets.push(character);
            }

            if (closedBrackets.contains(character)) {
                if (brackets.isEmpty()) {
                    return false;
                }

                if (matchingBrackets.get(character).equals(brackets.peek())) {
                    brackets.pop();
                } else {
                    return false;
                }
            }
        }

        return brackets.isEmpty();
    }

    public static  Function<String> lines(final String... strings) {
        return new Function<String>() {
            Sequence<String> lines = sequence(strings);
            public String call() throws Exception {
                Option<String> head = lines.headOption();
                lines = lines.tail();
                return head.getOrNull();
            }
        };
    }

    public static Function1<Sequence<String>, Void> emptyPrompt() {
        return new Function1<Sequence<String>, Void>() {
            @Override
            public Void call(Sequence<String> strings) throws Exception {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }
}
