import java.util.*;
import java.math.*;
import static java.lang.Math.*;

$imports$

public class $className$ extends repler.java.REPL.\$Context{
    public Object \$eval() {
        $_$        $context.evaluations: { evaluation |
        $evaluation.result.type$ $evaluation.result.key$ = \$val("$evaluation.result.key$"); // $evaluation.result.key$ = $evaluation.expression$
        }$

        Object \$ = null;

        $_$        $expression$;

        return \$;

    }
}
