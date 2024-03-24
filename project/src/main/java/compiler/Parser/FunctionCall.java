package compiler.Parser;

import java.util.ArrayList;

public class FunctionCall {
	
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
	
	

}
