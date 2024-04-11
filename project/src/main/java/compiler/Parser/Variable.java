package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Variable extends Statement implements Visitable{
	
	String varName;
	Object value;
	
	
	/**
	 * @param varName
	 * @param value
	 */
	public Variable(String varName, Object value) {
		super();
		this.varName = varName;
		this.value = value;
	}
	
	public String toString() {
		return "Variable : " + ", varName = " + varName + ", type = " + value;
	}

	public boolean equals (Object o) {
		Variable variable = (Variable) o;
		return Objects.equals(this.varName, variable.varName) && Objects.equals(this.value, variable.value);
	}

	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
