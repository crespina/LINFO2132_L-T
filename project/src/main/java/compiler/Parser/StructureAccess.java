package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class StructureAccess extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
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

	
	public String getInstance() {
		return instance;
	}


	public void setInstance(String instance) {
		this.instance = instance;
	}


	public String getParam() {
		return param;
	}


	public void setParam(String param) {
		this.param = param;
	}


	public ArrayAccess getInstanceInArray() {
		return instanceInArray;
	}


	public void setInstanceInArray(ArrayAccess instanceInArray) {
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
	public Type acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		return visitor.TypeCheck(this, ST);
	}


	@Override
	public void accept(GenVisitor visitor, MethodVisitor mv) throws SemanticException {
		visitor.visit(this, mv);
	}
	

}
