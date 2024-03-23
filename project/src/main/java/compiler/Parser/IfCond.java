package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class IfCond {
    
    Statement condition;
    ArrayList<Statement> body;
    ArrayList<Statement> elseBody;

    /**
	 * @param condition
	 * @param body
	 * @param elseBody
	 */
	public IfCond(Statement condition, ArrayList<Statement> body, ArrayList<Statement> elseBody) {
		super();
		this.condition = condition;
        this.body = body;
        this.elseBody = elseBody;
	}

    public String toString() {
		return "IfCond : " + "condition = " + condition + ", body = " + body + ", elseBody = " + elseBody + "\n";
	}

	public boolean equals (Object o) {
		IfCond ifCond = (IfCond) o;
		return Objects.equals(this.condition, ifCond.condition) && Objects.equals(this.body, ifCond.body)
            && Objects.equals(this.elseBody, ifCond.elseBody);
	}
}
