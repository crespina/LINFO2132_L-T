package compiler.Parser;

/**
 * @author Crespin
 *
 */

public class Operand{
	
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


	/**
	 * @param type
	 * @param value
	 */
	public Operand(String type, Float value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	

}
