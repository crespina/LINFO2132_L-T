package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

import javax.swing.plaf.nimbus.State;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class IfCond extends Statement{
    
    Operation condition;
    ArrayList<Statement> body;
	Boolean isElse;
    ArrayList<Statement> elseBody;

    /**
	 * @param condition
	 * @param body
	 * @param elseBody
	 */
	public IfCond(Operation condition, ArrayList<Statement> body, boolean isElse, ArrayList<Statement> elseBody) {
		super();
		this.condition = condition;
        this.body = body;
		this.isElse = isElse;
        this.elseBody = elseBody;
	}

    public String toString() {
		return "IfCond : " + "condition = " + condition + ", body = " + body + ", isElse = " + isElse + ", elseBody = " + elseBody + "\n";
	}

	public boolean equals (Object o) {
		IfCond ifCond = (IfCond) o;
		return Objects.equals(this.condition, ifCond.condition) && Objects.equals(this.body, ifCond.body)
            && Objects.equals(this.isElse, ifCond.isElse) && Objects.equals(this.elseBody, ifCond.elseBody);
	}
}
