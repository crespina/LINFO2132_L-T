package compiler.Parser;

import java.util.Objects;

public class ReturnStatement extends Statement{
	
	Statement returnStmt;

	public ReturnStatement(Statement returnStmt) {
		super();
		this.returnStmt = returnStmt;
	}	

	public String toString() {
		return "ReturnStatement : " + "returnStmt = " + returnStmt + "\n";
	}

	public boolean equals (Object o) {
		ReturnStatement returnStat = (ReturnStatement) o;
		return Objects.equals(this.returnStmt, returnStat.returnStmt);
	}
}
