package compiler.Parser;

import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Variable extends Statement{
	
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

}
