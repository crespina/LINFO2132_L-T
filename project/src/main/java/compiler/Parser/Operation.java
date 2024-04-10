package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Crespin
 *
 */
public class Operation extends Statement implements Visitable{

	
	Operand operand1;
	Operator operation;
	Operand operand2; 
	Operation op1;
	Operation op2;
	
	/**
	 * @param operand1
	 * @param operation
	 * @param operand2
	 */
	public Operation(Operand operand1, Operator operation, Operand operand2) {
		super();
		this.operand1 = operand1;
		this.operation = operation;
		this.operand2 = operand2;
	}
	
	/**
	 * @param op1
	 * @param operation
	 * @param operand2
	 */
	public Operation(Operation op1, Operator operation, Operand operand2) {
		super();
		this.op1 = op1;
		this.operation = operation;
		this.operand2 = operand2;
	}
	
	
	/**
	 * @param operand1
	 * @param operation
	 * @param op2
	 */
	public Operation(Operand operand1, Operator operation, Operation op2) {
		super();
		this.operand1 = operand1;
		this.operation = operation;
		this.op2 = op2;
	}
	
	
	/**
	 * @param op1
	 * @param operation
	 * @param op2
	 */
	public Operation(Operation op1, Operator operation, Operation op2) {
		super();
		this.op1 = op1;
		this.operation = operation;
		this.op2 = op2;
	}


	@Override
	public String toString() {
		return "Operation [" + (operand2 != null ? "operand2=" + operand2 + ", " : "")
				+ (operation != null ? "operation=" + operation + ", " : "")
				+ (operand1 != null ? "operand1=" + operand1 + ", " : "") + (op2 != null ? "op1=" + op2 + ", " : "")
				+ (op1 != null ? "op2=" + op1 : "") + "]";
	}

	public boolean equals (Object o) {
		Operation oper = (Operation) o;
		return Objects.equals(this.operand1, oper.operand1) && Objects.equals(this.operand2, oper.operand2) 
			&& Objects.equals(this.operation, oper.operation) && Objects.equals(this.op1, oper.op1) && Objects.equals(this.op2, oper.op2);
	}

	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
