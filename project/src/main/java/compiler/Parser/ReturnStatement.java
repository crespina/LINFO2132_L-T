package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class ReturnStatement extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
	Statement returnStmt;
	int id;

	/**
	 * @param returnStmt
	 */
	public ReturnStatement(Statement returnStmt, int id) {
		super();
		this.returnStmt = returnStmt;
		this.id = id;
	}	

	public String toString() {
		return "ReturnStatement : " + "returnStatement = " + returnStmt;
	}

	public boolean equals (Object o) {
		ReturnStatement returnStat = (ReturnStatement) o;
		return Objects.equals(this.returnStmt, returnStat.returnStmt);
	}

	public Statement getReturnStatement() {
		return returnStmt;
	}

	public Type getType() {
		return new Type("ReturnStatement");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void accept(TableVisitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}

	@Override
	public Type acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		return visitor.TypeCheck(this, ST);
	}

	@Override
	public void accept(GenVisitor visitor, MethodVisitor mv) throws SemanticException {
		visitor.visit(this, mv);
	}
}
