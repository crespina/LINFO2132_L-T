package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Method extends Statement implements Visitable{
	
	String identifier;
	Type returnType;
	ArrayList<Param> parameters;
	ArrayList<Statement> body;
	
	/**
	 * @param identifier
	 * @param returnType
	 * @param parameters
	 * @param body
	 */
	public Method(String identifier, Type returnType, ArrayList<Param> parameters, ArrayList<Statement> body) {
		super();
		this.identifier = identifier;
		this.returnType = returnType;
		this.parameters = parameters;
		this.body = body;
	}

	public String toString() {
		return "Method : " + "ReturnType = " + returnType + ", identifier = " + identifier + ", parameters = " + parameters + ", body = " + body;
	}

	public boolean equals (Object o) {
		Method method = (Method) o;
		return Objects.equals(this.identifier, method.identifier) && Objects.equals(this.returnType, method.returnType) 
			&& Objects.equals(this.parameters, method.parameters) && Objects.equals(this.body, method.body);
	}

	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	
}
