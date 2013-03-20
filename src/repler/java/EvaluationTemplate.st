import java.util.*;
import java.math.*;
import static java.lang.Math.*;
$imports$
$newImport$

public class $className$ extends repler.java.EvaluationTemplate {
    public Object \$eval() {

        $_$        $context.evaluations: { evaluation | $evaluation.result.type$ $evaluation.result.key$ = \$val("$evaluation.result.key$"); // $evaluation.result.key$ = $evaluation.expression$
        }$

        $if(isAssignment)$
        return
        $_$        $expression$;
        $else$
        $_$        $expression$;
        return null;
$endif$



}}
