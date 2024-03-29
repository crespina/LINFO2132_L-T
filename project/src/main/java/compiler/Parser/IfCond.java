package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class IfCond extends Statement{
    
    Operation conditionop;
    String conditionstr;
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
		this.conditionop = condition;
        this.body = body;
		this.isElse = isElse;
        this.elseBody = elseBody;
	}
	
	/**
	 * @param condition
	 * @param body
	 * @param isElse
	 * @param elseBody
	 */
	public IfCond(String condition, ArrayList<Statement> body, boolean isElse, ArrayList<Statement> elseBody) {
		super();
		this.conditionstr = condition;
        this.body = body;
		this.isElse = isElse;
        this.elseBody = elseBody;
	}

    public String toString() {
    	if (conditionop != null) {
    		return "IfCond : " + "condition = " + conditionop + ", body = " + body + ", isElse = " + isElse + ", elseBody = " + elseBody;
    	}
    	else {
    		return "IfCond : " + "condition = " + conditionstr + ", body = " + body + ", isElse = " + isElse + ", elseBody = " + elseBody;
    	}
	}

	public boolean equals (Object o) {
		IfCond ifCond = (IfCond) o;
		return Objects.equals(this.conditionop, ifCond.conditionop) && Objects.equals(this.conditionstr, ifCond.conditionstr) && Objects.equals(this.body, ifCond.body)
            && Objects.equals(this.isElse, ifCond.isElse) && Objects.equals(this.elseBody, ifCond.elseBody);
	}
}
