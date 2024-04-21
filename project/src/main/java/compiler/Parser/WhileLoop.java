package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class WhileLoop extends Statement implements TableVisitable, TypeCheckVisitable{
    
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

	public Type getType() {
		return new Type("WhileLoop");
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
}
