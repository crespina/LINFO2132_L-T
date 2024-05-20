package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.objectweb.asm.MethodVisitor;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Type extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable {
	
	String identifier;
	
	/**
	 * @param identifier
	 */
	public Type(String identifier) {
		this.identifier = identifier;
	}

	public String toString() {
		return "Type : " + identifier;
	}

	public String getIdentifier() {
		return identifier;
	}
	
	@Override
	public boolean equals(Object o) {
		Type variable = (Type) o;
		return this.identifier.equals(variable.identifier);
	}

	public Type getType() {
		return new Type("Type");
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
