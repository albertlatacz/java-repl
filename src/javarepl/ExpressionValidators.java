package javarepl;

import static java.util.regex.Pattern.compile;

public class ExpressionValidators {

    public static final String IDENTIFIER_PATTERN = "([a-zA-Z\\$_][a-zA-Z0-9\\$_]*)";
    public static final String ASSIGNMENT_PATTERN = IDENTIFIER_PATTERN + " *=[^=](.*)$";
    public static final String TYPE_PATTERN = "([a-zA-Z\\$_][a-zA-Z0-9\\.\\$_\\[\\]<> ]*)";
    public static final String ASSIGNMENT_WITH_TYPE_PATTERN = TYPE_PATTERN + " +" + ASSIGNMENT_PATTERN;
    public static final String IMPORT_PATTERN = "import .*";

    public static boolean isValidIdentifier(String string) {
        return compile(IDENTIFIER_PATTERN).matcher(string.trim()).matches();
    }

    public static boolean isValidAssignment(String string) {
        return compile(ASSIGNMENT_PATTERN).matcher(string.trim()).matches();
    }

    public static boolean isValidAssignmentWithType(String string) {
        return compile(ASSIGNMENT_WITH_TYPE_PATTERN).matcher(string.trim()).matches();
    }

    public static boolean isValidImport(String string) {
        return compile(IMPORT_PATTERN).matcher(string.trim()).matches();
    }

}
