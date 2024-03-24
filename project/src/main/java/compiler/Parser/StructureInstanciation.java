package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class StructureInstanciation extends Statement{
	
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
	
	
	public String toString() {
		return "StructureInstanciation : " + "structName = " + structName + "instanceName = " + instanceName + ", statements = " + statements + "\n";
	}

	public boolean equals (Object o) {
		StructureInstanciation structure = (StructureInstanciation) o;
		return Objects.equals(this.structName, structure.structName) && Objects.equals(this.instanceName, structure.instanceName) 
			&& Objects.equals(this.instanceName, structure.instanceName);
	}
	

}
