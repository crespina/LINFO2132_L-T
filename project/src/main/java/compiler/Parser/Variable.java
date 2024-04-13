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
	
	
	
	public String getVarName() {
		return varName;
	}



	public void setVarName(String varName) {
		this.varName = varName;
	}



	public Object getValue() {
		return value;
	}



	public void setValue(Object value) {
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
	public void accept(Visitor visitor, SymbolTable ST) {
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}

}
