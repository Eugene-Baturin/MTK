import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTester {

    @Test
    public void testSimpleOperations() throws IOException {
        try {
            for (int i = 1, j = 1; i < 1000000; j++, i = i * j) {
                assertEquals(i + j, (new Parser(new Lexer(
                        new StringReader(i + " + " + j)))).parseExpression());
                assertEquals(i - j, (new Parser(new Lexer(
                        new StringReader(i + " - " + j)))).parseExpression());
                assertEquals(i * j, (new Parser(new Lexer(
                        new StringReader(i + " * " + j)))).parseExpression());
                assertEquals(i / j, (new Parser(new Lexer(
                        new StringReader(i + " / " + j)))).parseExpression());

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPower() throws IOException {
        try {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    assertEquals((int)Math.pow(i, j), (new Parser(new Lexer(
                            new StringReader(i + "^" + j)))).parseExpression());
                }
            }

            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    for(int k = 1; k < 4; k++) {
                        assertEquals((int) Math.pow(i,(int) Math.pow(j, k)), (new Parser(new Lexer(
                                new StringReader(i + "^" + j + "^" + k)))).parseExpression());
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnaryMinus() throws IOException {
        try {
            for (int i = 1, j = 1; i < 1000000; j++, i = i * j) {
                assertEquals(-i + j, (new Parser(new Lexer(
                        new StringReader("-" + i + " + " + j)))).parseExpression());
                assertEquals(i - j, (new Parser(new Lexer(
                        new StringReader(i + " + (-" + j + ")")))).parseExpression());
                assertEquals(-i - j, (new Parser(new Lexer(
                        new StringReader("-" + i + " + (-" + j + ")")))).parseExpression());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParenthesies() throws IOException {
        try {
            assertEquals(987, (new Parser(new Lexer(
                    new StringReader("987")))).parseExpression());
            assertEquals(-987, (new Parser(new Lexer(
                    new StringReader("-987")))).parseExpression());
            assertEquals(-987, (new Parser(new Lexer(
                    new StringReader("(-987)")))).parseExpression());
            assertEquals(-987, (new Parser(new Lexer(
                    new StringReader("((-987))")))).parseExpression());
            assertEquals(-986, (new Parser(new Lexer(
                    new StringReader("(-987) + 1")))).parseExpression());
            assertEquals(-986, (new Parser(new Lexer(
                    new StringReader("((-987)) + 1")))).parseExpression());
            assertEquals(-986, (new Parser(new Lexer(
                    new StringReader("((-987) + 1)")))).parseExpression());
            assertEquals(-986, (new Parser(new Lexer(
                    new StringReader("(((-987)) + 1)")))).parseExpression());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSomeExpressions() throws IOException {
        try {
            assertEquals(21, (new Parser(new Lexer(
                    new StringReader("2^2^2+4/2+15*3-(6*7)")))).parseExpression());
            assertEquals(-1711, (new Parser(new Lexer(
                    new StringReader("(-2^2^2)+32/2^(2+3)+(15-3)*3-(6*7)^2")))).parseExpression());
            assertEquals(-3372, (new Parser(new Lexer(
                    new StringReader("(32*4)/2^(2+3)+(-15)^3-(-6*7)^0")))).parseExpression());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkRightParent() throws IOException {
        try {
            (new Parser(new Lexer(new StringReader
                    ("(2^2^2")))).parseExpression();
        } catch (ParseException e) {
            assertEquals("There is no right parenthesis", e.getMessage());
        }
    }

    @Test
    public void checkLeftParent() throws IOException {
        try {
            (new Parser(new Lexer(new StringReader
                    ("2^2^2)")))).parseExpression();
        } catch (ParseException e) {
            assertEquals("There are no left parenthesies or numbers", e.getMessage());
        }
    }

    @Test
    public void checkNumber() throws IOException {
        try {
            (new Parser(new Lexer(new StringReader
                    ("()")))).parseExpression();
        } catch (ParseException e) {
            assertEquals("There are no left parenthesies or numbers", e.getMessage());
        }
    }
}
