package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class ReturnStatement extends Statement implements Visitable{
	
	Statement returnStmt;

	/**
	 * @param returnStmt
	 */
	public ReturnStatement(Statement returnStmt) {
		super();
		this.returnStmt = returnStmt;
	}	

	public String toString() {
		return "ReturnStatement : " + "returnStatement = " + returnStmt;
	}

	public boolean equals (Object o) {
		ReturnStatement returnStat = (ReturnStatement) o;
		return Objects.equals(this.returnStmt, returnStat.returnStmt);
	}

	public Statement getReturnStatement() {
		return returnStmt;
	}

	public Type getType() {
		return new Type("ReturnStatement");
	}

	@Override
	public void accept(Visitor visitor, SymbolTable ST, HashMap <String, ArrayList<Param>> funcT) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST, funcT);
	}
}
