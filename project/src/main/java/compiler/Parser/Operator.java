package compiler.Parser;

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
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	
}
