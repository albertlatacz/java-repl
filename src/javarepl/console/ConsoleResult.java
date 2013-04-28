package javarepl.console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleResult {
    private final String expression;
    private final List<ConsoleLog> logs;

    public ConsoleResult(String expression, List<ConsoleLog> logs) {
        this.expression = expression;
        this.logs = new ArrayList<ConsoleLog>(logs);
    }

    public String expression() {
        return expression;
    }

    public List<ConsoleLog> logs() {
        return logs;
    }

    public static ConsoleResult emptyResult() {
        return new ConsoleResult("", Collections.<ConsoleLog>emptyList());
    }
}
