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
public class Number extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{

	String value;
	Type type;

	/**
	 * @param value 
	 * @param type 
	 */
	public Number(String value, Type type) {
		super();
		this.value = value;
		this.type = type;
	}
	
	
	
	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public String toString() {
		return "Number : " + "value = " + value + ", type = " + type;
	}

	public boolean equals (Object o) {
		Number num = (Number) o;
		return Objects.equals(this.value, num.value) && Objects.equals(this.type, num.type);
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
