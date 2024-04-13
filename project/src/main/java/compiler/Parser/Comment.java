package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Crespin
 *
 */
public class Comment extends Statement implements Visitable{
	
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

	@Override
	public void accept(Visitor visitor, SymbolTable ST) {
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}

}
