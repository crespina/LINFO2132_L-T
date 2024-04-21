package compiler.Semantic;

import java.util.ArrayList;
import java.util.HashMap;

import compiler.Parser.*;
import compiler.Parser.Number;

public interface Visitor {

	void visit(ArrayAccess arrayAccess, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(ArrayInit ai, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Bool b, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Comment c, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(ForLoop fl, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(FunctionCall fc, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(IfCond ic, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Method m, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Number n, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Operand od, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Operation op, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Operator or, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Param p, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(ReturnStatement rs, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(StringStmt ss, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Structure s, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(StructureAccess sa, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(StructureInstanciation si, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Type t, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(Variable v, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(VariableCreation vc, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;

	void visit(WhileLoop wl, SymbolTable st, HashMap <String, ArrayList<Param>> funcT) throws SemanticException;
}
