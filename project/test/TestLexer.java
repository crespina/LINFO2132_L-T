import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import compiler.Lexer.Lexer;
import compiler.Lexer.Symbol;
import compiler.Parser.*;
import compiler.Parser.Number;
import compiler.Semantic.SemanticAnalysis;
import compiler.Semantic.SymbolTable;

public class TestLexer {
/*
	@Test
    public void test0() {
        String input = "//Good luck\r\n"
        		+ "\r\n"
        		+ "final string message = \"Hello\";\r\n"
        		+ "final bool run = true;\r\n"
        		+ "\r\n"
        		+ "struct Point {\r\n"
        		+ "    int x;\r\n"
        		+ "    int y;\r\n"
        		+ "}\r\n"
        		+ "\r\n"
        		+ "int a = 3;\r\n"
        		+ "\r\n"
        		+ "def int square(int v) {\r\n"
        		+ "    return v*v;\r\n"
        		+ "}\r\n"
        		+ "\r\n"
        		+ "def void main() {\r\n"
        		+ "    int value = readInt();\r\n"
        		+ "    Point p = Point(a, a+value);\r\n"
        		+ "    writeInt(square(value));\r\n"
        		+ "    writeln();\r\n"
        		+ "    int i;\r\n"
        		+ "    for (i=1, i<a, i = i+1) {\r\n"
        		+ "        while (value!=0) {\r\n"
        		+ "           ; if (run){\r\n"
        		+ "                value = value - 1;\r\n"
        		+ "            } else {\r\n"
        		+ "                write(message);\r\n"
        		+ "            }\r\n"
        		+ "        }\r\n"
        		+ "    }\r\n"
        		+ "    i = (i+2)*2;\r\n"
        		+ "}";
        		 
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        try {
			lexer.lex();
			List<Symbol> lexedInput = lexer.getLexedInput();
			
            ArrayList<Statement> statements = parser.getAST();
            
            SemanticAnalysis SA = new SemanticAnalysis(parser);
            SA.setSymbolTable();

            SA.doSemanticAnalysis();
            
            System.out.println("end test 0");
            
            
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
    }*/
	

	@Test
    public void test1() {
        String input = "bool v = true;"
        		+ "def int sum(int a){"
        		+ "return a;"
        		+ "}"
        		+ "def int square(int v) {"
        		+ "return v*v ;"
        		+ "}";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        try {
			lexer.lex();
			List<Symbol> lexedInput = lexer.getLexedInput();
			
            ArrayList<Statement> statements = parser.getAST();

            SemanticAnalysis SA = new SemanticAnalysis(parser);
            SA.setSymbolTable();
            SymbolTable st = SA.getSt();
            System.out.println(st);
            SA.doSemanticAnalysis();
            System.out.println("end");
            
            
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    /**
     * 
     */
	/*
    @Test
    public void test1() {
        String input = "int a = 2";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        try {
			lexer.lex();
			List<Symbol> lexedInput = lexer.getLexedInput();
			assertEquals(lexedInput.get(0).getToken(), "Keyword");
	        assertEquals(lexedInput.get(1).getToken(), "Identifier");
	        assertEquals(lexedInput.get(2).getToken(), "Operation");
	        assertEquals(lexedInput.get(2).getAttribute(), "=");
	        assertEquals(lexedInput.get(3).getToken(), "Number");
			
            ArrayList<Statement> statements = Util.parseStatements(0, lexedInput);
            assertEquals(statements.get(0).getClass(), VariableCreation.class);
            Number a = new Number("2", new Type("int"));
            VariableCreation t = new VariableCreation(false, new Type("int"), "a", a);
            statements.get(0).equals(t);
            assertEquals(statements.get(0), t);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @Test
    public void testParser_Method() {
        String input = "def int square(int v, string s) {return v*v;}";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        try {
			lexer.lex();
			List<Symbol> lexedInput = lexer.getLexedInput();
            ArrayList<Statement> statements = Util.parseStatements(0, lexedInput);
            assertEquals(statements.get(0).getClass(), Method.class);
		} catch (IOException | ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void testParser_SimpleStructure() {
        String input = "struct Point { int x; int y;}";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        try {
            lexer.lex();
            List<Symbol> lexedInput = lexer.getLexedInput();
            ArrayList<Statement> statements = Util.parseStatements(0, lexedInput);
            assertEquals(statements.get(0).getClass(), Structure.class);
        } catch (IOException | ParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    @Test
    public void testParserHardStructure() {
        String input = "struct Person {\n string name;\n Point location;\n int[] history;\n }";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        try {
            lexer.lex();
            List<Symbol> lexedInput = lexer.getLexedInput();
            ArrayList<Statement> statements = Util.parseStatements(0, lexedInput);
            assertEquals(statements.get(0).getClass(), Structure.class);
        } catch (IOException | ParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Test
    public void test3() {

        String input = "for string bool while * !=";
        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        assertEquals(lexer.getNextSymbol().getToken(), "Keyword");
        assertEquals(lexer.getNextSymbol().getToken(), "Keyword");
        assertEquals(lexer.getNextSymbol().getToken(), "Keyword");
        assertEquals(lexer.getNextSymbol().getToken(), "Keyword");
        assertEquals(lexer.getNextSymbol().getAttribute(), "*");
        assertEquals(lexer.getNextSymbol().getAttribute(), "!=");

    }

    @Test
    public void test4() throws IOException {

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
        assertEquals(lexer.getNextSymbol().getToken(), "Keyword");
        assertEquals(lexer.getNextSymbol().getAttribute(), "a");
        assertEquals(lexer.getNextSymbol().getAttribute(), "=");
        assertEquals(lexer.getNextSymbol().getToken(), "Keyword");
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
        assertEquals(lexer.getNextSymbol().getToken(), "Keyword");
        assertEquals(lexer.getNextSymbol().getToken(), "OpenParenthesis");
        assertEquals(lexer.getNextSymbol().getAttribute(), "a");
        assertEquals(lexer.getNextSymbol().getToken(), "Comma");
        assertEquals(lexer.getNextSymbol().getAttribute(), "b");
        assertEquals(lexer.getNextSymbol().getToken(), "CloseParenthesis");
        assertEquals(lexer.getNextSymbol().getToken(), "Semicolon");
    }

    @Test
    public void bigtest() {
    	String file = "def void main() {\r\n"
    			+ "    int value = readInt();\r\n"
    			+ "    Point p = Point(a, a+value);\r\n"
    			+ "    writeInt(square(value));\r\n"
    			+ "    writeln();\r\n"
    			+ "    int i;\r\n"
    			+ "    for (i=1, i<a, i = i+1) {\r\n"
    			+ "        while (value!=0) {\r\n"
    			+ "            if (run){\r\n"
    			+ "                value = value - 1;\r\n"
    			+ "            } else {\r\n"
    			+ "                write(message);\r\n"
    			+ "            }\r\n"
    			+ "        }\r\n"
    			+ "    }\r\n"
    			+ "    i = (i+2)*2;\r\n"
    			+ "}"; 
        try {
        	StringReader reader = new StringReader(file);
            Lexer lexer = new Lexer(reader);
            lexer.lex();
            List<Symbol> lexedInput = lexer.getLexedInput();
            ArrayList<Statement> statements = Util.parseStatements(0, lexedInput);
            		
		} catch (IOException | ParserException e) {
			e.printStackTrace();
			fail("sould not fail");
		}
        
    }*/
    
}
