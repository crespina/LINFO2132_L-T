package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class StructureAccess extends Statement implements TableVisitable, TypeCheckVisitable{
	
	String instance;
	String param;
	ArrayAccess instanceInArray; //if it's in an array, i.e. e.g. a[3].x with a = Array(struct)
	
	/**
	 * @param instance
	 * @param param
	 * @param instanceInArray 
	 */
	public StructureAccess(String instance, String param, ArrayAccess instanceInArray) {
		super();
		this.instance = instance;
		this.param = param;
		this.instanceInArray = instanceInArray;
	}

	

	@Override
	public String toString() {
		return "StructureAccess [" + (instance != null ? "instance=" + instance + ", " : "")
				+ (param != null ? "param=" + param + ", " : "")
				+ (instanceInArray != null ? "instanceInArray=" + instanceInArray : "") + "]";
	}



	public boolean equals (Object o) {
		StructureAccess structure = (StructureAccess) o;
		return Objects.equals(this.instance, structure.instance) && Objects.equals(this.param, structure.param) 
			&& Objects.equals(this.instanceInArray, structure.instanceInArray);
	}


	public Type getType() {
		return new Type("StructureAccess");
	}

	@Override
	public void accept(TableVisitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}



	@Override
	public void acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		visitor.TypeCheck(this, ST);
	}
	

}
