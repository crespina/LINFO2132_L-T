package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Structure extends Statement implements Visitable{
	
	String name;
	ArrayList<Statement> body;
	
	/**
	 * @param name
	 * @param body 
	 */
	public Structure(String name, ArrayList<Statement> body) {
		super();
		this.name = name;
		this.body = body;
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public ArrayList<Statement> getBody() {
		return body;
	}



	public void setBody(ArrayList<Statement> body) {
		this.body = body;
	}



	public String toString() {
		return "Structure : " + "name = " + name + ", body = " + body;
	}

	public boolean equals (Object o) {
		Structure structure = (Structure) o;
		return Objects.equals(this.name, structure.name) && Objects.equals(this.body, structure.body);
	}

	public Type getType() {
		return new Type("Structure");
	}

	@Override
	public void accept(Visitor visitor, SymbolTable ST, HashMap <String, ArrayList<Param>> funcT) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST, funcT);
	}

}
