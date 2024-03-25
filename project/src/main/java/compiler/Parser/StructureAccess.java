package compiler.Parser;

import java.util.Objects;

public class StructureAccess extends Statement{
	
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

	public String toString() {
		return "StructureAccess : " + "instance = " + instance + "param = " + param + ", instanceInArray = " + instanceInArray;
	}

	public boolean equals (Object o) {
		StructureAccess structure = (StructureAccess) o;
		return Objects.equals(this.instance, structure.instance) && Objects.equals(this.param, structure.param) 
			&& Objects.equals(this.instanceInArray, structure.instanceInArray);
	}
	

}
