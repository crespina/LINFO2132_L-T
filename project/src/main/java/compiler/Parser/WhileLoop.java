package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class WhileLoop extends Statement implements Visitable{
    
    Operation condition;
    ArrayList<Statement> body;

    /**
	 * @param condition
	 * @param body
	 */
	public WhileLoop(Operation condition, ArrayList<Statement> body) {
		super();
		this.condition = condition;
        this.body = body;
	}
	
	

    public Operation getCondition() {
		return condition;
	}



	public void setCondition(Operation condition) {
		this.condition = condition;
	}



	public ArrayList<Statement> getBody() {
		return body;
	}



	public void setBody(ArrayList<Statement> body) {
		this.body = body;
	}



	public String toString() {
		return "WhileLoop : " + "condition = " + condition + ", body = " + body;
	}

	public boolean equals (Object o) {
		WhileLoop whileLoop = (WhileLoop) o;
		return Objects.equals(this.condition, whileLoop.condition) && Objects.equals(this.body, whileLoop.body);
	}

	@Override
	public void accept(Visitor visitor, SymbolTable ST) {
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}
}
