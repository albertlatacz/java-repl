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
    final static Sequence<Character> openBrackets = sequence('[', '{', '(');
    final static Sequence<Character> closedBrackets = sequence(']', '}', ')');
    final static Map<Character, Character> matchingBrackets = Maps.map(closedBrackets.zip(openBrackets));

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

    private boolean expressionIsTerminated(Sequence<String> strings) {
        final Sequence<Character> characters = characters(strings.reduce(join));

        if (hasNullLine(strings))
            return true;

        if (hasTwoEmptyLines(strings))
            return true;

        LinkedList<Character> brackets = new LinkedList<Character>();
        for (Character character : characters) {
            if (isOpeningBracket(character)) {
                brackets.push(character);
            }

            if (isClosingBracket(character)) {
                if (brackets.isEmpty()) {
                    return false;
                }

                if (isMatchingBracket(brackets.peek(), character)) {
                    brackets.pop();
                } else {
                    return false;
                }
            }
        }

        return brackets.isEmpty();
    }

    private boolean isMatchingBracket(Character character1, Character character2) {
        return matchingBrackets.get(character2).equals(character1);
    }

    private boolean isClosingBracket(Character character) {
        return closedBrackets.contains(character);
    }

    private boolean isOpeningBracket(Character character) {
        return openBrackets.contains(character);
    }

    private boolean hasNullLine(Sequence<String> strings) {
        return strings.contains(null);
    }

    private boolean hasTwoEmptyLines(Sequence<String> lines) {
        return lines.windowed(2).contains(sequence("", ""));
    }

    public static Function1<Sequence<String>, String> lines(final String... strings) {
        return new Function1<Sequence<String>, String>() {
            Sequence<String> toRead = sequence(strings);
            public String call(Sequence<String> lines) throws Exception {
                Option<String> head = toRead.headOption();
                toRead = toRead.tail();
                return head.getOrNull();
            }
        };
    }
}
