package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Crespin
 *
 */
public class Comment extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{
	
	String comment;

	/**
	 * @param comment
	 */
	public Comment(String comment) {
		super();
		this.comment = comment;
	}
	
	public String toString() {
		return "Comment : "  + comment ;
	}

	public boolean equals (Object o) {
		Comment com = (Comment) o;
		return Objects.equals(this.comment, com.comment);
	}

	public Type getType() {
		return new Type("Comment");
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
