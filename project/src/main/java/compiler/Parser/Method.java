package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Method extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
	String identifier;
	Type returnType;
	ArrayList<Param> parameters;
	ArrayList<Statement> body;
	ArrayList<Integer> returnStatements;
	
	/**
	 * @param identifier
	 * @param returnType
	 * @param parameters
	 * @param body
	 */
	public Method(String identifier, Type returnType, ArrayList<Param> parameters, ArrayList<Statement> body, ArrayList<Integer> returnStatements) {
		super();
		this.identifier = identifier;
		this.returnType = returnType;
		this.parameters = parameters;
		this.body = body;
		this.returnStatements = returnStatements;
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

	public ArrayList<Integer> getReturnStatements() {
		return returnStatements;
	}

	public void setReturnStatements(ArrayList<Integer> returnStatements) {
		this.returnStatements = returnStatements;
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



	@Override
	public void accept(GenVisitor visitor, MethodVisitor mv) throws SemanticException {
		visitor.visit(this, mv);
	}
	
}
