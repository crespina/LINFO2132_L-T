package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Structure extends Statement{
	
	String name;
	ArrayList<Statement> body;
	
	/**
	 * @param name
	 * @param parameters
	 */
	public Structure(String name, ArrayList<Statement> body) {
		super();
		this.name = name;
		this.body = body;
	}
	
	public String toString() {
		return "Structure : " + "name = " + name + ", body = " + body + "\n";
	}

	public boolean equals (Object o) {
		Structure structure = (Structure) o;
		return Objects.equals(this.name, structure.name) && Objects.equals(this.body, structure.body);
	}

}
