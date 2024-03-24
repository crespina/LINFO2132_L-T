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
	 * 
	 */
	public int curIndex = 0;
	
	/**
	 * @param expectedToken
	 * @param expectedAttribute 
	 * @param curIndex
	 * @param lexedInput
	 * @return Symbol
	 * @throws ParserException
	 */
	public static Symbol match(String expectedToken, List<String> expectedAttribute, int curIndex, List<Symbol> lexedInput) throws ParserException {
	    Symbol lookahead = lexedInput.get(curIndex+1);
	    if(expectedAttribute != null) {
	    	if (! (lookahead.getToken().equals(expectedToken) && expectedAttribute.contains(lookahead.getAttribute()))) {
		        throw new ParserException("No");
		    } else {
		        curIndex++;
		        return lookahead;
		    }
	    } else {
	    	if (!(lookahead.getToken().equals(expectedToken))) {
		        throw new ParserException("No");
		    } else {
		        curIndex++;
		        return lookahead;
		    }
	    }	    
	}	
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Comment
	 * @throws ParserException
	 */
	public static Comment parseComment(int curIndex, List<Symbol> lexedInput) throws ParserException {
		String comment = Util.match("Comment", null, curIndex, lexedInput).getAttribute();
		return new Comment(comment);
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Type
	 * @throws ParserException
	 */
	public static Type parseType(int curIndex, List<Symbol> lexedInput) throws ParserException {
		List<String> types = new ArrayList<>(List.of("int", "float", "string", "bool", "void"));
		Symbol identifier = Util.match("Keyword", types, curIndex, lexedInput);
        return new Type((String) identifier.attribute);
    }
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Param
	 * @throws ParserException
	 */
	public static Param parseParam(int curIndex, List<Symbol> lexedInput) throws ParserException {
        Type type = (Type) Util.parseType(curIndex, lexedInput);
        Symbol identifier = Util.match("Identifier", null, curIndex, lexedInput);
        return new Param(type, (String) identifier.attribute);
    }
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return ArrayList<Param>
	 * @throws ParserException
	 */
	public static ArrayList<Param> parseParams(int curIndex, List<Symbol> lexedInput) throws ParserException{
		ArrayList<Param> parameters = new ArrayList<Param>();
		Symbol lookahead = lexedInput.get(curIndex);
		if(lookahead.getToken() != "CloseParenthesis") {
			parameters.add(Util.parseParam(curIndex, lexedInput));
			while(lookahead.getToken() == "Comma") {
				Util.match("Comma", null, curIndex, lexedInput);
				parameters.add(parseParam(curIndex, lexedInput));
			}
		}
		return parameters;
	}
	
	/**
	 * @param curIndex 
	 * @param lexedInput 
	 * @return Method
	 * @throws ParserException 
	 */
	public static Method parseMethod(int curIndex, List<Symbol> lexedInput) throws ParserException{
		Util.match("Keyword", new ArrayList<>(List.of("def")), curIndex, lexedInput);
		Type returnType = Util.parseType(curIndex, lexedInput);
		String name = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
		Util.match("OpenParenthesis", null, curIndex, lexedInput);
		ArrayList<Param> parameters = Util.parseParams(curIndex, lexedInput);
		Util.match("CloseParenthesis", null, curIndex, lexedInput);
		ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
		return new Method(name, returnType, parameters, body);
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Structure
	 * @throws ParserException
	 */
	public static Structure parseStructure(int curIndex, List<Symbol> lexedInput) throws ParserException {
		Util.match("Keyword", new ArrayList<>(List.of("struct")), curIndex, lexedInput);
		String name = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
		Util.match("OpenCurlyBraket", null, curIndex, lexedInput);
		ArrayList<Param> parameters = Util.parseParams(curIndex, lexedInput);
		Util.match("CloseCurlyBraket", null, curIndex, lexedInput);
		return new Structure(name, parameters);
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return StructureInstanciation
	 * @throws ParserException
	 */
	public static StructureInstanciation parseStructInstanciation (int curIndex, List<Symbol> lexedInput) throws ParserException {
		String structName = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
		String instanceName = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
		Util.match("Operation", new ArrayList<>(List.of("=")), curIndex, lexedInput);
		Util.match("Identifier", null, curIndex, lexedInput);
		Util.match("OpenParenthesis", null, curIndex, lexedInput);
		ArrayList<Statement> statements = Util.parseStatements(curIndex, lexedInput); //identifier or operation
		Util.match("CloseParenthesis", null, curIndex, lexedInput);
		return new StructureInstanciation(structName, instanceName, statements);
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return StructureAccess
	 * @throws ParserException
	 */
	public static StructureAccess parseStructAccess(int curIndex, List<Symbol> lexedInput) throws ParserException {
		
		try {
		    // table access
			ArrayAccess instance = Util.parseArrayAccess(curIndex, lexedInput);	
			Util.match("Dot", null, curIndex, lexedInput);
			String param = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
			return new StructureAccess(null, param, instance);
		} catch (ParserException e1) {
		    try {
		        // identifier
		    	String instance = Util.match("Identifier", null, curIndex, lexedInput).getAttribute(); 
				Util.match("Dot", null, curIndex, lexedInput);
				String param = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
				return new StructureAccess(instance, param, null);
		    } catch (ParserException e2) {
		        // Both attempts failed, throw an exception with both message
		        throw new ParserException(e1.getMessage() + e2.getMessage());
		    }
		}			
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Float
	 * @throws ParserException
	 */
	public static Float parseFloat(int curIndex, List<Symbol> lexedInput) throws ParserException {		
		try {
			String nom = Util.match("Number", null, curIndex, lexedInput).getAttribute();
			Util.match("Dot", null, curIndex, lexedInput);
			String denom = Util.match("Number", null, curIndex, lexedInput).getAttribute();
			return new Float(nom, denom);
		} catch(ParserException e) {
			throw e;
		}	
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Operator : accepted types: int float bool string ArrayAccess StructureAccess identifier
	 * @throws ParserException
	 */
	public static Operand parseOperand(int curIndex, List<Symbol> lexedInput) throws ParserException {
		
		int startIndex = curIndex;
		
		try { //bool
			Symbol bool = Util.match("Keyword", new ArrayList<>(List.of("true","false")), startIndex, lexedInput);
			return new Operand("bool", bool.getAttribute());
		} catch (ParserException ebool) {
			try { //float
				Float floa = Util.parseFloat(startIndex, lexedInput);
				return new Operand("float", floa);
			} catch (ParserException efloat) {
				try { //int
					Symbol in = Util.match("Number", null, startIndex, lexedInput);
					return new Operand("int", in.getAttribute());
				} catch (ParserException eint) {
					try { //Identifier
						Symbol identifier = Util.match("Identifier", null, startIndex, lexedInput);
						return new Operand("identifier", identifier.getAttribute());
					} catch (ParserException eidentifier) {
						try { //ArrayAccess
							ArrayAccess aa = Util.parseArrayAccess(startIndex, lexedInput);
							return new Operand("arrayAccess", aa);
						} catch (ParserException eaa) {
							try { //StructureAccess
								StructureAccess sa = Util.parseStructAccess(startIndex, lexedInput);
								return new Operand("structureAccess", sa);
							} catch (ParserException esa) {
								throw new ParserException("Not an operator");
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Operator
	 * @throws ParserException
	 */
	public static Operator parseOperator(int curIndex, List<Symbol> lexedInput) throws ParserException {
		Symbol op = Util.match("Operation", new ArrayList<>(List.of("+","-","*","/","==","!=","!","<",">",">=","<=","%","&&","||")), curIndex, lexedInput);
		return new Operator(op.getAttribute());
	}
	
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Operation
	 * @throws ParserException
	 */
	public static Operation parseOperation(int curIndex, List<Symbol> lexedInput) throws ParserException {
		Operand op1 = Util.parseOperand(curIndex, lexedInput);
		Operator op = Util.parseOperator(curIndex, lexedInput);
		Operand op2 = Util.parseOperand(curIndex, lexedInput);
		
		return new Operation(op1, op, op2);
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Operation
	 * @throws ParserException
	 */
	public static Operation parseOperations(int curIndex, List<Symbol> lexedInput) throws ParserException {
		
		try {
			Util.match("OpenParenthesis", null, curIndex, lexedInput);
			Operation o = parseOperations(curIndex, lexedInput);
			Util.match("CloseParenthesis", null, curIndex, lexedInput);
			return o;
		} catch (ParserException e) {
			try { //no parenthesis (ex : a + b + c + d)
				Operation op1 = Util.parseOperation(curIndex, lexedInput);
				Operator op = Util.parseOperator(curIndex, lexedInput);
				Operand op2 = Util.parseOperand(curIndex, lexedInput);
				Operation finalOperation = new Operation(op1, op, op2);
				while (lexedInput.get(curIndex+1).getAttribute().equals("Operation")) {
					Operator newop = Util.parseOperator(curIndex, lexedInput);
					Operand newop2 = Util.parseOperand(curIndex, lexedInput);
					finalOperation = new Operation(finalOperation, newop, newop2);
				}
				return finalOperation;
			} catch (ParserException e2) {
				throw new ParserException("The operation is not valid");
			}
		}
		
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Statement
	 * @throws ParserException
	 */
	public static Statement parseStatement(int curIndex, List<Symbol> lexedInput) throws ParserException {
		return null;
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return ArrayList<Statement>
	 * @throws ParserException
	 */
	public static ArrayList<Statement> parseStatements(int curIndex, List<Symbol> lexedInput) throws ParserException {
		return null;
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return Variable
	 * @throws ParserException
	 */
	public static Variable parseVariable(int curIndex, List<Symbol> lexedInput) throws ParserException {
	Boolean isFinal = false;
	try {
		Util.match("Keyword", new ArrayList<>(List.of("final")), curIndex, lexedInput);
		isFinal = true;		
	} catch (ParserException e) {
		throw e;
	}
	Type type = parseType(curIndex, lexedInput);
	String identifier = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
	Util.match("Operation", new ArrayList<>(List.of("=")), curIndex, lexedInput);
	Statement statement = parseStatement(curIndex, lexedInput);
	return new Variable(isFinal, type, identifier, statement);
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return VariableAssignation
	 * @throws ParserException
	 */
	public static VariableAssignation parseVariableAssign(int curIndex, List<Symbol> lexedInput) throws ParserException {
		int newIndex = curIndex;
		try {
			String identifier = Util.match("Identifier", null, newIndex, lexedInput).getAttribute(); //can also be table access
			Util.match("Operation", new ArrayList<>(List.of("=")), newIndex, lexedInput);
			Operation operation = parseOperations(newIndex, lexedInput);
			return new VariableAssignation(identifier, operation);
		} catch (ParserException e) {
			String identifier = Util.match("Identifier", null, newIndex, lexedInput).getAttribute(); //can also be table access
			Util.match("Operation", new ArrayList<>(List.of("=")), newIndex, lexedInput);
			Operand operand = parseOperand(newIndex, lexedInput);
			return new VariableAssignation(identifier, operand);
		}
		
		
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return ReturnStatement
	 * @throws ParserException
	 */
	public static ReturnStatement parseReturn(int curIndex, List<Symbol> lexedInput) throws ParserException {
		Util.match("Keyword", new ArrayList<>(List.of("return")), curIndex, lexedInput);
		Statement stmt = Util.parseStatement(curIndex, lexedInput);
		return new ReturnStatement(stmt);
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return String
	 * @throws ParserException
	 */
	public static String parseFunctionName(int curIndex, List<Symbol> lexedInput) throws ParserException {
		List<String> functions = new ArrayList<>(List.of("readInt", "readString", "writeInt", "readFloat", "writeFloat", "write", "writeln", "chr", "len", "floor", "free"));
		Symbol identifier = Util.match("Keyword", functions, curIndex, lexedInput);
        return identifier.getAttribute();
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return FunctionCall
	 * @throws ParserException
	 */
	public static FunctionCall parseFunctionCall(int curIndex, List<Symbol> lexedInput) throws ParserException {
		try {
			String functionName = Util.parseFunctionName(curIndex,lexedInput);
			Util.match("OpenParenthesis", null, curIndex, lexedInput);
			ArrayList<Statement> params = parseStatements(curIndex, lexedInput);
			return new FunctionCall(functionName, params);
		} catch (ParserException e) {
			String functionName = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
			Util.match("OpenParenthesis", null, curIndex, lexedInput);
			ArrayList<Statement> params = parseStatements(curIndex, lexedInput);
			return new FunctionCall(functionName, params);
		}
		
	}
	
	/**
	 * @param curIndex
	 * @param lexedInput
	 * @return ArrayAccess
	 * @throws ParserException
	 */
	public static ArrayAccess parseArrayAccess(int curIndex, List<Symbol> lexedInput) throws ParserException {
		String arrayName = Util.match("Identifier", null, curIndex, lexedInput).getAttribute();
		Util.match("OpenSquareBraket", null, curIndex, lexedInput);
		Operation op = Util.parseOperation(curIndex, lexedInput);
		Util.match("CloseSquareBraket", null, curIndex, lexedInput);
		return new ArrayAccess(arrayName, op);		
	}
}
