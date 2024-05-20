package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class ArrayInit extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
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

	@Override
	public Type acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		return visitor.TypeCheck(this, ST);
	}

	@Override
	public void accept(GenVisitor visitor, MethodVisitor mv) throws SemanticException {
		visitor.visit(this, mv);
	}
}
