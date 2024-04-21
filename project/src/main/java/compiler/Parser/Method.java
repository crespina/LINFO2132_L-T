package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Method extends Statement implements TableVisitable, TypeCheckVisitable{
	
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
	
	

	public String getIdentifier() {
		return identifier;
	}



	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}



	public Type getReturnType() {
		return returnType;
	}



	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}



	public ArrayList<Param> getParameters() {
		return parameters;
	}



	public void setParameters(ArrayList<Param> parameters) {
		this.parameters = parameters;
	}



	public ArrayList<Statement> getBody() {
		return body;
	}



	public void setBody(ArrayList<Statement> body) {
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

	public Type getType() {
		return new Type("Method");
	}

	@Override
	public void accept(TableVisitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}



	@Override
	public Type acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		return visitor.TypeCheck(this, ST);
	}
	
}
