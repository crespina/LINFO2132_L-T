package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Crespin
 *
 */
public class Number extends Statement implements Visitable{

	String value;
	Type type;

	/**
	 * @param value 
	 * @param type 
	 */
	public Number(String value, Type type) {
		super();
		this.value = value;
		this.type = type;
	}
	
	
	
	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public String toString() {
		return "Number : " + "value = " + value + ", type = " + type;
	}

		public boolean equals (Object o) {
		Number num = (Number) o;
		return Objects.equals(this.value, num.value) && Objects.equals(this.type, num.type);
	}

		@Override
		public void accept(Visitor visitor, SymbolTable ST) {
			// TODO Auto-generated method stub
			visitor.visit(this, ST);
		}
}
