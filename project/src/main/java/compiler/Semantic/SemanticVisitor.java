package compiler.Semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import compiler.Parser.*;
import compiler.Parser.Number;

public class SemanticVisitor implements TypeCheckVisitor{
	
	static String[] comp_operators = new String[]{"==","!=","<",">",">=","<="};
	static String[] arith_operators = new String[]{"+","-","*","/"};
	static String[] boolean_operators = new String[]{"&&","||","==","!="};

	@Override
	public Type TypeCheck(ArrayAccess arrayAccess, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		//e.g. a[3]
		Statement index = arrayAccess.getIndex();
		Type index_type = index.acceptTypeCheck(this, st);
		if (!index_type.equals(new Type("int"))) {
			System.out.println("exit : the index to enter an array access is not an int");
			System.exit(1); //to be modified with correct number
		}
		
		String array_name = arrayAccess.getArray();
		ArrayList<Type> types = st.get(array_name);
		if (types == null) {
			System.out.println("the array we want to access is not in the ST");
			System.exit(1); //to be modified with correct number
		}
		
		Type typeOfArray = types.get(0);
		return typeOfArray;
		
		
	}

	@Override
	public Type TypeCheck(ArrayInit ai, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		//int[] a = int[3] -> right part
		
		String keyword = ai.getKeyword();
		ArrayList<Statement> values = ai.getValues();
		Type t = null;
		for (Statement s : values) {
			t = s.acceptTypeCheck(this, st);
		}
		
		if ((t == null) || (!t.equals(new Type("int")))) {
			System.out.println("the length of the array isnt an int");
			System.exit(1); //to be modified with correct number
		}
		
		return new Type(keyword);
		
		
	}

	@Override
	public Type TypeCheck(Bool b, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		
		if (!b.getContent().equals("true") || !b.getContent().equals("false") ) {
			System.out.println("a boolean must be true or false");
			System.exit(1);//to be modified with correct number
		}
		
		return b.getType();
	}

	@Override
	public Type TypeCheck(Comment c, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		return c.getType();
	}

	@Override
	public Type TypeCheck(ForLoop fl, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		return new Type("for");
	}

	@Override
	public Type TypeCheck(FunctionCall fc, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		return new Type("functionCall");
		
	}

	@Override
	public Type TypeCheck(IfCond ic, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		return new Type("ifCOnd");
	}

	@Override
	public Type TypeCheck(Method m, SymbolTable st) throws SemanticException{
		ArrayList <Statement> body = m.getBody();
		return new Type("method");
	}

	@Override
	public Type TypeCheck(Number n, SymbolTable st) throws SemanticException {
		return n.getType();
		
	}

	@Override
	public Type TypeCheck(Operand od, SymbolTable st) throws SemanticException{
		// Rien a faire
		Type t1;
		// Check si le type 1 est un identifier/arrayAccess/structureAccess pour mettre a jour le type depuis la ST
		switch (type1) {
			case "identifier":
				t1 = st.get(ope1.getValue().toString());
				type1 = t1.getIdentifier();
				break;
			case "arrayAccess":
				ArrayAccess aa = (ArrayAccess) ope1.getValue();
				t1 = st.get(aa.getArray());
				type1 = t1.getIdentifier();
				break;
			case "structureAccess":
				break;
			default:
				break;
		}
		return new Type("operand");
	}

