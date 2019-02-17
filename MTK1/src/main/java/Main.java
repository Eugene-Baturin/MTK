import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

public class Main {

    public static void main(final String args[]) {
        try {
            System.out.println((new Parser(new Lexer(new StringReader("4 + 5")))).parseExpression());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
