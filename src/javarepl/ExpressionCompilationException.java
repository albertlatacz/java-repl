package javarepl;

class ExpressionCompilationException extends Exception {
    private final int code;

    ExpressionCompilationException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
