package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Variable extends Statement implements TableVisitable{
	
	String varName;
	Statement value;
	
	//e.g. a = 3
	
	/**
	 * @param varName
	 * @param value
	 */
	public Variable(String varName, Statement value) {
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



	public void setValue(Statement value) {
		this.value = value;
	}



	public String toString() {
		return "Variable : " + ", varName = " + varName + ", type = " + value;
	}

	public boolean equals (Object o) {
		Variable variable = (Variable) o;
		return Objects.equals(this.varName, variable.varName) && Objects.equals(this.value, variable.value);
	}

	public Type getType() {
		return new Type("Variable");
	}

	@Override
	public void accept(TableVisitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}

}
