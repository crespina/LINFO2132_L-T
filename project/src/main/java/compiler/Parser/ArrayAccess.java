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
public class ArrayAccess extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
	//e.g a[4]

	String array;
	Statement index;
	
	
	/**
	 * @param array
	 * @param index
	 */
	public ArrayAccess(String array, Statement index) {
		super();
		this.array = array;
		this.index = index;
	}

	public String getArray() {
		return array;
	}
	
	public Statement getIndex() {
		return index;
	}
	
	public String toString() {
		return "ArrayAccess : " + " array = " + array + ", index = " + index ;
	}

	public boolean equals (Object o) {
		ArrayAccess array = (ArrayAccess) o;
		return Objects.equals(this.array, array.array) && Objects.equals(this.index, array.index);
	}

	public Type getType() {
		return new Type("ArrayAccess");
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
