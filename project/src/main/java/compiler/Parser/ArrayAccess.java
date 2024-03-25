package compiler.Parser;

import java.util.Objects;

public class ArrayAccess extends Statement{

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
		return "ArrayAccess : " + "array = " + array + ", index = " + index ;
	}

	public boolean equals (Object o) {
		ArrayAccess array = (ArrayAccess) o;
		return Objects.equals(this.array, array.array) && Objects.equals(this.index, array.index);
	}
}
