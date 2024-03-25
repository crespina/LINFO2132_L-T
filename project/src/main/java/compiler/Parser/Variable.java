package compiler.Parser;

import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 * 
 */
public class Variable extends Statement{
	
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
	public Variable(Boolean isFinal, Type type, String identifier, Statement statement) {
		super();
		this.isFinal = isFinal;
		this.type = type;
		this.identifier = identifier;
		this.statement = statement;
	}
	
	public String toString() {
		return "VarCreation : " + "isFinal = " + isFinal + " " + type + ", identifier = " + identifier + ", statement = " + statement;
	}

	public boolean equals (Object o) {
		Variable variable = (Variable) o;
		return Objects.equals(this.isFinal, variable.isFinal) && Objects.equals(this.identifier, variable.identifier) 
			&& this.type.equals(variable.type) && this.statement.equals(variable.statement);
	}

}
