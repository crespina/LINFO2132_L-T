package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class ArrayInit extends Statement implements TableVisitable{
	
	// e.g. : int[] c = int[5];
	//the arrayInit is the left part
    
    String keyword;
    ArrayList<Statement> values;

	/**
	 * @param keyword 
	 * @param values
	 */
    public ArrayInit(String keyword, ArrayList<Statement> values) {
		super();
        this.keyword = keyword;
        this.values = values;
	}
    
    public String getKeyword() {
		return keyword;
	}

    public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

    public ArrayList<Statement> getValues() {
		return values;
	}

    public void setValues(ArrayList<Statement> values) {
		this.values = values;
	}

    public String toString() {
		return "ArrayInit : " + "keyword = " + keyword + ", values = " + values;
	}

	public boolean equals (Object o) {
		ArrayInit arr = (ArrayInit) o;
		return Objects.equals(this.keyword, arr.keyword) && Objects.equals(this.values, arr.values);
	}

	public Type getType() {
		return new Type("ArrayInit");
	}

	@Override
	public void accept(TableVisitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}
}
