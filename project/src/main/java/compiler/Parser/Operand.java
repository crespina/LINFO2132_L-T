package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Crespin
 *
 */

public class Operand extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
	//accepted types: int float bool string ArrayAccess StructureAccess identifier
	String type;
	Object value;
	
	
	/**
	 * @param type
	 * @param value
	 */
	public Operand(String type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	
	
	public Type getType() {
		return new Type(type);
	}



	public void setType(String type) {
		this.type = type;
	}



	public Object getValue() {
		return value;
	}



	public void setValue(Object value) {
		this.value = value;
	}



	public String toString() {
		return "Operand : " + type + ", value = " + value;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operand other = (Operand) obj;
		return Objects.equals(type, other.type) && Objects.equals(value, other.value);
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
