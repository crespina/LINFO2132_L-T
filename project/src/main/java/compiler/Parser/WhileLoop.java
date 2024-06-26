package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class WhileLoop extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
    
    Operation condition;
    String conditionstr;
    ArrayList<Statement> body;

    /**
	 * @param condition
	 * @param body
	 */
	public WhileLoop(Operation condition, String conditionstr, ArrayList<Statement> body) {
		super();
		this.condition = condition;
		this.conditionstr = conditionstr;
        this.body = body;
	}
	
	public String getConditionStr() {
		return conditionstr;
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

	@Override
	public void accept(GenVisitor visitor, MethodVisitor mv) throws SemanticException {
		visitor.visit(this, mv);
	}
}
