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
	static String[] reserved_names = new String[] {"readInt", "readString", "writeInt", "readFloat", "writeFloat", "write", "writeln", "chr", "len", "floor", "free", "true", "false", "int", "float", "string", "bool"};
	
	@Override
	public Type TypeCheck(ArrayAccess arrayAccess, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		//e.g. a[3]
		Statement index = arrayAccess.getIndex();
		Type index_type = index.acceptTypeCheck(this, st);
		if (!index_type.equals(new Type("int"))) {
			System.err.println("TypeError : the index to enter an array access is not an int");
			System.exit(1); //to be modified with correct number
		}
		
		String array_name = arrayAccess.getArray();
		ArrayList<Param> types = st.get(array_name);
		if (types == null) {
			System.err.println("ScopeError : the array we want to access is not in the ST");
			System.exit(7); //to be modified with correct number
		}
		
		Type typeOfArray = types.get(0).getType();
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
			System.err.println("TypeError : the length of the array isnt an int");
			System.exit(1); //to be modified with correct number
		}
		
		return new Type(keyword);
		
		
	}

	@Override
	public Type TypeCheck(Bool b, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		
		
		if (!(b.getContent().equals("true")) & !(b.getContent().equals("false")) ) {
			System.err.println("TypeError : a boolean must be true or false");
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
			System.err.println("MissingConditionError : " + "Condition "+ endValue.toString() + " is not a boolean");
			System.exit(5);
		}
		Variable increment = fl.getIncrement();
		Type incrementType = increment.acceptTypeCheck(this, st);
		return null;
	}

	@Override
	public Type TypeCheck(FunctionCall fc, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		ArrayList<Param> types_ST = st.get(fc.getFunctionName());
		ArrayList<Type> types = new ArrayList<Type>();
		
		for (Statement s : fc.getParams()){
			types.add(s.acceptTypeCheck(this, st));
		}
		
		if (types.size() != 0 ) {
			for (int i = 0; i<types_ST.size()-1; i++) {
				if (!types.get(i).equals(types_ST.get(i).getType())) {
					System.err.println("ArgumentError : the parameters at position " + i + "doesnt have the right type : expected type " + types_ST.get(i) + " received type " + types.get(i));
					System.exit(4);
				}
			}
		}
		

		
		return types_ST.get(types_ST.size()-1).getType();
		
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
	    	System.err.println("ScopeError : there is no if statement in the symbol table");
	    	System.exit(7);
	    }
	    
	    if(conditionstr == null & conditionop != null) {
	    	Type conditionop_type = conditionop.acceptTypeCheck(this, st);
	    	if (!conditionop_type.equals(new Type("bool"))) {
	    		System.err.println("MissingConditionError : the condition doesnt resolve to a bool but to " + conditionop_type);
	    		System.exit(5);
	    	}
	    } else {
	    	Type cond = null;
	    	if (st.contains(conditionstr)) {
	    		cond = st.get(conditionstr).get(0).getType();
	    	}
			if(conditionstr != "true" && conditionstr != "false" && !cond.equals(new Type("bool"))) {
	    		System.err.println("MissingConditionError : the condition doesnt resolve to a bool but to " + conditionstr);
	    		System.exit(5);
			}
	    }
	    
	    for (Statement s : body) {
	    	s.acceptTypeCheck(this, ifst);
	    }
	    
	    if(isElse) {
	    	
	    	SymbolTable elsest = st.getScopes("else");
		    if (elsest == null) {
		    	System.err.println("ScopeError : there is no else statement in the symbol table");
		    	System.exit(7);
		    }
		    
	    	for (Statement s : elseBody) {
	    		s.acceptTypeCheck(this, elsest);
	    	}
	    }
	    
	    return null;
	    
	}

	@Override
	public Type TypeCheck(Method m, SymbolTable st) throws SemanticException{
		ArrayList<Param> types_ST = st.get(m.getIdentifier());
		ArrayList<Param> params = m.getParameters();
		
		for (int i = 0; i < types_ST.size()-1; i++) {
			if (!params.get(i).getType().equals(types_ST.get(i).getType())) {
				System.err.println("ArgumentError : the parameters at position " + i + "doesnt have the right type : expected type " + types_ST.get(i) + " received type " + params.get(i));
				System.exit(4);
			}
		}
		
		Type expected_returnType = types_ST.get(types_ST.size()-1).getType();
		Type actual_returnType = m.getReturnType();
		
		if (!expected_returnType.equals(actual_returnType)) {
			System.err.println("ReturnError : the return type is not correct : expected type " + expected_returnType + " received type " + actual_returnType);
			System.exit(6);
		}
		
		SymbolTable newst = st.scopes.get(m.getIdentifier());
		ArrayList<Param> paramsOfTheFunction = st.paramOfAFunction(m.getIdentifier());
		for (Param param_funct : paramsOfTheFunction ) {
			ArrayList<Param> toAdd = new ArrayList<Param>();
			toAdd.add(param_funct);
			newst.addEntry(param_funct.getName(), toAdd);
		}
		
		if (newst == null) {
			System.err.println("ScopeError : the method is not in the symbolTable");
			System.exit(7);
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
		if (type.equals(new Type("identifier"))) {
			if (st.contains((String)od.getValue())) {
				type = st.get( (String)od.getValue() ).get(0).getType();
			}
		}
		String typeName = type.getIdentifier();
		
		if (typeName.equals("int")) {
			return new Type("int");
		} else if (typeName.equals("float")) {		
			//Number n = (Number) od.getValue();
			//return n.acceptTypeCheck(this, st); 
			return new Type("float");
		} else if (typeName.equals("identifier")) {
			
			String identifier = (String) od.getValue();
			ArrayList<Param> types = st.get(identifier);
			if (types == null) {
					System.err.println("ScopeError : " + "Variable "+ identifier + " is not defined");
					System.exit(7);
				}
			return types.get(0).getType(); 
			
		} else if (typeName.equals("arrayAccess")) {
			ArrayAccess aa = (ArrayAccess) od.getValue();
			return aa.acceptTypeCheck(this, st);
		} else if (typeName.equals("structureAccess")) {
			StructureAccess sa = (StructureAccess) od.getValue();
			return sa.acceptTypeCheck(this, st);
		} else if (typeName.equals("string")) {
			return new Type("String");
		} else if (typeName.equals("bool")) {
			return new Type("bool");
		} else {
			System.err.println("TypeError : Operand not recognized");
			System.exit(1);
		}
		return null;
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
				System.err.println("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
				System.exit(3);
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
				System.err.println("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
				System.exit(3);
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
				System.err.println("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
				System.exit(3);
			}
		}
		// 2 bool
		else if(type1.getIdentifier().equals("bool") && type1.equals(type2)) {
			// si operation ok
			if(isSpecialStr(operator.getOperation(), boolean_operators)) {
				return new Type("bool");
			}
			else {
				System.err.println("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
				System.exit(3);
			}
		}
		// 2 string
		else if(type1.getIdentifier().equals("string") && type1.equals(type2)) {
			if(operator.getOperation().equals("+") || operator.getOperation().equals("==") || operator.getOperation().equals("!=")) {
				return new Type("string");
			}
			else {
				System.err.println("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
				System.exit(3);
			}
		}
		else {
			System.err.println("OperatorError : " + "tried to do  "+ type1.getIdentifier() + " " + operator.getOperation() + " " + type2.getIdentifier());
			System.exit(1);
		}
		
		return null;
	
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
		// On recupere l'id du return et le nom de la methode a qui il appartient
		int returnId = rs.getId();
		String methodName = st.getReturn(returnId);
		// On recupere la valeur de retour de la methode
		ArrayList<Param> methodTypes = st.get(methodName);
		Statement s = rs.getReturnStatement();
		Type return_type = s.acceptTypeCheck(this, st);
		if(methodTypes != null) {
			if(!return_type.equals(methodTypes.get(methodTypes.size()-1).getType())) {
				System.err.println("ReturnError : the return type is not correct : expected type " + methodTypes.get(methodTypes.size()-1).getType() + " received type " + return_type );
				System.exit(6);
			}
		}
		return null;
	}

	@Override
	public Type TypeCheck(StringStmt ss, SymbolTable st) throws SemanticException{
		return new Type("string");
	}

	@Override
	public Type TypeCheck(Structure s, SymbolTable st) throws SemanticException{
		String struct_name = s.getName();
		
		if (isSpecialStr(struct_name, reserved_names)) {
			System.err.println("StructError : the structure name is not valid " + struct_name);
			System.exit(2);
		}
		
		if (!st.structContains(struct_name)) {
			System.err.println("ScopeError : the structure is not in the ST");
			System.exit(7);
		}
		return null;
	}

	@Override
	public Type TypeCheck(StructureAccess sa, SymbolTable st) throws SemanticException{
		// Check that instance already in the symbol table
		// Check that param is a param of the structure in the ST
		String instance = sa.getInstance();
		String param = sa.getParam();
		ArrayAccess instanceInArray = sa.getInstanceInArray(); //if it's in an array, i.e. e.g. a[3].x with a = Array(struct)
		Param correctParam = null;
		
		if (instance != null) { //a.y
			ArrayList<Param> params_ST = st.structures.get(instance);
			for (Param param_ST : params_ST) {
				if (param_ST.getName().equals(param)) {
					correctParam = param_ST;
				}
			}			
		}
		/*
		else if (instanceInArray != null) {
			String array = instanceInArray.getArray();
			Statement s = instanceInArray.getIndex();
			
			ArrayList<Param> params_ST = st.structures.get(array);
			for (Param param_ST : params_ST) {
				if (param_ST.getName().equals(param)) {
					correctParam = param_ST;
				}
			}
		}*/ //Doesnt work because the parser doesnt allow yet to create an array of struct
		
		if (correctParam == null) {
			System.err.println("ArgumentError : the parameter tried to be accessed" + param +" is not in the structure" + instance);
			System.exit(4);
		} else {
			return correctParam.getType();
		}
		
		
		
		return null;	
	
	}

	@Override
	public Type TypeCheck(StructureInstanciation si, SymbolTable st) throws SemanticException{
		// Check that structure name exists in the SymbolTable
		String structName = si.getStructName();
		String instanceName = si.getInstanceName();
		ArrayList<Statement> parameters = si.getStatements();
		
		if (isSpecialStr(structName, reserved_names)) {
			System.err.println("StructError : the structure name is not valid " + structName);
			System.exit(2);
		}
		
		Type type_of_instance = st.get(instanceName).get(0).getType();
		
		if (!type_of_instance.equals(new Type(structName))) {
			System.err.println("ScopeError : the struct you're trying to instanciate is not in the ST");
			System.exit(7);
		}
		Type t = null;
		ArrayList<Param> params_ST = st.structures.get(structName);
		for (int i = 0; i < parameters.size(); i++) {
			t = parameters.get(i).acceptTypeCheck(this, st);
			if (!t.equals(params_ST.get(i).getType())) {
				System.err.println("TypeError : the parameters at position " + i + "doesnt have the right type : expected type " +  params_ST.get(i).getType() + " actual type :" + t);
				System.exit(1);
			}
		}
		
		return new Type(structName);
		
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
		ArrayList<Param> types = st.get(name);
		if (types == null) {
			System.err.println("ScopeError : " + "Variable "+ name + " is not defined");
			System.exit(7);
		}
		Statement righStatement = v.getValue();
		Type rightType = righStatement.acceptTypeCheck(this, st);
		Type leftType = types.get(0).getType();
		if(!leftType.equals(rightType)) {
			System.err.println("TypeError : tried to assign " + rightType.getIdentifier() + " to " + leftType.getIdentifier()+ ", variable " + v.getVarName());
			System.exit(1);
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
				System.err.println("TypeError : tried to assign " + rightType.getIdentifier() + " to " + leftType.getIdentifier()+ ", variable " + vc.getIdentifier());
				System.exit(1);
			}
		}
		return leftType;
	}

	@Override
	public Type TypeCheck(WhileLoop wl, SymbolTable st) throws SemanticException{
		Operation op = wl.getCondition();
		String opstr = wl.getConditionStr();
		
		if (op != null) {
			Type conditionType = op.acceptTypeCheck(this, st);
			if(!conditionType.getIdentifier().equals("bool")) {
				System.err.println("MissingConditionError : " + "Condition Type =" + conditionType.getIdentifier());
				System.exit(5);
			}
			ArrayList <Statement> statements = wl.getBody();
			for (Statement s : statements) {
				s.acceptTypeCheck(this, st);
			}
			return new Type("whileLoop");
		}
		
		else if (opstr != null) {
			
	    	Type cond = null;
	    	if (st.contains(opstr)) {
	    		cond = st.get(opstr).get(0).getType();
	    	}
			if(opstr != "true" && opstr != "false" && !cond.equals(new Type("bool"))) {
	    		System.err.println("MissingConditionError : the condition doesnt resolve to a bool but to " + opstr);
	    		System.exit(5);
			}
		
		}
		
		return null;

	}
	
	@Override
	public Type TypeCheck(UnaryOperation uo, SymbolTable sT) throws SemanticException {
		// TODO Auto-generated method stub
		Operator op = uo.getOp();
		Operand operand = uo.getOperand();
		
		Type typeOperand = operand.acceptTypeCheck(this, sT);
		
		if (op.getOperation().equals("!")) {
			if (!typeOperand.equals(new Type("bool"))) {
				System.err.println("OperatorError : " + "tried to do  "+ op.getOperation() + " on  " + typeOperand);
				System.exit(3);
			} else {
				return new Type("bool");
			}
		} else if (op.getOperation().equals("-")) {
			if ( (!typeOperand.equals(new Type("int"))) && (!typeOperand.equals(new Type("float")))  ){
				System.err.println("OperatorError : " + "tried to do  "+ op.getOperation() + " on  " + typeOperand);
				System.exit(3);
			} else {
				return typeOperand;
			}
		} 
		return null;
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
