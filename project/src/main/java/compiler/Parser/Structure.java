package compiler.Parser;

import java.util.ArrayList;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Structure {
	
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
	
	

}
