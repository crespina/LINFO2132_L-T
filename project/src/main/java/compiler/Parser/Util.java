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
	public static int curIndex = 0;
	/**
	 * 
	 */
	public static List<Symbol> lexedInput;

	static String[] keywords_variable = new String[]{"int", "float", "string", "bool"};
	static String[] keywords_boolean = new String[]{"true", "false"};
	static String[] keywords_function_call = new String[]{"readInt", "readString", "writeInt", "readFloat", "writeFloat", "write", "writeln", "chr", "len", "floor", "free"};
	static List<String> operators = new ArrayList<>(List.of("+","-","*","/","==","!=","!","<",">",">=","<=","%","&&","||"));
	
	/**
	 * @param expectedToken
	 * @param expectedAttribute 
	 * @return Symbol
	 * @throws ParserException
	 */
	public static Symbol match(String expectedToken, List<String> expectedAttribute) throws ParserException {
	    Symbol lookahead = lexedInput.get(curIndex);
	    if(expectedAttribute != null) {
	    	if (! (lookahead.getToken().equals(expectedToken) && expectedAttribute.contains(lookahead.getAttribute()))) {
				/* 
				System.out.println(lookahead.getToken());
				System.out.println(expectedToken);
				System.out.println(lookahead.getAttribute());
				System.out.println(expectedAttribute);
				*/
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
	 * @return Comment
	 * @throws ParserException
	 */
	public static Comment parseComment() throws ParserException {
		String comment = Util.match("Comment", null).getAttribute();
		return new Comment(comment);
	}
	
	/**
	 * @return Type
	 * @throws ParserException
	 */
	public static Type parseType() throws ParserException {
		List<String> types = new ArrayList<>(List.of("int", "float", "string", "bool", "void"));
		Symbol identifier = Util.match("Keyword", types);
		int indexRead = 0;
		try {
			Util.match("OpenSquareBraket", null);
			indexRead++;
			Util.match("CloseSquareBraket", null);
			indexRead++;
			return new Type((String) identifier.attribute + "[]");
		} catch (ParserException e) {
			curIndex-=indexRead;
			return new Type((String) identifier.attribute);
		}		
    }
	
	/**
	 * @return Param
	 * @throws ParserException
	 */
	public static Param parseParam() throws ParserException {
        Type type = (Type) Util.parseType();
        Symbol identifier = Util.match("Identifier", null);
        return new Param(type, (String) identifier.attribute);
    }
	
	/**
	 * @return ArrayList<Param>
	 * @throws ParserException
	 */
	public static ArrayList<Param> parseParams() throws ParserException{
		ArrayList<Param> parameters = new ArrayList<Param>();
		Symbol lookahead = lexedInput.get(curIndex);
		if(lookahead.getToken() != "CloseParenthesis") {
			parameters.add(Util.parseParam());
			lookahead = lexedInput.get(curIndex);
			while(lookahead.getToken() == "Comma" || lookahead.getToken() == "SemiColon") {
				Util.match("Comma", null);
				parameters.add(parseParam());
				lookahead = lexedInput.get(curIndex);
			}
		}
		return parameters;
	}
	
	/**
	 * @return Method
	 * @throws ParserException 
	 */
	public static Method parseMethod() throws ParserException{
		Util.match("Keyword", new ArrayList<>(List.of("def")));
		Type returnType = Util.parseType();
		String name = Util.match("Identifier", null).getAttribute();
		Util.match("OpenParenthesis", null);
		ArrayList<Param> parameters = Util.parseParams();
		Util.match("CloseParenthesis", null);
		Util.match("OpenCurlyBraket", null);
		ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
		return new Method(name, returnType, parameters, body);
	}
	
	/**
	 * @return Structure
	 * @throws ParserException
	 */
	public static Structure parseStructure() throws ParserException {
		Util.match("Keyword", new ArrayList<>(List.of("struct")));
		String name = Util.match("Identifier", null).getAttribute();
		Util.match("OpenCurlyBraket", null);
		ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
		Util.match("CloseCurlyBraket", null);
		return new Structure(name, body);
	}
	
	/**
	 * @return StructureInstanciation
	 * @throws ParserException
	 */
	public static StructureInstanciation parseStructInstanciation () throws ParserException {
		String structName = Util.match("Identifier", null).getAttribute();
		String instanceName = Util.match("Identifier", null).getAttribute();
		try {
			Util.match("Operation", new ArrayList<>(List.of("=")));
			Util.match("Identifier", null);
			Util.match("OpenParenthesis", null);
			ArrayList<Statement> statements = Util.parseStatements(curIndex, lexedInput); //identifier or operation
			Util.match("CloseParenthesis", null);
			return new StructureInstanciation(structName, instanceName, statements);
		} catch (ParserException e) {
			return new StructureInstanciation(structName, instanceName, null);
		}

	}
	
	/**
	 * @return StructureAccess
	 * @throws ParserException
	 */
	public static StructureAccess parseStructAccess() throws ParserException {
		
		try {
		    // table access
			ArrayAccess instance = Util.parseArrayAccess();	
			Util.match("Dot", null);
			String param = Util.match("Identifier", null).getAttribute();
			return new StructureAccess(null, param, instance);
		} catch (ParserException e1) {
		    try {
		        // identifier
		    	String instance = Util.match("Identifier", null).getAttribute(); 
				Util.match("Dot", null);
				String param = Util.match("Identifier", null).getAttribute();
				return new StructureAccess(instance, param, null);
		    } catch (ParserException e2) {
		        // Both attempts failed, throw an exception with both message
		        throw new ParserException(e1.getMessage() + e2.getMessage());
		    }
		}			
	}
	
	/**
	 * @return Float
	 * @throws ParserException
	 */
	public static Number parseNumber() throws ParserException {	
		String value = Util.match("Number", null).getAttribute();	
		try {
			Util.match("Dot", null);
			String decim = Util.match("Number", null).getAttribute();
			return new Number(value + "." + decim, new Type("float"));
		} catch(ParserException e) {
			return new Number(value, new Type("int"));
		}	
	}
	
	/**
	 * @return Operator : accepted types: int float bool string ArrayAccess StructureAccess identifier
	 * @throws ParserException
	 */
	public static Operand parseOperand() throws ParserException {		
	
		try { //StructureAccess
			StructureAccess sa = Util.parseStructAccess();
			return new Operand("structureAccess", sa);
		} catch (ParserException esa) {
			try { //ArrayAccess
				ArrayAccess aa = Util.parseArrayAccess();
				return new Operand("arrayAccess", aa);
			} catch (ParserException eaa) {
				try { //Identifier
					Symbol identifier = Util.match("Identifier", null);
					return new Operand("identifier", identifier.getAttribute());
				} catch (ParserException eidentifier) {
					try { //Number
						Number floa = Util.parseNumber();
						return new Operand(floa.type.toString(), floa.value);
					} catch (ParserException efloat) {
						try { //bool
							Symbol bool = Util.match("Keyword", new ArrayList<>(List.of("true","false")));
							return new Operand("bool", bool.getAttribute());
						} catch (ParserException ebool) {
							throw new ParserException("Not an operator");
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * @return Operator
	 * @throws ParserException
	 */
	public static Operator parseOperator() throws ParserException {
		Symbol op = Util.match("Operation", new ArrayList<>(List.of("+","-","*","/","==","!=","!","<",">",">=","<=","%","&&","||")));
		return new Operator(op.getAttribute());
	}
	
	
	/**
	 * @return Operation
	 * @throws ParserException
	 */
	public static Operation parseOperation() throws ParserException {
		int indexlu = 0;
		try {
			Operand op1 = Util.parseOperand();
			indexlu = op1.value.toString().length();
			Operator op = Util.parseOperator();
			Operand op2 = Util.parseOperand();
			return new Operation(op1, op, op2);
		} catch (Exception e) {
			curIndex -= indexlu;
			throw new ParserException("Not an operator");
		}
	}
	
	/**
	 * @return Operation
	 * @throws ParserException
	 */
	public static Operation parseOperations() throws ParserException {
		
		try {
			Util.match("OpenParenthesis", null);
			Operation o = parseOperations();
			Util.match("CloseParenthesis", null);
			return o;
		} catch (ParserException e) {
			try { //no parenthesis (ex : a + b + c + d)
				Operation op1 = Util.parseOperation();
				Operator op = Util.parseOperator();
				Operand op2 = Util.parseOperand();
				Operation finalOperation = new Operation(op1, op, op2);
				while (lexedInput.get(curIndex+1).getAttribute().equals("Operation")) {
					Operator newop = Util.parseOperator();
					Operand newop2 = Util.parseOperand();
					finalOperation = new Operation(finalOperation, newop, newop2);
				}
				return finalOperation;
			} catch (ParserException e2) {
				throw new ParserException("The operation is not valid" + e2);
			}
		}
		
	}
	
	/**
	 * @return Statement
	 * @throws ParserException
	 */
	public static Statement parseStatement() throws ParserException {
		Symbol lookahead = lexedInput.get(curIndex);
		Symbol lookahead2 = lexedInput.get(curIndex+1);
		
		//System.out.println(lookahead.getAttribute());
		//System.out.println(lookahead.getToken());
		
		//System.out.println(curIndex);

		if(lookahead.getToken().equals("CloseCurlyBraket") || lookahead.getToken().equals("CloseParenthesis") || lookahead.getToken().equals("CloseSquareBraket")) {
			// End of the body of a function/for/if/while/struct
			return null;
		}
		
		else if (lookahead.getToken().equals("Semicolon") || lookahead.getToken().equals("Comma")) {
			curIndex++;
			lookahead = lexedInput.get(curIndex);
			if(curIndex > lexedInput.size()-2) {
				return null;
			}
			else {
				lookahead2 = lexedInput.get(curIndex+1);
			}
		}
		//System.out.println(lookahead.getAttribute());

		if(isKeyword(lookahead.getAttribute(), keywords_variable) || lookahead.getAttribute().equals("final")) {
			if(lookahead2.getToken().equals("OpenSquareBraket")) {
				Symbol lookahead3 = lexedInput.get(curIndex+2);
				if(!lookahead3.getToken().equals("CloseSquareBraket")) {
					return parseInitArray();
				}
				else {
					// Variable Creation
					return parseVariable();
				}
			}
			else {
				// Variable Creation
				return parseVariable();
			}
		}
		else if(lookahead.getToken().equals("Identifier")) {
			if (lookahead2.getToken().equals("OpenParenthesis")) {
				// Function call
				return parseFunctionCall();
			}
			else if(lookahead2.getToken().equals("Identifier")) {
				// Structure instance
				return parseStructInstanciation();
			}
			else if(lookahead2.getToken().equals("OpenSquareBraket")) {
				// Structure instance
				return parseArrayAccess();
			}
			else if(operators.contains(lookahead2.getAttribute())) {
				// identifier operation
				return parseOperation();
			}
			else {
				// Variable assign
				return parseVariableAssign();
			}
		}
		else if(lookahead.getToken().equals("Number")) {
			 
			try {
				return parseOperation();
			} catch (ParserException e) {
				return parseNumber();
			}
			/* 
			if(operators.contains(lookahead2.getAttribute())) {
				//Number operation
				System.out.println(lookahead.getToken());
				System.out.println(lookahead.getAttribute());
				return parseOperation();
			}
			else {
				// Single number
				return parseNumber();
			}*/
		}
		else if(lookahead.getToken().equals("String")) {
			// String 
			return parseString();
		}
		else if(isKeyword(lookahead.getAttribute(), keywords_boolean)) {
			// String 
			return parseBool();
		}
		else if(lookahead.getAttribute().equals("def")) {
			// Function creation
			return parseMethod();
		}
		else if(lookahead.getAttribute().equals("for")) {
			// For Loop
			return parseForLoop();
		}
		else if(lookahead.getAttribute().equals("while")) {
			//While Loop
			return parseWhileLoop();
		}
		else if(lookahead.getAttribute().equals("if")) {
			// If Cond
			return parseIfCond();
		}
		else if(lookahead.getAttribute().equals("struct")) {
			// Structure 
			return parseStructure();
		}
		else if(lookahead.getAttribute().equals("return")) {
			// return 
			return parseReturn();
		}
		else if(isKeyword(lookahead.getAttribute(), keywords_function_call)) {
			// Function call
			return parseFunctionCall();
		}
		throw new ParserException("Cannot begin statement with " + lookahead.toString());
	}
	
	/**
	 * @param index 
	 * @param input 
	 * @return ArrayList<Statement>
	 * @throws ParserException
	 */
	public static ArrayList<Statement> parseStatements(int index, List<Symbol> input) throws ParserException {
		curIndex = index;
		lexedInput = input;
		ArrayList<Statement> statements = new ArrayList<Statement>();
		while(true) {
			if(curIndex > lexedInput.size()-2) {
				return statements;
			}
			Statement s = parseStatement();
			if(s==null){
				return statements;
			}
			statements.add(s);
		}
	}
	
	/**
	 * @return Variable
	 * @throws ParserException
	 */
	public static Variable parseVariable() throws ParserException {
		Boolean isFinal = false;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("final")));
			isFinal = true;		
		} catch (ParserException e) {
			
		} 
		Type type = parseType();
		String identifier = Util.match("Identifier", null).getAttribute();
		try {
			Util.match("Operation", new ArrayList<>(List.of("=")));
			Statement statement = parseStatement();
			return new Variable(isFinal, type, identifier, statement);
		} catch (ParserException e2) {
			return new Variable(isFinal, type, identifier, null);
		}
	}
	
	/**
	 * @return VariableAssignation
	 * @throws ParserException
	 */
	public static VariableAssignation parseVariableAssign() throws ParserException {
		try {
			String identifier = Util.match("Identifier", null).getAttribute(); //can also be table access
			Util.match("Operation", new ArrayList<>(List.of("=")));
			Operation operation = parseOperations();
			return new VariableAssignation(identifier, operation);
		} catch (ParserException e) {
			String identifier = Util.match("Identifier", null).getAttribute(); //can also be table access
			Util.match("Operation", new ArrayList<>(List.of("=")));
			Operand operand = parseOperand();
			return new VariableAssignation(identifier, operand);
		}
		
		
	}
	
	/**
	 * @return ReturnStatement
	 * @throws ParserException
	 */
	public static ReturnStatement parseReturn() throws ParserException {
		Util.match("Keyword", new ArrayList<>(List.of("return")));
		Statement stmt = Util.parseStatement();
		return new ReturnStatement(stmt);
	}
	
	/**
	 * @return String
	 * @throws ParserException
	 */
	public static String parseFunctionName() throws ParserException {
		List<String> functions = new ArrayList<>(List.of("readInt", "readString", "writeInt", "readFloat", "writeFloat", "write", "writeln", "chr", "len", "floor", "free"));
		Symbol identifier = Util.match("Keyword", functions);
        return identifier.getAttribute();
	}
	
	/**
	 * @return FunctionCall
	 * @throws ParserException
	 */
	public static FunctionCall parseFunctionCall() throws ParserException {
		try {
			String functionName = Util.parseFunctionName();
			Util.match("OpenParenthesis", null);
			ArrayList<Statement> params = parseStatements(curIndex, lexedInput);
			Util.match("CloseParenthesis", null);
			return new FunctionCall(functionName, params);
		} catch (ParserException e) {
			String functionName = Util.match("Identifier", null).getAttribute();
			Util.match("OpenParenthesis", null);
			ArrayList<Statement> params = parseStatements(curIndex, lexedInput);
			Util.match("CloseParenthesis", null);
			return new FunctionCall(functionName, params);
		}
		
	}
	
	/**
	 * @return ArrayAccess
	 * @throws ParserException
	 */
	public static ArrayAccess parseArrayAccess() throws ParserException {
		String arrayName = Util.match("Identifier", null).getAttribute();
		Util.match("OpenSquareBraket", null);
		Statement op = Util.parseStatement();
		Util.match("CloseSquareBraket", null);
		return new ArrayAccess(arrayName, op);		
	}

	/**
	 * @return ForLoop
	 * @throws ParserException
	 */
	public static ForLoop parseForLoop() throws ParserException {
		Util.match("Keyword", new ArrayList<>(List.of("for")));
		Util.match("OpenParenthesis", null);
		Operation initValue = Util.parseOperation();
		Util.match("Comma", null);
		Operation endValue = Util.parseOperation();
		Util.match("Comma", null);
		Operation increment = Util.parseOperations();
		Util.match("CloseParenthesis", null);
		Util.match("OpenCurlyBraket", null);
		ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
		return new ForLoop(initValue, endValue, increment, body);
	}

	/**
	 * @return WhileLoop
	 * @throws ParserException
	 */
	public static WhileLoop parseWhileLoop() throws ParserException {
		Util.match("Keyword", new ArrayList<>(List.of("while")));
		Util.match("OpenParenthesis", null);
		Operation condition = Util.parseOperation();
		Util.match("CloseParenthesis", null);
		Util.match("OpenCurlyBraket", null);
		ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
		return new WhileLoop(condition, body);
	}

	/**
	 * @return IfCond
	 * @throws ParserException
	 */
	public static IfCond parseIfCond() throws ParserException {
		Util.match("Keyword", new ArrayList<>(List.of("if")));
		Util.match("OpenParenthesis", null);
		Operation condition = Util.parseOperation();
		Util.match("CloseParenthesis", null);
		Util.match("OpenCurlyBraket", null);
		ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
		Boolean isElse;
		ArrayList<Statement> elseBody;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("else")));
			Util.match("OpenCurlyBraket", null);
			isElse = true;
			elseBody = Util.parseStatements(curIndex, lexedInput);
		} catch (ParserException e1) {
			isElse = false;
			elseBody = null;
		}
		return new IfCond(condition, body, isElse, elseBody);
	}

	public static StringStmt parseString() throws ParserException {
		String s = Util.match("String", null).getAttribute();
		return new StringStmt(s);
	}

	public static Bool parseBool() throws ParserException {
		String b = Util.match("Keyword", new ArrayList<>(List.of("true", "false"))).getAttribute();
		return new Bool(b);
	}

	public static ArrayInit parseInitArray() throws ParserException {
		Type type = parseType();
		Util.match("OpenSquareBraket", null);
		ArrayList<Statement> values = Util.parseStatements(curIndex, lexedInput);
		Util.match("CloseSquareBraket", null);
		return new ArrayInit(type.identifier, values);
	}

	/**
     * @param s : {@link String} to be compared to the keywords list
	 * @param keywords : keyword list to check
     * @return {@link Boolean} true if s is a keyword (appear in the keywords list), false if not 
     */
    public static boolean isKeyword(String s, String[] keywords){
        for (int i=0; i<keywords.length; i++){
            if(keywords[i].equals(s)){
                return true;
            }
        }
        return false;
    }
}
