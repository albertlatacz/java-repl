package javarepl.console.rest;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import static com.googlecode.totallylazy.Option.none;
import static com.googlecode.totallylazy.Option.some;
import static javarepl.ExpressionReader.expressionIsTerminated;

public class RestConsoleExpressionReader {
    private Sequence<String> lines = Sequences.empty();

    public Option<String> readExpression(String line) {
        lines = lines.add(line);
        if (expressionIsTerminated(lines)) {
            Option<String> result = some(lines.toString("\n"));
            lines = Sequences.empty();
            return result;
        } else {
            return none();
        }
    }
}
