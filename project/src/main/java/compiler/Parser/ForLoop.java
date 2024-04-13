package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class ForLoop extends Statement implements Visitable{

    Operation initValue;
    Operation endValue;
    Operation increment;
    ArrayList<Statement> body;

    /**
	 * @param initValue
	 * @param endValue
	 * @param increment
	 * @param body
	 */
	public ForLoop(Operation initValue, Operation endValue, Operation increment, ArrayList<Statement> body) {
		super();
		this.initValue = initValue;
		this.endValue = endValue;
		this.increment = increment;
        this.body = body;
	}

	
	
	
    public Operation getInitValue() {
		return initValue;
	}




	public void setInitValue(Operation initValue) {
		this.initValue = initValue;
	}




	public Operation getEndValue() {
		return endValue;
	}




	public void setEndValue(Operation endValue) {
		this.endValue = endValue;
	}




	public Operation getIncrement() {
		return increment;
	}




	public void setIncrement(Operation increment) {
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

	@Override
	public void accept(Visitor visitor, SymbolTable ST) {
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}


}
