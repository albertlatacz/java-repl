package javarepl;

import com.googlecode.totallylazy.*;

import java.util.LinkedList;
import java.util.Map;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static com.googlecode.totallylazy.Sequences.characters;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.join;

public class ExpressionReader {
    final static Sequence<Character> openBrackets = sequence('[', '{', '(');
    final static Sequence<Character> closedBrackets = sequence(']', '}', ')');
    final static Map<Character, Character> matchingBrackets = Maps.map(closedBrackets.zip(openBrackets));

    private final Mapper<Sequence<String>, String> lineReader;

    public ExpressionReader(Mapper<Sequence<String>, String> lineReader) {
        this.lineReader = lineReader;
    }

    public Option<String> readExpression() {
        Sequence<String> lines = Sequences.empty();

        do {
            lines = lines.add(lineReader.apply(lines));
        } while (!expressionIsTerminated(lines));

        return lines.contains(null)
                ? none(String.class)
                : some(lines.toString("\n").trim());
    }

    public static boolean expressionIsTerminated(Sequence<String> strings) {
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

    private static boolean isMatchingBracket(Character character1, Character character2) {
        return matchingBrackets.get(character2).equals(character1);
    }

    private static boolean isClosingBracket(Character character) {
        return closedBrackets.contains(character);
    }

    private static boolean isOpeningBracket(Character character) {
        return openBrackets.contains(character);
    }

    private static boolean hasNullLine(Sequence<String> strings) {
        return strings.contains(null);
    }

    private static boolean hasTwoEmptyLines(Sequence<String> lines) {
        return lines.windowed(2).contains(sequence("", ""));
    }

    public static Mapper<Sequence<String>, String> lines(final String... strings) {
        return new Mapper<Sequence<String>, String>() {
            Sequence<String> toRead = sequence(strings);

            public String call(Sequence<String> lines) throws Exception {
                Option<String> head = toRead.headOption();
                toRead = toRead.tail();
                return head.getOrNull();
            }
        };
    }
}
