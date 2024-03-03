import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import compiler.Lexer.Lexer;
import compiler.Lexer.Symbol;

public class TestLexer {
    
    @Test
    public void test1() {
        String input = "int = 2;";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        try {
			lexer.lex();
			List<Symbol> lexedInput = lexer.getLexedInput();
			assertEquals(lexedInput.get(0).getToken(), "KeywordINT");
	        assertEquals(lexedInput.get(1).getToken(), "EqualOperation");
	        assertEquals(lexedInput.get(2).getToken(), "Number");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    
    public void bigtest() {
    	String filePath = "code_example.lang"; // Update the path to your file
        try {
			String input = Files.readString(Paths.get(filePath));
			StringReader reader = new StringReader(input);
			Lexer lexer = new Lexer(reader);
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("sould not fail");
		}
        
    }

}
