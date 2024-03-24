package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class WhileLoop extends Statement{
    
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
		return "WhileLoop : " + "condition = " + condition + ", body = " + body + "\n";
	}

	public boolean equals (Object o) {
		WhileLoop whileLoop = (WhileLoop) o;
		return Objects.equals(this.condition, whileLoop.condition) && Objects.equals(this.body, whileLoop.body);
	}
}
