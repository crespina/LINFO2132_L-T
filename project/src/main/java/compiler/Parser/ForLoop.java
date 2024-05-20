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
public class ForLoop extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{

    Variable initValue;
    Operation endValue;
    Variable increment;
    ArrayList<Statement> body;

    /**
	 * @param initValue
	 * @param endValue
	 * @param increment
	 * @param body
	 */
	public ForLoop(Variable initValue, Operation endValue, Variable increment, ArrayList<Statement> body) {
		super();
		this.initValue = initValue;
		this.endValue = endValue;
		this.increment = increment;
        this.body = body;
	}

	
	
	
    public Variable getInitValue() {
		return initValue;
	}




	public void setInitValue(Variable initValue) {
		this.initValue = initValue;
	}




	public Operation getEndValue() {
		return endValue;
	}




	public void setEndValue(Operation endValue) {
		this.endValue = endValue;
	}




	public Variable getIncrement() {
		return increment;
	}




	public void setIncrement(Variable increment) {
		this.increment = increment;
	}




	public ArrayList<Statement> getBody() {
		return body;
	}




	public void setBody(ArrayList<Statement> body) {
		this.body = body;
	}




	public String toString() {
		return "ForLoop : " + "initValue = " + initValue + ", endValue = " + endValue + ", increment = " + increment + ", body = " + body;
	}

	public boolean equals (Object o) {
		ForLoop forLoop = (ForLoop) o;
		return Objects.equals(this.initValue, forLoop.initValue) && Objects.equals(this.endValue, forLoop.endValue) 
			&& Objects.equals(this.increment, forLoop.increment) && Objects.equals(this.body, forLoop.body);
	}

	public Type getType() {
		return new Type("ForLoop");
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
