package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Param extends Statement implements Visitable{
	
	Type type;
	String name;
	
	/**
	 * @param type 
	 * @param name 
	 */
	public Param(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	public String toString() {
		return "Param : " + "type = " + type +  ", name = " + name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Param other = (Param) obj;
		return Objects.equals(name, other.name) && Objects.equals(type, other.type);
	}

	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	
	
}
