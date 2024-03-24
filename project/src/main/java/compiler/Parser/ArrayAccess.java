package compiler.Parser;

public class ArrayAccess {

	String array;
	Operation index;
	
	
	/**
	 * @param array
	 * @param index
	 */
	public ArrayAccess(String array, Operation index) {
		super();
		this.array = array;
		this.index = index;
	}
	
	public String toString() {
		return "ArrayAccess : " + "array = " + array + ", index = " + index + "\n";
	}
}