public class Lexeme {
    private final LexemeType type;
    private final String text;

    public Lexeme(final LexemeType type, final String text) {
        this.type = type;
        this.text = text;
    }

    public LexemeType getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
