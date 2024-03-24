package compiler.Parser;

/**
 * @author Crespin
 *
 */

public class Operator{
	
	String operation;
	
	/**
	 * @param type
	 * @param value
	 */
	public Operator(String operation) {
		super();
		this.operation = operation;
	}
	
	public String toString() {
		return "operation : " + operation + "\n";
	}
}
