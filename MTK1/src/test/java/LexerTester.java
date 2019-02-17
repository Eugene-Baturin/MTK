import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LexerTester {

    @Test
    public void checkTypes() throws IOException {
        Lexer lexer = new Lexer(new StringReader("+-*/^()364"));

        try {
            assertEquals(LexemeType.PLUS, lexer.getLexeme().getType());
            assertEquals(LexemeType.MINUS, lexer.getLexeme().getType());
            assertEquals(LexemeType.STAR, lexer.getLexeme().getType());
            assertEquals(LexemeType.DIV, lexer.getLexeme().getType());
            assertEquals(LexemeType.POW, lexer.getLexeme().getType());
            assertEquals(LexemeType.LPAREN, lexer.getLexeme().getType());
            assertEquals(LexemeType.RPAREN, lexer.getLexeme().getType());
            assertEquals(LexemeType.NUM, lexer.getLexeme().getType());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkWhiteSpaces() throws IOException {
        Lexer lexer = new Lexer(new StringReader("364\n + 444 "));

        try {
            assertEquals("364", lexer.getLexeme().getText());
            assertEquals(LexemeType.PLUS, lexer.getLexeme().getType());
            assertEquals("444", lexer.getLexeme().getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
