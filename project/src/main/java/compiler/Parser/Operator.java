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

public class Operator extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
	String operation;
	
	/**
	 * @param operation 
	 */
	public Operator(String operation) {
		super();
		this.operation = operation;
	}
	
	
	
	public String getOperation() {
		return operation;
	}



	public void setOperation(String operation) {
		this.operation = operation;
	}



	public String toString() {
		return "operation : " + operation;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operator other = (Operator) obj;
		return Objects.equals(operation, other.operation);
	}

	public Type getType() {
		return new Type("Operator");
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
