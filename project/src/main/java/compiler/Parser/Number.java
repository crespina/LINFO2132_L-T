package compiler.Parser;

import java.util.Objects;

public class Number extends Statement{

	String value;
	Type type;

	/**
	 * @param nom 
	 * @param denom 
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
}
