import java.util.*;
import java.math.*;
import static java.lang.Math.*;

$imports$

public class $className$ extends repler.java.REPL.Context{
    public Object eval() {
        $context.results: {result | $result.value.class.canonicalName$ $result.key$ = \$val("$result.key$"); // $result.key$ = $result.task.expression$
        }$

        Object \$ = null;

        $expression$;

        return \$;

    }
}
