package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class StructureInstanciation extends Statement implements Visitable{
	
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
	
	
	
	public String getStructName() {
		return structName;
	}



	public void setStructName(String structName) {
		this.structName = structName;
	}



	public String getInstanceName() {
		return instanceName;
	}



	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}



	public ArrayList<Statement> getStatements() {
		return statements;
	}



	public void setStatements(ArrayList<Statement> statements) {
		this.statements = statements;
	}



	public String toString() {
		return "StructureInstanciation : " + "structName = " + structName + ", instanceName = " + instanceName + ", parameters = " + statements;
	}

	public boolean equals (Object o) {
		StructureInstanciation structure = (StructureInstanciation) o;
		return Objects.equals(this.structName, structure.structName) && Objects.equals(this.instanceName, structure.instanceName) 
			&& Objects.equals(this.instanceName, structure.instanceName);
	}


	@Override
	public void accept(Visitor visitor, SymbolTable ST) {
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}
	

}
