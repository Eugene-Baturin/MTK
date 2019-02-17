import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private int current;
    private Reader reader;

    public Lexer(final Reader reader) throws IOException {
        this.reader = reader;
        this.current = this.reader.read();
    }

    public Lexeme getLexeme() throws ParseException {
        try {
            while (Character.isWhitespace(current)) {
                current = reader.read();
            }

            switch (current) {
                case '+':
                    current = reader.read();
                    return new Lexeme(LexemeType.PLUS, "+");
                case '-':
                    current = reader.read();
                    return new Lexeme(LexemeType.MINUS, "-");
                case '*':
                    current = reader.read();
                    return new Lexeme(LexemeType.STAR, "*");
                case '/':
                    current = reader.read();
                    return new Lexeme(LexemeType.DIV, "/");
                case '^':
                    current = reader.read();
                    return new Lexeme(LexemeType.POW, "^");
                case '(':
                    current = reader.read();
                    return new Lexeme(LexemeType.LPAREN, "(");
                case ')':
                    current = reader.read();
                    return new Lexeme(LexemeType.RPAREN, ")");
                default:

                    if (Character.isDigit(current)) {
                        StringBuilder num = new StringBuilder();

                        while (Character.isDigit(current)){
                            num.append((char)current);
                            current = reader.read();
                        }

                        return new Lexeme(LexemeType.NUM, num.toString());
                    }

                    if (current == -1) {
                        return new Lexeme(LexemeType.EOF, null);
                    }

                    throw new ParseException("An uncorrect symbol was found", 0);
            }

        } catch (IOException e) {
            throw new ParseException("There are no symbols for reading", 0);
        }
    }
}
