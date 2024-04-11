package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Type extends Statement implements Visitable {
	
	String identifier;
	
	/**
	 * @param identifier
	 */
	public Type(String identifier) {
		this.identifier = identifier;
	}

	public String toString() {
		return "Type : " + identifier;
	}
	
	@Override
	public boolean equals(Object o) {
		Type variable = (Type) o;
		return this.identifier.equals(variable.identifier);
	}

	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
}
