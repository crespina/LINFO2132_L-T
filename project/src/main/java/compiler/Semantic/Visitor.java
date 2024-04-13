package compiler.Semantic;

import java.util.ArrayList;
import java.util.Map;

import compiler.Parser.*;
import compiler.Parser.Number;

public interface Visitor {

	void visit(ArrayAccess arrayAccess, SymbolTable st);

	void visit(ArrayInit ai, SymbolTable st);

	void visit(Bool b, SymbolTable st);

	void visit(Comment c, SymbolTable st);

	void visit(ForLoop fl, SymbolTable st);

	void visit(FunctionCall fc, SymbolTable st);

	void visit(IfCond ic, SymbolTable st);

	void visit(Method m, SymbolTable st);

	void visit(Number n, SymbolTable st);

	void visit(Operand od, SymbolTable st);

	void visit(Operation op, SymbolTable st);

	void visit(Operator or, SymbolTable st);

	void visit(Param p, SymbolTable st);

	void visit(ReturnStatement rs, SymbolTable st);

	void visit(StringStmt ss, SymbolTable st);

	void visit(Structure s, SymbolTable st);

	void visit(StructureAccess sa, SymbolTable st);

	void visit(StructureInstanciation si, SymbolTable st);

	void visit(Type t, SymbolTable st);

	void visit(Variable v, SymbolTable st);

	void visit(VariableCreation vc, SymbolTable st);

	void visit(WhileLoop wl, SymbolTable st);
}
