package compiler.Parser;

import java.util.ArrayList;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class StructureInstanciation {
	
	String structName;
	String instanceName;
	ArrayList<Statement> statements;
	
	
	/**
	 * @param structName
	 * @param instanceName
	 * @param statements
	 */
	public StructureInstanciation(String structName, String instanceName, ArrayList<Statement> statements) {
		super();
		this.structName = structName;
		this.instanceName = instanceName;
		this.statements = statements;
	}
	
	
	
	

}
