package compiler.Semantic;
import java.util.ArrayList;
import java.util.Arrays;

import compiler.Parser.*;
import compiler.Parser.Number;


public class SymbolTableVisitor implements Visitor {

	@Override
	public void visit(ArrayAccess aa, SymbolTable st) {
		// TODO Auto-generated method stub
		//leaf, do nothing
	}

	@Override
	public void visit(ArrayInit ai, SymbolTable st) {
		// TODO Auto-generated method stub
		//leaf, do nothing
	}

	@Override
	public void visit(Bool b, SymbolTable st) {
		// TODO Auto-generated method stub
		//leaf, do nothing
	}

	@Override
	public void visit(Comment c, SymbolTable st) {
		// TODO Auto-generated method stub
		//leaf, do nothing
	}

	@Override
	public void visit(ForLoop fl, SymbolTable st) {
		// TODO Auto-generated method stub
		
		ArrayList<Statement> body = fl.getBody();
		for (Statement stmt : body) {
			try {
				stmt.accept(this, st);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void visit(FunctionCall fc, SymbolTable st) {
		// TODO Auto-generated method stub
		// no declaration in a function call
	}

	@Override
	public void visit(IfCond ic, SymbolTable st) {
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
		}

	}

	@Override
	public void visit(Method m, SymbolTable st) {
		// TODO Auto-generated method stub
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
		}
	}

	@Override
	public void visit(Number n, SymbolTable st) {
		// TODO Auto-generated method stub
		// leaf node, nothing to do
	}

	@Override
	public void visit(Operand od, SymbolTable st) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Operation op, SymbolTable st) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Operator or, SymbolTable st) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Param p, SymbolTable st) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ReturnStatement rs, SymbolTable st) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StringStmt ss, SymbolTable st) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Structure s, SymbolTable st) {
		// TODO Auto-generated method stub
		String identifier = s.getName();
		st.addStructure(identifier);
		ArrayList<Statement> body = s.getBody();
		SymbolTable newst = new SymbolTable(st);
		for (Statement stmt : body) {
			try {
				stmt.accept(this, newst);
			} catch (SemanticException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void visit(StructureAccess sa, SymbolTable st) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StructureInstanciation si, SymbolTable st) {
		// TODO Auto-generated method stub
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
		}
	}

	@Override
	public void visit(Type t, SymbolTable st) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(Variable v, SymbolTable st) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VariableCreation vc, SymbolTable st) {
		// TODO Auto-generated method stub
		String identifier = vc.getIdentifier();
		Type type = vc.getType();
		ArrayList<Type> types = new ArrayList<Type>();
		types.add(type);
		st.addEntry(identifier, new Tuple(types,null));
		
	}

	@Override
	public void visit(WhileLoop wl, SymbolTable st) {
		ArrayList<Statement> body = wl.getBody();
		SymbolTable newst = new SymbolTable(st);
		for (Statement stmt : body) {
			try {
				stmt.accept(this, newst);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


}
