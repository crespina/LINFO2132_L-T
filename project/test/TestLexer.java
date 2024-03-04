import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.io.FileReader;
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
        String input = "int = 2";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        try {
			lexer.lex();
			List<Symbol> lexedInput = lexer.getLexedInput();
			assertEquals(lexedInput.get(0).getToken(), "KeywordINT");
	        assertEquals(lexedInput.get(1).getToken(), "Operation");
	        assertEquals(lexedInput.get(1).getAttribute(), "=");
	        assertEquals(lexedInput.get(2).getToken(), "Number");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void test2() {

        String input = "for string bool while * !=";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordFOR");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordSTRING");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordBOOL");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordWHILE");
        assertEquals(lexer.getNextSymbol().getAttribute(), "*");
        assertEquals(lexer.getNextSymbol().getAttribute(), "!=");
    }
    
    @Test
    public void test3() throws IOException {

        String input = 
        		"\r\n"
        		+ "// this is a comment\r\n"
        		+ "int a = int b "
        		+ "a / b + c * d % e"
        		+ " a == b != c || d && s &"
        		+ "write(a,b);";
        
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);		
        assertEquals(lexer.getNextSymbol().getToken(), "Comment");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordINT");
        assertEquals(lexer.getNextSymbol().getAttribute(), "a");
        assertEquals(lexer.getNextSymbol().getAttribute(), "=");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordINT");
        assertEquals(lexer.getNextSymbol().getAttribute(), "b");
        assertEquals(lexer.getNextSymbol().getAttribute(), "a");
        assertEquals(lexer.getNextSymbol().getAttribute(), "/");
        assertEquals(lexer.getNextSymbol().getAttribute(), "b");
        assertEquals(lexer.getNextSymbol().getAttribute(), "+");
        assertEquals(lexer.getNextSymbol().getAttribute(), "c");
        assertEquals(lexer.getNextSymbol().getAttribute(), "*");
        assertEquals(lexer.getNextSymbol().getAttribute(), "d");
        assertEquals(lexer.getNextSymbol().getAttribute(), "%");
        assertEquals(lexer.getNextSymbol().getAttribute(), "e");
        assertEquals(lexer.getNextSymbol().getAttribute(), "a");
        assertEquals(lexer.getNextSymbol().getAttribute(), "==");
        assertEquals(lexer.getNextSymbol().getAttribute(), "b");
        assertEquals(lexer.getNextSymbol().getAttribute(), "!=");
        assertEquals(lexer.getNextSymbol().getAttribute(), "c");
        assertEquals(lexer.getNextSymbol().getAttribute(), "||");
        assertEquals(lexer.getNextSymbol().getAttribute(), "d");
        assertEquals(lexer.getNextSymbol().getAttribute(), "&&");
        assertEquals(lexer.getNextSymbol().getAttribute(), "s");
        assertEquals(lexer.getNextSymbol().getToken(), "IllegalToken");
        assertEquals(lexer.getNextSymbol().getToken(), "KeywordWRITE");
        assertEquals(lexer.getNextSymbol().getToken(), "OpenParenthesis");
        assertEquals(lexer.getNextSymbol().getAttribute(), "a");
        assertEquals(lexer.getNextSymbol().getToken(), "Colon");
        assertEquals(lexer.getNextSymbol().getAttribute(), "b");
        assertEquals(lexer.getNextSymbol().getToken(), "CloseParenthesis");
        assertEquals(lexer.getNextSymbol().getToken(), "Semicolon");
    }
    
    @Test
    public void bigtest() {
    	String file = "\r\n"
    			+ "                                                                                              \r\n"
    			+ "def int square(int v) {\r\n"
    			+ "    return v*v;\r\n"
    			+ "}\r\n"
    			+ "\r\n"
    			+ "def Point copyPoints(Point[] p) {\r\n"
    			+ "    return Point(p[0].x+p[1].x, p[0].y+p[1].y);\r\n"
    			+ "}\r\n"
    			+ "                            \r\n"
    			+ "def void main() {\r\n"
    			+ "    int value = readInt();                             \r\n"
    			+ "    writeln(square(value));\r\n"
    			+ "    int i;\r\n"
    			+ "    for (i=1, i<100, i = i+1) {\r\n"
    			+ "        while (value!=3) {\r\n"
    			+ "            if (i > 10){\r\n"
    			+ "                // ....\r\n"
    			+ "            } else {\r\n"
    			+ "                // ....\r\n"
    			+ "            }\r\n"
    			+ "        }\r\n"
    			+ "    }\r\n"
    			+ "    \r\n"
    			+ "    i = (i+2)*2;\r\n"
    			+ "} "; 
        try {
        	StringReader reader = new StringReader(file);
            Lexer lexer = new Lexer(reader);
            lexer.lex();
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("sould not fail");
		}
        
    }
}
