package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Structure extends Statement{
	
	String name;
	ArrayList<Param> parameters;
	
	/**
	 * @param name
	 * @param parameters
	 */
	public Structure(String name, ArrayList<Param> parameters) {
		super();
		this.name = name;
		this.parameters = parameters;
	}
	
	public String toString() {
		return "Structure : " + "name = " + name + "parameters = " + parameters + "\n";
	}

	public boolean equals (Object o) {
		Structure structure = (Structure) o;
		return Objects.equals(this.name, structure.name) && Objects.equals(this.parameters, structure.parameters);
	}

}
