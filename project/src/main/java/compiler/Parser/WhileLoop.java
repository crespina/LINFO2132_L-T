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

    public String toString() {
		return "WhileLoop : " + "condition = " + condition + ", body = " + body;
	}

	public boolean equals (Object o) {
		WhileLoop whileLoop = (WhileLoop) o;
		return Objects.equals(this.condition, whileLoop.condition) && Objects.equals(this.body, whileLoop.body);
	}

	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
}
