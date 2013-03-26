package javarepl;

public interface EvaluationCallBack {
    public String nextExpression();

    public Boolean canEvaluate(String expression);

    public void evaluationSuccessful(Evaluation evaluation);

    public void evaluationError(String expression, Throwable error);
}
