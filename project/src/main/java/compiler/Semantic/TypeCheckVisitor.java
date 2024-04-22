package compiler.Semantic;

import compiler.Parser.*;
import compiler.Parser.Number;

public interface TypeCheckVisitor {

	Type TypeCheck(ArrayAccess arrayAccess, SymbolTable st) throws SemanticException;

	Type TypeCheck(ArrayInit ai, SymbolTable st) throws SemanticException;

	Type TypeCheck(Bool b, SymbolTable st) throws SemanticException;

	Type TypeCheck(Comment c, SymbolTable st) throws SemanticException;

	Type TypeCheck(ForLoop fl, SymbolTable st) throws SemanticException;

	Type TypeCheck(FunctionCall fc, SymbolTable st) throws SemanticException;

	Type TypeCheck(IfCond ic, SymbolTable st) throws SemanticException;

	Type TypeCheck(Method m, SymbolTable st) throws SemanticException;

	Type TypeCheck(Number n, SymbolTable st) throws SemanticException;

	Type TypeCheck(Operand od, SymbolTable st) throws SemanticException;

	Type TypeCheck(Operation op, SymbolTable st) throws SemanticException;

	Type TypeCheck(Operator or, SymbolTable st) throws SemanticException;

	Type TypeCheck(Param p, SymbolTable st) throws SemanticException;

	Type TypeCheck(ReturnStatement rs, SymbolTable st) throws SemanticException;

	Type TypeCheck(StringStmt ss, SymbolTable st) throws SemanticException;

	Type TypeCheck(Structure s, SymbolTable st) throws SemanticException;

	Type TypeCheck(StructureAccess sa, SymbolTable st) throws SemanticException;

	Type TypeCheck(StructureInstanciation si, SymbolTable st) throws SemanticException;

	Type TypeCheck(Type t, SymbolTable st) throws SemanticException;

	Type TypeCheck(Variable v, SymbolTable st) throws SemanticException;

	Type TypeCheck(VariableCreation vc, SymbolTable st) throws SemanticException;

	Type TypeCheck(WhileLoop wl, SymbolTable st) throws SemanticException;
}
