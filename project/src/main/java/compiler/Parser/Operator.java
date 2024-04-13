package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */

public class Operator extends Statement implements Visitable{
	
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

	@Override
	public void accept(Visitor visitor, SymbolTable ST) {
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}

	
}
