package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class IfCond extends Statement implements Visitable{
	
	// e.g. if (op) {} else {}
	//      if (op) {}
    
    Operation conditionop;
    String conditionstr; //bool
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

	
	
    public Operation getConditionop() {
		return conditionop;
	}

	public void setConditionop(Operation conditionop) {
		this.conditionop = conditionop;
	}

	public String getConditionstr() {
		return conditionstr;
	}

	public void setConditionstr(String conditionstr) {
		this.conditionstr = conditionstr;
	}

	public ArrayList<Statement> getBody() {
		return body;
	}

	public void setBody(ArrayList<Statement> body) {
		this.body = body;
	}

	public Boolean getIsElse() {
		return isElse;
	}

	public void setIsElse(Boolean isElse) {
		this.isElse = isElse;
	}

	public ArrayList<Statement> getElseBody() {
		return elseBody;
	}

	public void setElseBody(ArrayList<Statement> elseBody) {
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

	public Type getType() {
		return new Type("IfCond");
	}

	@Override
	public void accept(Visitor visitor, SymbolTable ST, HashMap <String, ArrayList<Param>> funcT) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST, funcT);
	}
}
