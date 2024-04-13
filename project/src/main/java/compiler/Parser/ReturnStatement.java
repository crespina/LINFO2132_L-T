package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

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

	@Override
	public void accept(Visitor visitor, SymbolTable ST) {
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}
}
