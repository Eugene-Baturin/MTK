import java.text.ParseException;

public class Parser {
    private Lexer lexer;
    private Lexeme current;

    public Parser(final Lexer lexer) throws ParseException {
        this.lexer = lexer;
        this.current = this.lexer.getLexeme();
    }

    public int parseExpression() throws ParseException {
        int temp = parseTerm();

        while (current.getType() == LexemeType.PLUS ||
                current.getType() == LexemeType.MINUS) {

            if (current.getType() == LexemeType.PLUS) {
                current = lexer.getLexeme();
                temp += parseTerm();
            }

            if (current.getType() == LexemeType.MINUS) {
                current = lexer.getLexeme();
                temp -= parseTerm();
            }
        }

        return temp;
    }

    private int parseTerm() throws ParseException {
        int temp = parseFactor();

        while (current.getType() == LexemeType.STAR ||
                current.getType() == LexemeType.DIV) {

            if (current.getType() == LexemeType.STAR) {
                current = lexer.getLexeme();
                temp *= parseFactor();
            }

            if (current.getType() == LexemeType.DIV) {
                current = lexer.getLexeme();
                temp /= parseFactor();
            }
        }

        return temp;
    }

    public int parseFactor() throws ParseException {
        int temp = parsePower();

        if (current.getType() == LexemeType.POW) {
            current = lexer.getLexeme();
            return (int)Math.pow(temp, parseFactor());
        }

        return temp;
    }

    public int parsePower() throws ParseException {

        if (current.getType() == LexemeType.MINUS) {
            current = lexer.getLexeme();
            return -parseAtom();
        }

        return parseAtom();
    }

    private int parseAtom() throws ParseException {
        int temp;

        if (current.getType() == LexemeType.NUM) {
            try {
                temp = Integer.parseInt(current.getText());
            } catch (NumberFormatException e) {
                throw new ParseException("There is uncorrect number", 0);
            }
            current = lexer.getLexeme();

            return temp;
        }

        if (current.getType() == LexemeType.LPAREN) {
            current = lexer.getLexeme();
            temp = parseExpression();

            if (current.getType() != LexemeType.RPAREN) {
                throw new ParseException("There is no right parenthesis", 0);
            }
            current = lexer.getLexeme();

            return temp;
        }

        throw new ParseException("There are no left parenthesies or numbers", 0);
    }
}
