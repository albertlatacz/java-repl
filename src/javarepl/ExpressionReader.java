package javarepl;

import com.googlecode.totallylazy.*;

import java.util.LinkedList;
import java.util.Map;

import static com.googlecode.totallylazy.Pair.pair;
import static com.googlecode.totallylazy.Predicates.not;
import static com.googlecode.totallylazy.Predicates.nullValue;
import static com.googlecode.totallylazy.Sequences.characters;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.join;
import static javarepl.Expression.Type.STATEMENT;
import static javarepl.Expression.expression;

public class ExpressionReader {
    private final Function1<Sequence<String>, String> lineReader;

    public ExpressionReader(Function1<Sequence<String>, String> lineReader) {
        this.lineReader = lineReader;
    }

    public Expression readExpression() {
        Sequence<String> lines = Sequences.empty();

        do {
            lines = lines.add(lineReader.apply(lines));
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

        LinkedList<Character> brackets = new LinkedList<Character>();
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

    public static  Function1<Sequence<String>, String> lines(final String... strings) {
        return new Function1<Sequence<String>, String>() {
            Sequence<String> toRead = sequence(strings);
            public String call(Sequence<String> lines) throws Exception {
                Option<String> head = toRead.headOption();
                toRead = toRead.tail();
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
