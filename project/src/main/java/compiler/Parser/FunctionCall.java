package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class FunctionCall extends Statement{
	
	String functionName;
	ArrayList<Statement> params;
	
	
	/**
	 * @param functionName
	 * @param params
	 */
	public FunctionCall(String functionName, ArrayList<Statement> params) {
		super();
		this.functionName = functionName;
		this.params = params;
	}
	
	public String toString() {
		return "FunctionCall : " + " functionName = " + functionName + ", parameters = " + params;
	}

	public boolean equals (Object o) {
		FunctionCall function = (FunctionCall) o;
		return Objects.equals(this.functionName, function.functionName) && Objects.equals(this.params, function.params);
	}

}
