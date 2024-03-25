package compiler.Parser;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */

public class Operator{
	
	String operation;
	
	/**
	 * @param operation 
	 */
	public Operator(String operation) {
		super();
		this.operation = operation;
	}
	
	public String toString() {
		return "operation : " + operation;
	}
}