	@Override
	public Type TypeCheck(Operation op, SymbolTable st) throws SemanticException{
		Type type1;
		try {
			Operation op1 = op.getOp1();
			type1 = op1.acceptTypeCheck(this, st);

		} catch (Exception e) {
			Operand ope1 = op.getOperand1();
			type1 = ope1.acceptTypeCheck(null, st);
		}

		Operator operator = op.getOperation();
		Operand ope2 = op.getOperand2();
		Type type2 = ope2.acceptTypeCheck(this, st);

		// 2 float
		if(type1.getIdentifier().equals("float") && type1.equals(type2)) {
			// si comparaison alors Boolean
			if(isSpecialStr(operator.getOperation(), comp_operators)) {
				return new Type("bool");
			}
			// si operation arithmétique alors float
			else if(isSpecialStr(operator.getOperation(), arith_operators)) {
				return new Type("float");
			}
			else {
				throw new SemanticException("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
			}
		}
		// float int && int float
		else if(type1.getIdentifier().equals("float") && type2.getIdentifier().equals("int") 
				|| type1.getIdentifier().equals("int") && type2.getIdentifier().equals("float")) {
			// si comparaison alors Boolean
			if(isSpecialStr(operator.getOperation(), comp_operators)) {
				return new Type("bool");
			}
			// si operation arithmétique alors float
			else if(isSpecialStr(operator.getOperation(), arith_operators)) {
				return new Type("float");
			}
			else {
				throw new SemanticException("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
			}
		}
		// 2 int
		else if(type1.getIdentifier().equals("int") && type1.equals(type2)) {
			// si / ou % alors float
			if(operator.getOperation().equals("/") || operator.getOperation().equals("%")) {
				return new Type("float");
			}
			// si operation arithmétique alors int
			else if(isSpecialStr(operator.getOperation(), arith_operators)) {
				return new Type("int");
			}
			// si comparaison alors Boolean
			else if(isSpecialStr(operator.getOperation(), comp_operators)) {
				return new Type("bool");
			}
			else {
				throw new SemanticException("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
			}
		}
		// 2 bool
		else if(type1.getIdentifier().equals("bool") && type1.equals(type2)) {
			// si operation ok
			if(isSpecialStr(operator.getOperation(), boolean_operators)) {
				return new Type("bool");
			}
			else {
				throw new SemanticException("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
			}
		}
		// 2 string
		else if(type1.getIdentifier().equals("string") && type1.equals(type2)) {
			if(operator.getOperation().equals("+") || operator.getOperation().equals("==") || operator.getOperation().equals("!=")) {
				return new Type("string");
			}
			else {
				throw new SemanticException("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
			}
		}
		else {
			throw new SemanticException("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
		}
	
	}

	@Override
	public Type TypeCheck(Operator or, SymbolTable st) throws SemanticException{
		// Rien a faire 
		return new Type(or.getOperation());
	}

	@Override
	public Type TypeCheck(Param p, SymbolTable st) throws SemanticException{
		// Rien a faire 
		return p.getType();
	}

	@Override
	public Type TypeCheck(ReturnStatement rs, SymbolTable st) throws SemanticException{
		// Peut etre check que le type du return est le meme que celui de la methode
		// JSP trop comment
		return new Type("return");
	}

	@Override
	public Type TypeCheck(StringStmt ss, SymbolTable st) throws SemanticException{
		return new Type("string");
	}

	@Override
	public Type TypeCheck(Structure s, SymbolTable st) throws SemanticException{
		return new Type("structure");
	}

	@Override
	public Type TypeCheck(StructureAccess sa, SymbolTable st) throws SemanticException{
		// Check that instance already in the symbol table
		// Check that param is a param of the structure in the ST
		return new Type("structAcc");
	}

	@Override
	public Type TypeCheck(StructureInstanciation si, SymbolTable st) throws SemanticException{
		// Check that structure name exists in the SymbolTable
		ArrayList <Statement> statements = si.getStatements();
		for (Statement s : statements) {
            try {
				s.acceptTypeCheck(this, st);
			} catch (SemanticException e) {
				e.printStackTrace();
			}
        }
		return new Type("structInst");
	}

	@Override
	public Type TypeCheck(Type t, SymbolTable st) throws SemanticException{
		// Rien a faire
		return t;
	}

	@Override
	public Type TypeCheck(Variable v, SymbolTable st) throws SemanticException{
		// Check if v.getVarName() is already in the ST
		String name = v.getVarName();
		ArrayList<Type> types = st.get(name);
		if (types == null) {
			throw new SemanticException("ScopeError : " + "Variable "+ name + " is not defined");
		}
		Statement righStatement = v.getValue();
		Type rightType = righStatement.acceptTypeCheck(this, st);
		Type leftType = types.get(0);
		if(!leftType.equals(rightType)) {
			throw new SemanticException("TypeError : tried to assign " + rightType.getIdentifier() + " to " + leftType.getIdentifier()+ ", variable " + vc.getIdentifier());
		}
		return leftType;
	}

	@Override
	public Type TypeCheck(VariableCreation vc, SymbolTable st) throws SemanticException{
		String identifier = vc.getIdentifier();
		Type leftType = vc.getType();
		// Check si cette variable existe deja 

		if(vc.getStatement() != null) {
			Statement rightStatement = vc.getStatement();
			Type rightType = rightStatement.acceptTypeCheck(this, st);
			if(!leftType.equals(rightType)) {
				throw new SemanticException("TypeError : tried to assign " + rightType.getIdentifier() + " to " + leftType.getIdentifier()+ ", variable " + vc.getIdentifier());
			}
		}
		return leftType;
	}

	@Override
	public Type TypeCheck(WhileLoop wl, SymbolTable st) throws SemanticException{
		wl.getCondition().acceptTypeCheck(null, st)(this, st);
		ArrayList <Statement> statements = wl.getBody();
		for (Statement s : statements) {
            try {
				s.acceptTypeCheck(this, st);
			} catch (SemanticException e) {
				e.printStackTrace();
			}
        }
		return new Type("whileLoop");
	}

	/**
     * @param s : {@link String} to be compared to the keywords list
	 * @param keywords : keyword list to check
     * @return {@link Boolean} true if s is a keyword (appear in the keywords list), false if not 
     */
    public static boolean isSpecialStr(String s, String[] keywords){
        for (int i=0; i<keywords.length; i++){
            if(keywords[i].equals(s)){
                return true;
            }
        }
        return false;
    }

}
