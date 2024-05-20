package compiler;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import compiler.Generator.CodeGenerator;
import compiler.Lexer.Lexer;
import compiler.Lexer.Symbol;
import compiler.Parser.Statement;
import compiler.Semantic.SemanticAnalysis;
import compiler.Semantic.SymbolTable;
import compiler.Parser.Parser;
import compiler.Parser.ParserException;


public class Compiler {

	
public static void main(String ... argv) throws IOException, ParserException {
	
		System.out.println("starting");
		
		/* 
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
			System.out.println("starting analysis");
			FileReader reader = new FileReader(argv[0]);
			Lexer lexer = new Lexer(reader);
			Parser parser = new Parser(lexer);
			SemanticAnalysis SA = new SemanticAnalysis(parser);
            SA.setSymbolTable();
            SA.doSemanticAnalysis();
		}
		*/
		FileReader reader = new FileReader(argv[0]);
		Lexer lexer = new Lexer(reader);
		Parser parser = new Parser(lexer);
		SemanticAnalysis SA = new SemanticAnalysis(parser);
		SA.setSymbolTable();
		SA.doSemanticAnalysis();
		ArrayList<Statement> statements = parser.getAST();
		try {
			CodeGenerator cg = new CodeGenerator(statements, argv[1]);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}