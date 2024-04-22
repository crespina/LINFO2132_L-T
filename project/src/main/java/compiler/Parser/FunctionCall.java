package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class FunctionCall extends Statement implements TableVisitable, TypeCheckVisitable{
	
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

	public String getFunctionName() {
		return functionName;
	}
	
	public ArrayList<Statement> getParams(){
		return params;
	}
	
	public String toString() {
		return "FunctionCall : " + " functionName = " + functionName + ", parameters = " + params;
	}

	public boolean equals (Object o) {
		FunctionCall function = (FunctionCall) o;
		return Objects.equals(this.functionName, function.functionName) && Objects.equals(this.params, function.params);
	}

	public Type getType() {
		return new Type("FunctionCall");
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

}
