package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class ArrayAccess extends Statement implements Visitable{

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
	
	public String toString() {
		return "ArrayAccess : " + " array = " + array + ", index = " + index ;
	}

	public boolean equals (Object o) {
		ArrayAccess array = (ArrayAccess) o;
		return Objects.equals(this.array, array.array) && Objects.equals(this.index, array.index);
	}

	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		return visitor.visit(this);	
		//array.accept(visitor);
		//index.accept(visitor);
	}
}
