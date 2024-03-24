package compiler.Parser;

public class StructureAccess {
	
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

}
