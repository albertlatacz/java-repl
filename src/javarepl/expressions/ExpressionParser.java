package javarepl.expressions;

import static javarepl.expressions.Patterns.*;

public final class ExpressionParser {

    public static Expression parseExpression(String expression) {
        if (isValidImport(expression))
            return new Import(expression);

        if (isValidType(expression))
            return new Type(expression);

        if (isValidMethod(expression))
            return new Method(expression);

        if (isValidAssignmentWithType(expression))
            return new AssignmentWithType(expression);

        if (isValidAssignment(expression))
            return new Assignment(expression);

        return new Value(expression);
    }
}
