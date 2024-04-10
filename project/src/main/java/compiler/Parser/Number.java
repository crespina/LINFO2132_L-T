package compiler.Parser;

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
	
	public String toString() {
		return "Number : " + "value = " + value + ", type = " + type;
	}

		public boolean equals (Object o) {
		Number num = (Number) o;
		return Objects.equals(this.value, num.value) && Objects.equals(this.type, num.type);
	}

		@Override
		public ArrayList<Type> accept(Visitor visitor) {
			// TODO Auto-generated method stub
			return visitor.visit(this);
		}
}
