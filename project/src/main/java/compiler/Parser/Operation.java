package compiler.Parser;

/**
 * @author Crespin
 *
 */
public class Operation {

	
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
	
	
	public Operation(Operation op1, Operator operation, Operation op2) {
		super();
		this.op1 = op1;
		this.operation = operation;
		this.op2 = op2;
	}

}
