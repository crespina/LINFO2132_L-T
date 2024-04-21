package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class ArrayAccess extends Statement implements Visitable{
	
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
	public void accept(Visitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}
}
