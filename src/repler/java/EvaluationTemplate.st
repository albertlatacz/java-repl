import java.util.*;
import java.math.*;
import static java.lang.Math.*;
$imports$
$newImport$

public class $className$ extends repler.java.EvaluationTemplate {
    public $if(isAssignment)$ Object $else$ void $endif$ \$eval() {

        $_$        $context.evaluations: { evaluation | $evaluation.result.type$ $evaluation.result.key$ = \$val("$evaluation.result.key$"); // $evaluation.result.key$ = $evaluation.expression$
        }$

        $if(isAssignment)$
        return
        $endif$
        $_$        $expression$;

}}
