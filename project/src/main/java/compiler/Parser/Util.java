package compiler.Parser;

import java.util.List;
import java.util.ArrayList;
import compiler.Lexer.Symbol;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Util {
	
	/**
	 * @param expectedToken
	 * @param curIndex
	 * @param lexedInput
	 * @return
	 * @throws ParserException
	 */
	public static Symbol match(String expectedToken, int curIndex, List<Symbol> lexedInput) throws ParserException {
	    Symbol lookahead = lexedInput.get(curIndex);
	    if (!lookahead.getToken().equals(expectedToken)) {
	        throw new ParserException("No");
	    } else {
	        curIndex++;
	        return lookahead;
	    }
	}	
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return
	 * @throws ParserException
	 */
	public static Type parseType(int curIndex, List<Symbol> lexedInput) throws ParserException {
        Symbol identifier = Util.match("Keyword", curIndex, lexedInput);
        return new Type((String) identifier.attribute);
    }
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return
	 * @throws ParserException
	 */
	public static Param parseParam(int curIndex, List<Symbol> lexedInput) throws ParserException {
        Type type = (Type) Util.parseType(curIndex, lexedInput);
        Symbol identifier = Util.match("Identifier", curIndex, lexedInput);
        return new Param(type, (String) identifier.attribute);
    }
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return
	 * @throws ParserException
	 */
	public static ArrayList<Param> parseParams(int curIndex, List<Symbol> lexedInput) throws ParserException{
		ArrayList<Param> parameters = new ArrayList<Param>();
		Symbol lookahead = lexedInput.get(curIndex);
		if(lookahead.getToken() != "CloseParenthesis") {
			parameters.add(Util.parseParam(curIndex, lexedInput));
			while(lookahead.getToken() == "Comma") {
				Util.match("Comma", curIndex, lexedInput);
				parameters.add(parseParam(curIndex, lexedInput));
			}
		}
		return parameters;
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return
	 * @throws ParserException
	 */
	public static Block parseBlock(int curIndex, List<Symbol> lexedInput) throws ParserException {
		return null;	
	}
	
	/**
	 * @param curIndex 
	 * @param lexedInput 
	 * @return
	 * @throws ParserException 
	 */
	public static Method parseMethod(int curIndex, List<Symbol> lexedInput) throws ParserException{
		Type returnType = Util.parseType(curIndex, lexedInput);
		String name = Util.match("Identifier", curIndex, lexedInput).getAttribute();
		Util.match("OpenParenthesis", curIndex, lexedInput);
		ArrayList<Param> parameters = Util.parseParams(curIndex, lexedInput);
		Util.match("CloseParenthesis", curIndex, lexedInput);
		Block body = Util.parseBlock(curIndex, lexedInput);
		return new Method(name, returnType, parameters, body);
	}
	
}