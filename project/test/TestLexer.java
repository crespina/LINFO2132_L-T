import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.StringReader;
import compiler.Lexer.Lexer;

public class TestLexer {
    
    @Test
    public void test1() {
        String input = "int = 2;";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordINT");
        assertEquals(lexer.getNextSymbol().getToken(), "EqualOperation");
        assertEquals(lexer.getNextSymbol().getToken(), "Number");
    }
    
    @Test
    public void test2() {

        String input = "for String bool while * !=";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordFOR");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordSTRING");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordBOOL");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordWHILE");
        assertEquals(lexer.getNextSymbol().getToken(), "MultOperation");
        assertEquals(lexer.getNextSymbol().getToken(), "DifferentComparison");
    }

}
