package compiler.Semantic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import compiler.Parser.*;
import compiler.Parser.Number;


public class SymbolTableVisitor implements TableVisitor {

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
		
		SymbolTable newst = new SymbolTable();
		newst.addAll(st);
		
		
		for (Statement stmt : body) {
			try {
				stmt.accept(this, newst);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		st.addScope("if",newst);
		
		if (isElse) {
			for (Statement stmt : elseBody) {
				try {
					stmt.accept(this, newst);
				} catch (SemanticException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		st.addScope("else",newst);

	}

	@Override
	public void visit(Method m, SymbolTable st) {
		// TODO Auto-generated method stub
		
		String identifier = m.getIdentifier();
		Type returnType = m.getReturnType();
		ArrayList<Param> params = m.getParameters();

		params.add(new Param(returnType,null));
		
		st.addEntry(identifier, params);

		ArrayList<Statement> body = m.getBody();

		//Ajout des id des returns
		ArrayList<Integer> returnID = m.getReturnStatements();
		for(int id : returnID) {
			st.addReturn(id, identifier);
		}
		SymbolTable newst = new SymbolTable();
		newst.addAll(st);
		Set<Entry<String, ArrayList<Param>>> entries = st.entries.entrySet();
		for (Entry<String, ArrayList<Param>> entry : entries) {
			if (entry.getValue().size()==1) {
				newst.addEntry(entry.getKey(), entry.getValue());
			} else {
				ArrayList<Param> paramsToAdd = entry.getValue();
				for (int i = 0; i<paramsToAdd.size()-1; i++) {
					Param p = paramsToAdd.get(i);
					ArrayList<Param> toAdd = new ArrayList<Param>();
					toAdd.add(new Param(p.getType(),null));
					newst.addEntry(p.getName(), toAdd );
				}
			}
		}
		
		
		for (Statement stmt : body) {
			try {
				stmt.accept(this, newst);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		st.addScope(identifier,newst);
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
		ArrayList <Param> params = new ArrayList<>();
		
		
		for (Statement stmt : s.getBody()) {
			Param param = (Param) stmt;
			Type type_p = param.getType();
			params.add(new Param(type_p, param.getName()));
		}
		
		st.addStructure(identifier, params);
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
		ArrayList<Param> types = new ArrayList<Param>();
		types.add(new Param(type,null));
		st.addEntry(identifier, types);
	}

	@Override
	public void visit(Type t, SymbolTable st) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(Variable v, SymbolTable st) {
		// TODO Auto-generated method stub
		if (!st.contains(v.getVarName())) {
			System.out.println("The ST doesnt contains the variable " + v.getVarName());
			System.exit(6);
		}
		
		
	}

	@Override
	public void visit(VariableCreation vc, SymbolTable st) {
		// TODO Auto-generated method stub
		
		String identifier = vc.getIdentifier();
		Type type = vc.getType();
		ArrayList<Param> types = new ArrayList<Param>();
		types.add(new Param(type,vc.getIdentifier()));
		st.addEntry(identifier,types);
		
	}

	@Override
	public void visit(WhileLoop wl, SymbolTable st) {
		
		ArrayList<Statement> body = wl.getBody();
		for (Statement stmt : body) {
			try {
				stmt.accept(this, st);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


}
