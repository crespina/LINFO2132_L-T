package compiler.Generator;

import org.objectweb.asm.MethodVisitor;

import compiler.Parser.*;
import compiler.Semantic.*;
import compiler.Parser.Number;

public interface GenVisitor {

    void visit(ArrayAccess arrayAccess, MethodVisitor mv) throws SemanticException;

	void visit(ArrayInit ai, MethodVisitor mv) throws SemanticException;

	void visit(Bool b, MethodVisitor mv) throws SemanticException;

	void visit(Comment c, MethodVisitor mv) throws SemanticException;

	void visit(ForLoop fl, MethodVisitor mv) throws SemanticException;

	void visit(FunctionCall fc, MethodVisitor mv) throws SemanticException;

	void visit(IfCond ic, MethodVisitor mv) throws SemanticException;

	void visit(Method m, MethodVisitor mv) throws SemanticException;

	void visit(Number n, MethodVisitor mv) throws SemanticException;

	void visit(Operand od, MethodVisitor mv) throws SemanticException;

	void visit(Operation op, MethodVisitor mv) throws SemanticException;

	void visit(Operator or, MethodVisitor mv) throws SemanticException;

	void visit(Param p, MethodVisitor mv) throws SemanticException;

	void visit(ReturnStatement rs, MethodVisitor mv) throws SemanticException;

	void visit(StringStmt ss, MethodVisitor mv) throws SemanticException;

	void visit(Structure s, MethodVisitor mv) throws SemanticException;

	void visit(StructureAccess sa, MethodVisitor mv) throws SemanticException;

	void visit(StructureInstanciation si, MethodVisitor mv) throws SemanticException;

	void visit(Type t, MethodVisitor mv) throws SemanticException;

	void visit(Variable v, MethodVisitor mv) throws SemanticException;

	void visit(VariableCreation vc, MethodVisitor mv) throws SemanticException;

	void visit(WhileLoop wl, MethodVisitor mv) throws SemanticException;

	void visit(UnaryOperation unaryOperation, MethodVisitor mv) throws SemanticException;
}
