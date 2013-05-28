package javarepl.client;

public final class ExpressionTemplate {
    private final String template;
    private final String token;

    ExpressionTemplate(String template, String token) {
        this.template = template;
        this.token = token;
    }

    public String template() {
        return template;
    }

    public String token() {
        return token;
    }
}
