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
		Variable initValue = fl.getInitValue();
		Type initValueType = initValue.acceptTypeCheck(this, st);
		Operation endValue = fl.getEndValue();
		Type endValueType = endValue.acceptTypeCheck(this, st);
		if(!endValueType.getIdentifier().equals("bool")) {
			throw new SemanticException("MissingConditionError : " + "Condition "+ endValue.toString() + " is not a boolean"); 
		}
		Variable increment = fl.getIncrement();
		Type incrementType = increment.acceptTypeCheck(this, st);
		return null;
	}

	@Override
	public Type TypeCheck(FunctionCall fc, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		ArrayList<Type> types_ST = st.get(fc.getFunctionName());
		ArrayList<Type> types = new ArrayList<Type>();
		
		for (Statement s : fc.getParams()){
			types.add(s.acceptTypeCheck(this, st));
		}
		
		for (int i = 0; i<types_ST.size()-1; i++) {
			if (!types.get(i).equals(types_ST.get(i))) {
				System.out.println("the parameters at position " + i + "doesnt have the right type : expected type " + types_ST.get(i) + " received type " + types.get(i));
				System.exit(1);
			}
		}
		
		return types_ST.get(types_ST.size()-1);
		
	}

	@Override
	public Type TypeCheck(IfCond ic, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		// e.g. if (op) {} else {}
		//      if (op) {}
	    
	    Operation conditionop = ic.getConditionop();
	    String conditionstr = ic.getConditionstr(); //bool
	    ArrayList<Statement> body = ic.getBody();
		Boolean isElse = ic.getIsElse(); //bool
	    ArrayList<Statement> elseBody = ic.getElseBody();
	    
	    SymbolTable ifst = st.getScopes("if");
	    if (ifst == null) {
	    	System.out.println("there is no if statement in the symbol table");
	    	System.exit(1);
	    }
	    
	    if(conditionstr == null & conditionop != null) {
	    	Type conditionop_type = conditionop.acceptTypeCheck(this, st);
	    	if (!conditionop_type.equals(new Type("bool"))) {
	    		System.out.println("the condition doesnt resolve to a bool but to " + conditionop_type);
	    	}
	    } else {
	    	Type cond = null;
	    	if (st.contains(conditionstr)) {
	    		cond = st.get(conditionstr).get(0);
	    	}
			if(conditionstr != "true" || conditionstr != "false" || !cond.equals(new Type("bool"))) {
	    		System.out.println("the condition doesnt resolve to a bool but to " + conditionstr);
	    	}
	    }
	    
	    for (Statement s : body) {
	    	s.acceptTypeCheck(this, ifst);
	    }
	    
	    if(isElse) {
	    	
	    	SymbolTable elsest = st.getScopes("else");
		    if (elsest == null) {
		    	System.out.println("there is no else statement in the symbol table");
		    	System.exit(1);
		    }
		    
	    	for (Statement s : elseBody) {
	    		s.acceptTypeCheck(this, elsest);
	    	}
	    }
	    
	    return null;
	    
	}

	@Override
	public Type TypeCheck(Method m, SymbolTable st) throws SemanticException{
		ArrayList<Type> types_ST = st.get(m.getIdentifier());
		ArrayList<Param> params = m.getParameters();
		
		for (int i = 0; i < types_ST.size()-1; i++) {
			if (!params.get(i).equals(types_ST.get(i))) {
				System.out.println("the parameters at position " + i + "doesnt have the right type : expected type " + types_ST.get(i) + " received type " + params.get(i));
				System.exit(1);
			}
		}
		
		Type expected_returnType = types_ST.get(types_ST.size()-1);
		Type actual_returnType = m.getReturnType();
		
		if (!expected_returnType.equals(actual_returnType)) {
			System.out.println("the return type is not correct : expected type " + expected_returnType + " received type " + actual_returnType);
			System.exit(1);
		}
		
		SymbolTable newst = st.scopes.get(m.getIdentifier());
		
		if (newst == null) {
			System.out.println("the method is not in the symbolTable");
			System.exit(1);
		}
		
		//check return type with returnStatement
		
		for (Statement s : m.getBody()) {
			s.acceptTypeCheck(this, newst);
		}
		
		return null;
	}

	@Override
	public Type TypeCheck(Number n, SymbolTable st) throws SemanticException {
		return n.getType();
		
	}

	@Override
	public Type TypeCheck(Operand od, SymbolTable st) throws SemanticException{
		Type type = od.getType();
		String typeName = type.getIdentifier();
		switch (typeName) {
			case "int":
			case "float":
				Number n = (Number) od.getValue();
				return n.acceptTypeCheck(this, st);
			case "identifier":
				String identifier = (String) od.getValue();
				ArrayList<Type> types = st.get(identifier);
				if (types == null) {
					throw new SemanticException("ScopeError : " + "Variable "+ identifier + " is not defined");
				}
				return types.get(0);
			case "arrayAccess":
				ArrayAccess aa = (ArrayAccess) od.getValue();
				return aa.acceptTypeCheck(this, st);
			case "structureAccess":
				StructureAccess sa = (StructureAccess) od.getValue();
				return sa.acceptTypeCheck(this, st);
			default:
				throw new SemanticException("Operand not recognized");
		}
	}

	@Override
	public Type TypeCheck(Operation op, SymbolTable st) throws SemanticException{
		Type type1;
		try {
			Operation op1 = op.getOp1();
			type1 = op1.acceptTypeCheck(this, st);

		} catch (Exception e) {
			Operand ope1 = op.getOperand1();
			type1 = ope1.acceptTypeCheck(this, st);
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
		Statement s = rs.getReturnStatement();
		Type return_type = s.acceptTypeCheck(this, st);
		return return_type;
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
			throw new SemanticException("TypeError : tried to assign " + rightType.getIdentifier() + " to " + leftType.getIdentifier()+ ", variable " + v.getVarName());
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
		Operation op = wl.getCondition();
		Type conditionType = op.acceptTypeCheck(this, st);
		if(!conditionType.getIdentifier().equals("bool")) {
			throw new SemanticException("MissingConditionError : " + "Condition Type =" + conditionType.getIdentifier());
		}
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
