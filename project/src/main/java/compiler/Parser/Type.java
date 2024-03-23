package compiler.Parser;

import java.util.List;

import compiler.Lexer.Symbol;

/**
 * @author Crespin
 *
 */
public class Type {
	
	String identifier;
	
	/**
	 * @param ps 
	 * @param identifier
	 */
	public Type(String identifier) {
		this.identifier = identifier;
	}
}
