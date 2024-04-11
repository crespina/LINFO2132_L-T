package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Crespin
 *
 */

public class Operand extends Statement implements Visitable{
	
	//accepted types: int float bool string ArrayAccess StructureAccess identifier
	String type;
	Object value;
	
	
	/**
	 * @param type
	 * @param value
	 */
	public Operand(String type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	
	public String toString() {
		return "Operand : " + type + ", value = " + value;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operand other = (Operand) obj;
		return Objects.equals(type, other.type) && Objects.equals(value, other.value);
	}


	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
