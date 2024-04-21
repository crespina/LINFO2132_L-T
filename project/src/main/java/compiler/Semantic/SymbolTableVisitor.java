package compiler.Semantic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import compiler.Parser.*;
import compiler.Parser.Number;


public class SymbolTableVisitor implements Visitor {

	@Override
	public void visit(ArrayAccess aa, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		//leaf, do nothing
	}

	@Override
	public void visit(ArrayInit ai, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		//leaf, do nothing
	}

	@Override
	public void visit(Bool b, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		//leaf, do nothing
	}

	@Override
	public void visit(Comment c, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		//leaf, do nothing
	}

	@Override
	public void visit(ForLoop fl, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		/* 
		ArrayList<Statement> body = fl.getBody();
		for (Statement stmt : body) {
			try {
				stmt.accept(this, st);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

	@Override
	public void visit(FunctionCall fc, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		// no declaration in a function call
	}

	@Override
	public void visit(IfCond ic, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		/* 
		ArrayList<Statement> body = ic.getBody();
		Boolean isElse = ic.getIsElse();
		ArrayList<Statement> elseBody = ic.getElseBody();
		
		SymbolTable newst = new SymbolTable(st);
		
		for (Statement stmt : body) {
			try {
				stmt.accept(this, newst);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		SymbolTable elseSt = new SymbolTable(st);
		if (isElse) {
			for (Statement stmt : elseBody) {
				try {
					stmt.accept(this, elseSt);
				} catch (SemanticException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/

	}

	@Override
	public void visit(Method m, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		st.addEntry(m.getIdentifier(), m.getReturnType());
		funcT.put(m.getIdentifier(), m.getParameters());
		/* 
		String identifier = m.getIdentifier();
		Type returnType = m.getReturnType();
		ArrayList<Param> params = m.getParameters();
		ArrayList<Type> paramsTypes = new ArrayList<Type>();
		for (Param p : params) {
			paramsTypes.add(p.getType());
		}
		paramsTypes.add(returnType);
		SymbolTable newst = new SymbolTable(st);
		Tuple t = new Tuple(paramsTypes,newst);	
		st.addEntry(identifier, t);
		ArrayList<Statement> body = m.getBody();	
		for (Statement stmt : body) {
			try {
				stmt.accept(this, newst);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

	@Override
	public void visit(Number n, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		// leaf node, nothing to do
	}

	@Override
	public void visit(Operand od, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Operation op, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Operator or, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Param p, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ReturnStatement rs, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StringStmt ss, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Structure s, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		
		String identifier = s.getName();
		st.addEntry(identifier, new Type("Structure"));
		ArrayList <Param> params = new ArrayList<>();
		for (Statement stmt : s.getBody()) {
			//Can be a Variable Creation
			switch (stmt.getType().getIdentifier()) {
				case "float":
					VariableCreation vc = (VariableCreation) stmt;
					params.add(new Param(vc.getType(), vc.getIdentifier()));
					break;
				case "int":
					VariableCreation vc1 = (VariableCreation) stmt;
					params.add(new Param(vc1.getType(), vc1.getIdentifier()));
					break;
				case "String":
					VariableCreation vc2 = (VariableCreation) stmt;
					params.add(new Param(vc2.getType(), vc2.getIdentifier()));
					break;
				case "Bool":
					VariableCreation vc3 = (VariableCreation) stmt;
					params.add(new Param(vc3.getType(), vc3.getIdentifier()));
					break;
				case "ArrayInit":
					ArrayInit ai = (ArrayInit) stmt;
					params.add(new Param(new Type("Array"), ai.getKeyword()));
					break;
				case "StructureInstanciation":
					StructureInstanciation si = (StructureInstanciation) stmt;
					params.add(new Param(new Type(si.getStructName()), si.getInstanceName()));
					break;
				default:
					break;
			}
		}
		funcT.put(identifier, params);
	}

	@Override
	public void visit(StructureAccess sa, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructureInstanciation si, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		/* 
		String identifier = si.getInstanceName();
		Type type = new Type(si.getStructName());
		ArrayList<Statement> body = si.getStatements();
		ArrayList<Type> types = new ArrayList<Type>();
		types.add(type);	
		SymbolTable newst = new SymbolTable(st);
		Tuple t = new Tuple(types,newst);	
		st.addEntry(identifier, t);
		for (Statement stmt : body) {
			try {
				stmt.accept(this, newst);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

	@Override
	public void visit(Type t, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(Variable v, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VariableCreation vc, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		// TODO Auto-generated method stub
		/* 
		String identifier = vc.getIdentifier();
		Type type = vc.getType();
		ArrayList<Type> types = new ArrayList<Type>();
		types.add(type);
		st.addEntry(identifier, new Tuple(types,null));
		*/
	}

	@Override
	public void visit(WhileLoop wl, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) {
		/* 
		ArrayList<Statement> body = wl.getBody();
		SymbolTable newst = new SymbolTable(st);
		for (Statement stmt : body) {
			try {
				stmt.accept(this, newst);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

	}


}
