package javarepl;

import com.googlecode.totallylazy.Mapper;
import com.googlecode.totallylazy.Predicate;

import javax.tools.Diagnostic;
import java.io.File;
import java.util.Locale;

import static com.googlecode.totallylazy.Sequences.repeat;
import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.Strings.lines;
import static java.lang.String.format;
import static java.util.regex.Pattern.quote;
import static javax.tools.Diagnostic.Kind.ERROR;

public class ExpressionCompilationException extends Exception {

    public ExpressionCompilationException(File file, Iterable<? extends Diagnostic> diagnostics) {
        this(diagnosticsAsMessage(file, diagnostics));
    }

    public ExpressionCompilationException(String message) {
        super(message);
    }

    private static String diagnosticsAsMessage(final File file, final Iterable<? extends Diagnostic> diagnostics) {
        return sequence(diagnostics)
                .filter(isError())
                .map(diagnosticToMessage(file))
                .toString("\n");
    }

    private static Mapper<Diagnostic, String> diagnosticToMessage(final File file) {
        return new Mapper<Diagnostic, String>() {
            public String call(Diagnostic diagnostic) throws Exception {
                String line = lines(file).drop((int) diagnostic.getLineNumber() - 1).head();
                String marker = repeat(' ').take((int) diagnostic.getColumnNumber() - 1).toString("", "", "^");
                String message = diagnostic.getMessage(Locale.getDefault());
                String evaluationClass = file.getName().replaceAll("\\.java", "");
                return format("%s: %s\n%s\n%s", diagnostic.getKind(), message, line, marker)
                        .replaceAll(quote(evaluationClass), "Evaluation");
            }
        };
    }

    private static Predicate<Diagnostic> isError() {
        return new Predicate<Diagnostic>() {
            @Override
            public boolean matches(Diagnostic diagnostic) {
                return diagnostic.getKind() == ERROR;
            }
        };
    }
}
