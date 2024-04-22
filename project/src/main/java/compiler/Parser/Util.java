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
		        throw new ParserException("token " + expectedToken +" doesnt match with lookahead token " + lookahead.getToken() + " or attribute " + expectedAttribute + " doesnt match with lookahead "+lookahead.getAttribute());
		    } else {
		        curIndex++;
		        return lookahead;
		    }
	    } else {
	    	if (!(lookahead.getToken().equals(expectedToken))) {
		        throw new ParserException("token " + expectedToken +" doesnt match with lookahead token " + lookahead.getToken());
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
		int startIndex = curIndex;
		try {
			List<String> types = new ArrayList<>(List.of("int", "float", "string", "bool", "void"));
			Symbol identifier = Util.match("Keyword", types);
			try {
				Util.match("OpenSquareBraket", null);
				Util.match("CloseSquareBraket", null);
				return new Type((String) identifier.attribute + "[]");
			} catch (ParserException e) {
				curIndex--;
				return new Type((String) identifier.attribute);
			}	
		} catch (ParserException e2) {
			curIndex = startIndex;
			throw e2;
		}
    }
	
	/**
	 * @return Param
	 * @throws ParserException
	 */
	public static Param parseParam() throws ParserException {
		int startIndex = curIndex;
		try {
			Type type;
			int startIndex2 = curIndex;
			try {
				type = (Type) Util.parseType();
			} catch (ParserException e) {
				curIndex = startIndex2;
				Symbol typeSymbol = Util.match("Identifier", null);
				try {
					Util.match("OpenSquareBraket", null);
					Util.match("CloseSquareBraket", null);
					type = new Type(typeSymbol.getAttribute()+"[]");
				} catch (ParserException e2) {
					type = new Type(typeSymbol.getAttribute());
				}				
			}	
			curIndex++;
			Symbol identifier = Util.match("Identifier", null);
			return new Param(type, (String) identifier.attribute);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}
        
    }
	
	/**
	 * @return ArrayList<Param>
	 * @throws ParserException
	 */
	public static ArrayList<Param> parseParams() throws ParserException{
		int startIndex = curIndex;
		try {
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
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}
		
	}
	
	/**
	 * @return Method
	 * @throws ParserException 
	 */
	public static Method parseMethod() throws ParserException{
		int startIndex = curIndex;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("def")));
			Type returnType;
			int startIndex2 = curIndex;
			try {
				returnType = Util.parseType();
			} catch (ParserException e) {
				curIndex = startIndex2;
				String rettype = Util.match("Identifier", null).getAttribute(); //can also return a structure instance
				int startIndex3 = curIndex;
				try {
					Util.match("OpenSquareBraket", null);
					Util.match("CloseSquareBraket", null);
					returnType = new Type(rettype+"[]");
				} catch (ParserException e2) {
					curIndex = startIndex3;
					returnType = new Type(rettype);
				}				
			}			 
			String name = Util.match("Identifier", null).getAttribute();
			Util.match("OpenParenthesis", null);
			ArrayList<Param> parameters = Util.parseParams();
			Util.match("CloseParenthesis", null);
			Util.match("OpenCurlyBraket", null);
			ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
			Util.match("CloseCurlyBraket", null);
			return new Method(name, returnType, parameters, body);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}		
	}
	
	/**
	 * @return Structure
	 * @throws ParserException
	 */
	public static Structure parseStructure() throws ParserException {
		int startIndex = curIndex;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("struct")));
			String name = Util.match("Identifier", null).getAttribute();
			Util.match("OpenCurlyBraket", null);
			Symbol lookahead = lexedInput.get(curIndex);
			ArrayList<Param> parameters = new ArrayList<Param>();
			while(lookahead.getToken() != "CloseCurlyBraket") {
				parameters.add(parseParam());
				Util.match("Semicolon",null);
				lookahead = lexedInput.get(curIndex);
			}
			return new Structure(name, parameters);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}		
	}
	
	/**
	 * @return StructureInstanciation
	 * @throws ParserException
	 */
	public static StructureInstanciation parseStructInstanciation () throws ParserException {
		int startIndex = curIndex;
		try {
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
				curIndex = startIndex + 2;
				return new StructureInstanciation(structName, instanceName, null);
			}
		} catch (ParserException e2) {
			curIndex = startIndex;
			throw e2;
		}
	}
	
	/**
	 * @return StructureAccess
	 * @throws ParserException
	 */
	public static StructureAccess parseStructAccess() throws ParserException {
		int startIndex = curIndex;
		try {
			// table access
			ArrayAccess instance = Util.parseArrayAccess();	
			Util.match("Dot", null);
			String param = Util.match("Identifier", null).getAttribute();
			return new StructureAccess(null, param, instance);
		} catch (ParserException e1) {
			try {
				curIndex = startIndex;
				// identifier
				String instance = Util.match("Identifier", null).getAttribute(); 
				Util.match("Dot", null);
				String param = Util.match("Identifier", null).getAttribute();
				return new StructureAccess(instance, param, null);
			} catch (ParserException e2) {
				// Both attempts failed, throw an exception with both message
				curIndex = startIndex;
				throw new ParserException(e1.getMessage() + e2.getMessage());
			}
		}					
	}
	
	/**
	 * @return Float
	 * @throws ParserException
	 */
	public static Number parseNumber() throws ParserException {
		int startIndex = curIndex;
		try {
			String value = Util.match("Number", null).getAttribute();	
			try {
				Util.match("Dot", null);
				String decim = Util.match("Number", null).getAttribute();
				return new Number(value + "." + decim, new Type("float"));
			} catch(ParserException e) {
				curIndex = startIndex +1;
				return new Number(value, new Type("int"));
			}	
		} catch (ParserException e2) {
			curIndex = startIndex;
			throw e2;
		}
		
	}
	
	/**
	 * @return Operator : accepted types: int float bool string ArrayAccess StructureAccess identifier
	 * @throws ParserException
	 */
	public static Operand parseOperand() throws ParserException {	
		int startIndex = curIndex;
		try { //StructureAccess
			StructureAccess sa = Util.parseStructAccess();
			return new Operand("structureAccess", sa);
		} catch (ParserException esa) {
			try { //ArrayAccess
				curIndex = startIndex;
				ArrayAccess aa = Util.parseArrayAccess();
				return new Operand("arrayAccess", aa);
			} catch (ParserException eaa) {
				try { //Identifier
					curIndex = startIndex;
					Symbol identifier = Util.match("Identifier", null);
					return new Operand("identifier", identifier.getAttribute());
				} catch (ParserException eidentifier) {
					try { //Number
						curIndex = startIndex;
						Number floa = Util.parseNumber();
						return new Operand(floa.type.getIdentifier(), floa.value);
					} catch (ParserException efloat) {
						try { //bool
							curIndex = startIndex;
							Symbol bool = Util.match("Keyword", new ArrayList<>(List.of("true","false")));
							return new Operand("bool", bool.getAttribute());
						} catch (ParserException ebool) {
							curIndex = startIndex;
							throw new ParserException("Not an operand");
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
		int startIndex = curIndex;
		try {
			Symbol op = Util.match("Operation", new ArrayList<>(List.of("+","-","*","/","==","=","!=","!","<",">",">=","<=","%","&&","||")));
			return new Operator(op.getAttribute());
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}		
	}
	
	
	/**
	 * @return Operation
	 * @throws ParserException
	 */
	public static Operation parseOperation() throws ParserException {
		int startIndex = curIndex;
		try {
			Operand op1 = Util.parseOperand();
			Operator op = Util.parseOperator();
			Operand op2 = Util.parseOperand();
			return new Operation(op1, op, op2);
		} catch (Exception e) {
			curIndex = startIndex;
			throw new ParserException("Not an operation");
		}
	}
	
	/**
	 * @return Operation
	 * @throws ParserException
	 */
	public static Operation parseOperations() throws ParserException {
		int startIndex = curIndex;
		try {//case (a*2)+2
			Util.match("OpenParenthesis", null);
			Operation o = parseOperations();
			Util.match("CloseParenthesis", null);
			while ((lexedInput.get(curIndex).getAttribute() != null) && (lexedInput.get(curIndex).getToken().equals("Operation"))) {
				Operator newop = Util.parseOperator();
				Operand newop2 = Util.parseOperand();
				o = new Operation(o, newop, newop2);
			}
			return o;
		} catch (ParserException e) {
			try { //no parenthesis (ex : a + b + c + d)
				Operand op1 = Util.parseOperand();
				Operator op = Util.parseOperator();
				Operand op2 = Util.parseOperand();
				Operation finalOperation = new Operation(op1, op, op2);
				while ((lexedInput.get(curIndex).getAttribute() != null) && (lexedInput.get(curIndex).getToken().equals("Operation"))) {
					Operator newop = Util.parseOperator();
					Operand newop2 = Util.parseOperand();
					finalOperation = new Operation(finalOperation, newop, newop2);
				}
				return finalOperation;
			} catch (ParserException e2) {
				curIndex = startIndex;
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
		//System.out.println();
		//System.out.println(curIndex);
		
		if (lookahead.getToken().equals("Semicolon") || lookahead.getToken().equals("Comma")) {
			curIndex++;
			lookahead = lexedInput.get(curIndex);
			if(curIndex > lexedInput.size()-2) {
				return null;
			}
			else {
				lookahead2 = lexedInput.get(curIndex+1);
			}
		}

		if(lookahead.getToken().equals("CloseCurlyBraket") || lookahead.getToken().equals("CloseParenthesis") || lookahead.getToken().equals("CloseSquareBraket")) {
			// End of the body of a function/for/if/while/struct
			return null;
		}
		
		
		//System.out.println(lookahead.getAttribute());

		if( (lookahead.getAttribute() != null) && ( isKeyword(lookahead.getAttribute(), keywords_variable) || lookahead.getAttribute().equals("final"))) {
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
		else {
			int index = curIndex;
			if(lookahead2.getAttribute() != null) {
				if( (lookahead.getToken().equals("Identifier")) & (lookahead2.getAttribute().equals("=")) ) {
					// Variable assign
					return parseVariableAssign();
				}
			}
			
			try {
				Statement s = parseOperations();
				return s;
			} catch (ParserException e) {
				curIndex = index;
			}		
		
			if(lookahead.getToken().equals("Identifier")) {
				if (lookahead2.getToken().equals("OpenParenthesis")) {
					// Function call
					return parseFunctionCall();
				}
				else if(lookahead2.getToken().equals("Identifier")) {
					// Structure instance
					return parseStructInstanciation();
				}
				else if(lookahead2.getToken().equals("OpenSquareBraket")) {
					int index2 = curIndex;
					try {
						//Structure Access
						Statement s = parseStructAccess();
						return s;
					} catch (ParserException e) {
						// Structure instance
						curIndex = index2;
						return parseArrayAccess();
					}								
				}
				else if((operators.contains(lookahead2.getAttribute())) && (lookahead2.getAttribute() != null) && (!lookahead2.getAttribute().equals("="))) {
					// identifier operation
					return parseOperations();
				} 
				else {
					return parseOperand();
				}
				
			}
			else if(lookahead.getToken().equals("Number")) {
			 
				try {
					return parseOperations();
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
			else if (lookahead.getToken().equals("Comment")) {
				return parseComment();
			}
			else if(lookahead.getToken().equals("String")) {
				// String 
				return parseString();
			}
		
			else if (lookahead.getAttribute() != null) {
				if(isKeyword(lookahead.getAttribute(), keywords_boolean)) {
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
			}	
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
	 * @return VariableCreation
	 * @throws ParserException
	 */
	public static VariableCreation parseVariable() throws ParserException {
		int startIndex = curIndex;
		Boolean isFinal = false;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("final")));
			isFinal = true;		
		} catch (ParserException e) {
			
		} 
		try {
			Type type = parseType();
			String identifier = Util.match("Identifier", null).getAttribute();	
			int startIndex2 = curIndex;
			try {	
				Util.match("Operation", new ArrayList<>(List.of("=")));
				Statement statement = parseStatement();
				return new VariableCreation(isFinal, type, identifier, statement);
			} catch (ParserException e2) {
				curIndex = startIndex2;
				return new VariableCreation(isFinal, type, identifier, null);
			}
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}
		
	}
	
	/**
	 * @return VariableAssignation
	 * @throws ParserException
	 */
	public static Variable parseVariableAssign() throws ParserException {
		
		String identifier = Util.match("Identifier", null).getAttribute(); //can also be table access
		int startIndex = curIndex;
		try {
			Util.match("Operation", new ArrayList<>(List.of("=")));
			try {
				Operation operation = parseOperations();
				return new Variable(identifier, operation);
			} catch (ParserException e) {
				curIndex = startIndex +1 ;
				Operand operand = parseOperand();
				return new Variable(identifier, operand);
			}
		} catch (ParserException e) {
			return new Variable(identifier, null);
		}
	}
	
	/**
	 * @return ReturnStatement
	 * @throws ParserException
	 */
	public static ReturnStatement parseReturn() throws ParserException {
		int startIndex = curIndex;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("return")));
			Statement stmt = Util.parseStatement();
			return new ReturnStatement(stmt);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}
		
	}
	
	/**
	 * @return String
	 * @throws ParserException
	 */
	public static String parseFunctionName() throws ParserException {
		int startIndex = curIndex;
		try {
			List<String> functions = new ArrayList<>(List.of("readInt", "readString", "writeInt", "readFloat", "writeFloat", "write", "writeln", "chr", "len", "floor", "free"));
			Symbol identifier = Util.match("Keyword", functions);
			return identifier.getAttribute();
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}	
	}
	
	/**
	 * @return FunctionCall
	 * @throws ParserException
	 */
	public static FunctionCall parseFunctionCall() throws ParserException {
		int startIndex = curIndex;
		try {
			try {
				String functionName = Util.parseFunctionName();
				Util.match("OpenParenthesis", null);
				ArrayList<Statement> params = parseStatements(curIndex, lexedInput);
				Util.match("CloseParenthesis", null);
				return new FunctionCall(functionName, params);
			} catch (ParserException e) {
				curIndex = startIndex;
				String functionName = Util.match("Identifier", null).getAttribute();
				Util.match("OpenParenthesis", null);
				ArrayList<Statement> params = parseStatements(curIndex, lexedInput);
				Util.match("CloseParenthesis", null);
				return new FunctionCall(functionName, params);
			}
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}
	}
	
	/**
	 * @return ArrayAccess
	 * @throws ParserException
	 */
	public static ArrayAccess parseArrayAccess() throws ParserException {
		int startIndex = curIndex;
		try {
			String arrayName = Util.match("Identifier", null).getAttribute();
			Util.match("OpenSquareBraket", null);
			Statement op = Util.parseStatement();
			Util.match("CloseSquareBraket", null);
			return new ArrayAccess(arrayName, op);	
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}			
	}

	/**
	 * @return ForLoop
	 * @throws ParserException
	 */
	public static ForLoop parseForLoop() throws ParserException {
		int startIndex = curIndex;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("for")));
			Util.match("OpenParenthesis", null);
			Variable initValue = Util.parseVariableAssign();
			Util.match("Comma", null);
			Operation endValue = Util.parseOperations();
			Util.match("Comma", null);
			Variable increment = Util.parseVariableAssign();
			Util.match("CloseParenthesis", null);
			Util.match("OpenCurlyBraket", null);
			ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
			Util.match("CloseCurlyBraket", null);
			return new ForLoop(initValue, endValue, increment, body);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}		
	}

	/**
	 * @return WhileLoop
	 * @throws ParserException
	 */
	public static WhileLoop parseWhileLoop() throws ParserException {
		int startIndex = curIndex;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("while")));
			Util.match("OpenParenthesis", null);
			Operation condition = Util.parseOperation();
			Util.match("CloseParenthesis", null);
			Util.match("OpenCurlyBraket", null);
			ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
			Util.match("CloseCurlyBraket", null);
			return new WhileLoop(condition, body);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}	
	}

	/**
	 * @return IfCond
	 * @throws ParserException
	 */
	public static IfCond parseIfCond() throws ParserException {
		int startIndex = curIndex;
		try {
			Util.match("Keyword", new ArrayList<>(List.of("if")));
			Util.match("OpenParenthesis", null);
			int startIndex2 = curIndex;
			Operation conditionop = null;
			String conditionstr = null;
			try {
				conditionop = Util.parseOperations();
			} catch (ParserException e) {
				curIndex = startIndex2;
				conditionstr = Util.match("Identifier", null).getAttribute();
			}
			Util.match("CloseParenthesis", null);
			Util.match("OpenCurlyBraket", null);
			ArrayList<Statement> body = Util.parseStatements(curIndex, lexedInput);
			Util.match("CloseCurlyBraket", null);
			Boolean isElse;
			ArrayList<Statement> elseBody;
			int startIndex3 = curIndex;
			try {		
				Util.match("Keyword", new ArrayList<>(List.of("else")));
				Util.match("OpenCurlyBraket", null);
				isElse = true;
				elseBody = Util.parseStatements(curIndex, lexedInput);
				Util.match("CloseCurlyBraket", null);
			} catch (ParserException e1) {
				curIndex = startIndex3;
				isElse = false;
				elseBody = null;
			}	
			if (conditionop != null) {
				return new IfCond(conditionop, body, isElse, elseBody);
			} else if (conditionstr != null) {
				return new IfCond(conditionstr, body, isElse, elseBody);
			} else {
				throw new ParserException("The condition is not an operation nor a identifer");
			}
			
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}		
	}

	/**
	 * @return StringStmt
	 * @throws ParserException
	 */
	public static StringStmt parseString() throws ParserException {
		int startIndex = curIndex;
		try {
			String s = Util.match("String", null).getAttribute();
			return new StringStmt(s);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}	
	}

	/**
	 * @return Bool
	 * @throws ParserException
	 */
	public static Bool parseBool() throws ParserException {
		int startIndex = curIndex;
		try {
			String b = Util.match("Keyword", new ArrayList<>(List.of("true", "false"))).getAttribute();
			return new Bool(b);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}		
	}

	/**
	 * @return ArrayInit
	 * @throws ParserException
	 */
	public static ArrayInit parseInitArray() throws ParserException {
		int startIndex = curIndex;
		try {
			Type type = parseType();
			Util.match("OpenSquareBraket", null);
			ArrayList<Statement> values = Util.parseStatements(curIndex, lexedInput);
			Util.match("CloseSquareBraket", null);
			return new ArrayInit(type.identifier, values);
		} catch (ParserException e) {
			curIndex = startIndex;
			throw e;
		}
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
