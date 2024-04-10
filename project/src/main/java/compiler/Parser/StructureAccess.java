package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class StructureAccess extends Statement implements Visitable{
	
	String instance;
	String param;
	ArrayAccess instanceInArray;
	
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



	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	

}
