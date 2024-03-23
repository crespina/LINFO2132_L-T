package compiler.Parser;
import java.io.IOException;
import java.util.List;

import compiler.Lexer.Lexer;
import compiler.Lexer.Symbol;



/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Parser {

	Lexer lexer;
	List<Symbol> lexedInput;
	int curIndex;
	
	
	/**
	 * @param lexer 
	 */
	public Parser(Lexer lexer) {
		this.lexer = lexer;
		try {
			lexer.lex();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.lexedInput = lexer.getLexedInput();
		this.curIndex = 0;
	}
	
	/**
	 * 
	 */
	public void getAST() {
		return;
	}
	
}
