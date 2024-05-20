package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Structure extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
	String name;
	ArrayList<Param> body;
	
	/**
	 * @param name
	 * @param body 
	 */
	public Structure(String name, ArrayList<Param> body) {
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



	public ArrayList<Param> getBody() {
		return body;
	}



	public void setBody(ArrayList<Param> body) {
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
