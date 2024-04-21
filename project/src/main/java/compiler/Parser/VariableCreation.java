package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 * 
 */
public class VariableCreation extends Statement implements Visitable{
	
	Boolean isFinal;
	Type type; 
	String identifier;
	Statement statement;
	
	
	/**
	 * @param isFinal 
	 * @param type
	 * @param identifier
	 * @param statement
	 */
	public VariableCreation(Boolean isFinal, Type type, String identifier, Statement statement) {
		super();
		this.isFinal = isFinal;
		this.type = type;
		this.identifier = identifier;
		this.statement = statement;
	}
	
	
	
	public Boolean getIsFinal() {
		return isFinal;
	}



	public void setIsFinal(Boolean isFinal) {
		this.isFinal = isFinal;
	}



	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public String getIdentifier() {
		return identifier;
	}



	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}



	public Statement getStatement() {
		return statement;
	}



	public void setStatement(Statement statement) {
		this.statement = statement;
	}



	public String toString() {
		return "VarCreation : " + "isFinal = " + isFinal + " " + type + ", identifier = " + identifier + ", statement = " + statement;
	}

	public boolean equals (Object o) {
		VariableCreation variable = (VariableCreation) o;
		return Objects.equals(this.isFinal, variable.isFinal) && Objects.equals(this.identifier, variable.identifier) 
			&& this.type.equals(variable.type) && this.statement.equals(variable.statement);
	}

	@Override
	public void accept(Visitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}

}
