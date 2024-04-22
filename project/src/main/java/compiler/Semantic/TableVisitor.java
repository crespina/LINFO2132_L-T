package compiler.Semantic;

import java.util.ArrayList;
import java.util.HashMap;

import compiler.Parser.*;
import compiler.Parser.Number;

public interface TableVisitor {

	void visit(ArrayAccess arrayAccess, SymbolTable st) throws SemanticException;

	void visit(ArrayInit ai, SymbolTable st) throws SemanticException;

	void visit(Bool b, SymbolTable st) throws SemanticException;

	void visit(Comment c, SymbolTable st) throws SemanticException;

	void visit(ForLoop fl, SymbolTable st) throws SemanticException;

	void visit(FunctionCall fc, SymbolTable st) throws SemanticException;

	void visit(IfCond ic, SymbolTable st) throws SemanticException;

	void visit(Method m, SymbolTable st) throws SemanticException;

	void visit(Number n, SymbolTable st) throws SemanticException;

	void visit(Operand od, SymbolTable st) throws SemanticException;

	void visit(Operation op, SymbolTable st) throws SemanticException;

	void visit(Operator or, SymbolTable st) throws SemanticException;

	void visit(Param p, SymbolTable st) throws SemanticException;

	void visit(ReturnStatement rs, SymbolTable st) throws SemanticException;

	void visit(StringStmt ss, SymbolTable st) throws SemanticException;

	void visit(Structure s, SymbolTable st) throws SemanticException;

	void visit(StructureAccess sa, SymbolTable st) throws SemanticException;

	void visit(StructureInstanciation si, SymbolTable st) throws SemanticException;

	void visit(Type t, SymbolTable st) throws SemanticException;

	void visit(Variable v, SymbolTable st) throws SemanticException;

	void visit(VariableCreation vc, SymbolTable st) throws SemanticException;

	void visit(WhileLoop wl, SymbolTable st) throws SemanticException;

	void visit(UnaryOperation unaryOperation, SymbolTable sT) throws SemanticException;
}
