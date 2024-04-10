package compiler.Parser;

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
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
