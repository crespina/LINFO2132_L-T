package compiler.Parser;


/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Type {
	
	String identifier;
	
	/**
	 * @param identifier
	 */
	public Type(String identifier) {
		this.identifier = identifier;
	}

	public String toString() {
		return "Type : " + identifier;
	}
	
	@Override
	public boolean equals(Object o) {
		Type variable = (Type) o;
		return this.identifier.equals(variable.identifier);
	}
}
