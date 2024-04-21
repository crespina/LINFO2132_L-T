package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

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
	Type valueType = null;
	
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

	
	

	public Operand getOperand1() {
		return operand1;
	}

	public void setOperand1(Operand operand1) {
		this.operand1 = operand1;
	}

	public Operator getOperation() {
		return operation;
	}

	public void setOperation(Operator operation) {
		this.operation = operation;
	}

	public Operand getOperand2() {
		return operand2;
	}

	public void setOperand2(Operand operand2) {
		this.operand2 = operand2;
	}

	public Operation getOp1() {
		return op1;
	}

	public void setOp1(Operation op1) {
		this.op1 = op1;
	}

	public Operation getOp2() {
		return op2;
	}

	public void setOp2(Operation op2) {
		this.op2 = op2;
	}

	@Override
	public String toString() {
		return "Operation [" + (operand1 != null ? "operand1=" + operand1 + ", " : "")
				+ (operation != null ? " operation= " + operation + ", " : "")
				+ (operand2 != null ? " operand2= " + operand2 + ", " : "") + (op2 != null ? "op1=" + op1 + ", " : "")
				+ (op1 != null ? "op2=" + op2 : "") + "]";
	}

	public boolean equals (Object o) {
		Operation oper = (Operation) o;
		return Objects.equals(this.operand1, oper.operand1) && Objects.equals(this.operand2, oper.operand2) 
			&& Objects.equals(this.operation, oper.operation) && Objects.equals(this.op1, oper.op1) && Objects.equals(this.op2, oper.op2);
	}

	public void setVType(Type t) {
		this.valueType = t;
	}

	public Type getVType() {
		return valueType;
	}

	public Type getType() {
		return new Type("Operation");
	}

	@Override
	public void accept(Visitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}

}
