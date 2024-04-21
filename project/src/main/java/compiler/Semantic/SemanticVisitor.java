package compiler.Semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import compiler.Parser.*;
import compiler.Parser.Number;

public class SemanticVisitor implements TypeCheckVisitor{

	static String[] comp_operators = new String[]{"==","!=","<",">",">=","<="};

	@Override
	public Type TypeCheck(ArrayAccess arrayAccess, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
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
		
		
	}

	@Override
	public Type TypeCheck(ArrayInit ai, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type TypeCheck(Bool b, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type TypeCheck(Comment c, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type TypeCheck(ForLoop fl, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type TypeCheck(FunctionCall fc, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		if(st.contains(fc.getFunctionName())) {
			for (Param p : funcT.get(fc.getFunctionName())) {
				// Verifier que chaque statement dans le funtionCall est du même type que la liste de param
			}
		}
		
	}

	@Override
	public Type TypeCheck(IfCond ic, SymbolTable st) throws SemanticException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type TypeCheck(Method m, SymbolTable st) throws SemanticException{
		for (Param p : m.getParameters()) {
			st.addEntry(p.getName(), p.getType());
		}
		ArrayList <Statement> body = m.getBody();

		if(body.get(body.size()-1).getType().getIdentifier().equals("ReturnStatement")) {
			ReturnStatement returnStmt = (ReturnStatement) body.get(body.size()-1);
			if(m.getReturnType().getIdentifier().equals("void")) {
				throw new SemanticException("ReturnError" + "void function " + m.getIdentifier() +  "cannot return anything");
			}
			// Je voulaias check le type de retour mais impossible, trop trop de cas pour le Statement dans ReturnStatement
			/* 
			if(!returnStmt.getReturnStatement().getType().equals(m.getReturnType().getIdentifier())) {
				throw new SemanticException("ReturnError" + m.getReturnType().getIdentifier() + " function " + m.getIdentifier() +  "returns" + body.get(body.size()-1).getType().getIdentifier());
			}*/
		}
		else {
			if(!m.getReturnType().getIdentifier().equals("void")) {
				throw new SemanticException("ReturnError" + m.getReturnType().getIdentifier() + " function " + m.getIdentifier() +  "must return something");
			}
		}
		for (Statement statement : body) {
            try {
				statement.accept(this, st, funcT);
			} catch (SemanticException e) {
				e.printStackTrace();
			}
        }
	}

	@Override
	public Type TypeCheck(Number n, SymbolTable st) throws SemanticException {
		// Rien a faire
		
	}

	@Override
	public Type TypeCheck(Operand od, SymbolTable st) throws SemanticException{
		// Rien a faire
		
	}

	@Override
	public Type TypeCheck(Operation op, SymbolTable st) throws SemanticException{
		String type1;
		Operand ope1;
		String type2;
		try {
			Operation op1 = op.getOp1();
			op1.accept(this, st, funcT);
			ope1 = null;
			type1 = op1.getVType().getIdentifier();
		} catch (Exception e) {
			ope1 = op.getOperand1();
			type1 = ope1.getType().getIdentifier();
		}
		Operator operator = op.getOperation();
		Operand ope2 = op.getOperand2();
		type2 = ope2.getType().getIdentifier();

		System.out.println(type1);
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
		Type t2;
		// Check si le type 2 est un identifier/arrayAccess/structureAccess pour mettre a jour le type depuis la ST
		switch (type2) {
			case "identifier":
				t2 = st.get(ope2.getValue().toString());
				type2 = t2.getIdentifier();
				break;
			case "arrayAccess":
				ArrayAccess aa = (ArrayAccess) ope1.getValue();
				t2 = st.get(aa.getArray());
				type1 = t2.getIdentifier();
				break;
			case "structureAccess":
				break;
		
			default:
				break;
		}

		// 2 float
		if(type1.equals("float") && type1.equals(type2)) {
			// si comparaison alors Boolean
			if(isSpecialStr(operator.getOperation(), comp_operators)) {
				op.setVType(new Type("bool"));
			}
			else {
				op.setVType(new Type("float"));
			}
		}
		// float int && int float
		else if(type1.equals("float") && type2.equals("int") || type1.equals("int") && type2.equals("float")) {
			// si comparaison alors Boolean
			if(isSpecialStr(operator.getOperation(), comp_operators)) {
				op.setVType(new Type("bool"));
			}
			else {
				op.setVType(new Type("float"));
			}
		}
		// 2 int
		else if(type1.equals("int") && type1.equals(type2)) {
			// si / ou % alors float
			if(operator.getOperation().equals("/") || operator.getOperation().equals("%")) {
				op.setVType(new Type("float"));
			}
			// si comparaison alors Boolean
			else if(isSpecialStr(operator.getOperation(), comp_operators)) {
				op.setVType(new Type("bool"));
			}
			else {
				op.setVType(new Type("int"));
			}
		}
		// 2 bool
		else if(type1.equals("bool") && type1.equals(type2)) {
			op.setVType(new Type("bool"));
		}

	}

	@Override
	public Type TypeCheck(Operator or, SymbolTable st) throws SemanticException{
		// Rien a faire 
		
	}

	@Override
	public Type TypeCheck(Param p, SymbolTable st) throws SemanticException{
		// Rien a faire 
		
	}

	@Override
	public Type TypeCheck(ReturnStatement rs, SymbolTable st) throws SemanticException{
		// Peut etre check que le type du return est le meme que celui de la methode
		// JSP trop comment
		
	}

	@Override
	public Type TypeCheck(StringStmt ss, SymbolTable st) throws SemanticException{
		// Rien a faire
	}

	@Override
	public Type TypeCheck(Structure s, SymbolTable st) throws SemanticException{
		
	}

	@Override
	public Type TypeCheck(StructureAccess sa, SymbolTable st) throws SemanticException{
		// Check that instance already in the symbol table
		// Check that param is a param of the structure in the ST
		
	}

	@Override
	public Type TypeCheck(StructureInstanciation si, SymbolTable st) throws SemanticException{
		// Check that structure name exists in the SymbolTable
		ArrayList <Statement> statements = si.getStatements();
		for (Statement s : statements) {
            try {
				s.accept(this, st, funcT);
			} catch (SemanticException e) {
				e.printStackTrace();
			}
        }
		
	}

	@Override
	public Type TypeCheck(Type t, SymbolTable st) throws SemanticException{
		// Rien a faire
		
	}

	@Override
	public Type TypeCheck(Variable v, SymbolTable st) throws SemanticException{
		// Check if v.getVarName() is already in the ST
		
	}

	@Override
	public Type TypeCheck(VariableCreation vc, SymbolTable st) throws SemanticException{
		String identifier = vc.getIdentifier();
		Type type = vc.getType();
		// Ajout de la variable dans la ST et check si final ou pas
		if(vc.getIsFinal())
			st.addEntry(identifier, new Type(type.getIdentifier()+"final"));
		else {
			st.addEntry(identifier, type);
		}

		if(vc.getStatement() != null) {
			// can be a Number (float/int), a String, a Boolean, an Operation, 
			Type t = vc.getStatement().getType();
			//Number float
			switch (t.getIdentifier()) {
				case "float":
					if(!vc.getType().equals(t)) {
						throw new SemanticException("TypeError : tried to assign " + t.getIdentifier() + " to " + vc.getType().getIdentifier()+ " variable " + vc.getIdentifier());
					}
					break;
				case "int":
					if(!vc.getType().equals(t)) {
						throw new SemanticException("TypeError : tried to assign " + t.getIdentifier() + " to " + vc.getType().getIdentifier()+ " variable " + vc.getIdentifier());
					}
					break;
				case "String":
					if(!vc.getType().equals(t)) {
						throw new SemanticException("TypeError : tried to assign " + t.getIdentifier() + " to " + vc.getType().getIdentifier()+ " variable " + vc.getIdentifier());
					}
					break;
				case "Bool":
					if(!vc.getType().equals(t)) {
						throw new SemanticException("TypeError : tried to assign " + t.getIdentifier() + " to " + vc.getType().getIdentifier()+ " variable " + vc.getIdentifier());
					}
					break;
				case "FunctionCall":
					FunctionCall fc = (FunctionCall) vc.getStatement();
					if(!st.contains(fc.getFunctionName())) {
						throw new SemanticException("ScopeError : " + "Function " + fc.getFunctionName() + " do not exists");
					}
					else if(!vc.getType().equals(st.get(fc.getFunctionName()))) {
						throw new SemanticException("TypeError : tried to assign " + st.get(fc.getFunctionName()) + " to " + vc.getType().getIdentifier()+ " variable " + vc.getIdentifier());
					}
				case "Operation":
					vc.getStatement().accept(this, st, funcT);
					Operation ope = (Operation) vc.getStatement();
					if(!vc.getType().equals(ope.getVType())) {
						throw new SemanticException("TypeError : tried to assign " + t.getIdentifier() + " " + ope.getVType().getIdentifier()+ " to " + vc.getType().getIdentifier() + " variable " + vc.getIdentifier());
					}
					break;
				default:
					break;
			}
		}
	}

	@Override
	public Type TypeCheck(WhileLoop wl, SymbolTable st) throws SemanticException{
		wl.getCondition().accept(this, st, funcT);
		ArrayList <Statement> statements = wl.getBody();
		for (Statement s : statements) {
            try {
				s.accept(this, st, funcT);
			} catch (SemanticException e) {
				e.printStackTrace();
			}
        }
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
