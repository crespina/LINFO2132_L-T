package compiler;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import compiler.Lexer.Lexer;
import compiler.Lexer.Symbol;
import compiler.Parser.Statement;
import compiler.Parser.Parser;
import compiler.Parser.ParserException;


public class Compiler {

	
public static void main(String ... argv) throws IOException, ParserException {
		
		if (argv[0].equals("-lexer")) {
			FileReader reader = new FileReader(argv[1]);
			Lexer lexer = new Lexer(reader);
			lexer.lex();
			List<Symbol> lexedInput = lexer.getLexedInput();
			for (Symbol s : lexedInput) {
				System.out.println(s);
			}
		} 
		else if (argv[0].equals("-parser")) {
			FileReader reader = new FileReader(argv[1]);
			Lexer lexer = new Lexer(reader);
			Parser parser = new Parser(lexer);
			ArrayList<Statement> statements = parser.getAST();
			for (Statement s : statements) {
				System.out.println(s.toString());
			}
		} else {
			System.out.println("No filePath was passed as an argument");
		}
	}
}