package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

public class UnaryOperation extends Statement implements TableVisitable, TypeCheckVisitable {

	Operator op;
	Operand operand;
	
	public UnaryOperation(Operator op, Operand operand) {
		this.op = op;
		this.operand = operand;
	}
	
	
	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}

	public Operand getOperand() {
		return operand;
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
	}

	

	@Override
	public String toString() {
		return "UnaryOperation [" + (op != null ? "op=" + op + ", " : "")
				+ (operand != null ? "operand=" + operand : "") + "]";
	}


	@Override
	public Type acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		return visitor.TypeCheck(this, ST);
	}


	@Override
	public void accept(TableVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		visitor.visit(this,ST);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnaryOperation other = (UnaryOperation) obj;
		return Objects.equals(op, other.op) && Objects.equals(operand, other.operand);
	}

	

}

