package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */

public class Operator extends Statement implements TableVisitable, TypeCheckVisitable{
	
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
	public void acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		visitor.TypeCheck(this, ST);
	}

	
}
