package javarepl;

import com.googlecode.totallylazy.regex.Regex;

import static com.googlecode.totallylazy.regex.Regex.regex;

public class ExpressionValidators {

    public static final Regex identifierPattern = regex("([a-zA-Z\\$_][a-zA-Z0-9\\$_]*)");
    public static final Regex assignmentPattern = regex(identifierPattern + " *=[^=](.*)$");
    public static final Regex typePattern = regex("([a-zA-Z\\$_][a-zA-Z0-9\\.\\$_\\[\\]<> ]*)");
    public static final Regex assignmentWithTypePattern = regex(typePattern + " +" + assignmentPattern);
    public static final Regex importPattern = regex("import .*");

    public static boolean isValidIdentifier(String string) {
        return identifierPattern.matches(string.trim());
    }

    public static boolean isValidAssignment(String string) {
        return assignmentPattern.matches(string.trim());
    }

    public static boolean isValidAssignmentWithType(String string) {
        return assignmentWithTypePattern.matches(string.trim());
    }

    public static boolean isValidImport(String string) {
        return importPattern.matches(string.trim());
    }

}
