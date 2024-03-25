package compiler.Parser;


/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Param {
	
	Type type;
	String name;
	
	/**
	 * @param type 
	 * @param name 
	 */
	public Param(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	public String toString() {
		return "Param : " + "type = " + type +  ", name = " + name;
	}
}
