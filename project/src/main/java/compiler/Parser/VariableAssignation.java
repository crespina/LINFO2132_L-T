package compiler.Parser;

import java.util.Objects;

public class VariableAssignation extends Statement{
	
	String varName;
	Object value;
	
	
	/**
	 * @param varName
	 * @param value
	 */
	public VariableAssignation(String varName, Object value) {
		super();
		this.varName = varName;
		this.value = value;
	}
	
	public String toString() {
		return "VarAssignation : " + "varName = " + varName + "type = " + value + "\n";
	}

	public boolean equals (Object o) {
		VariableAssignation variable = (VariableAssignation) o;
		return Objects.equals(this.varName, variable.varName) && Objects.equals(this.value, variable.value);
	}

}
