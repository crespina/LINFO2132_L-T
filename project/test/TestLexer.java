import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.StringReader;
import compiler.Lexer.Lexer;

public class TestLexer {
    
    @Test
    public void test() {
        String input = "int = 2;";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordInt");
        assertEquals(lexer.getNextSymbol().getToken(), "EqualOperation");
        assertEquals(lexer.getNextSymbol().getToken(), "Number");


        String input2 = "for String bool while * !=";
        StringReader reader2 = new StringReader(input2);
        Lexer lexer2 = new Lexer(reader2);
        assertEquals(lexer2.getNextSymbol().getToken(), "KeywordFor");
        assertEquals(lexer2.getNextSymbol().getToken(), "KeywordString");
        assertEquals(lexer2.getNextSymbol().getToken(), "KeywordBool");
        assertEquals(lexer2.getNextSymbol().getToken(), "KeywordWhile");
        assertEquals(lexer2.getNextSymbol().getToken(), "MultOperation");
        assertEquals(lexer2.getNextSymbol().getToken(), "DifferentComparison");
    }

}
