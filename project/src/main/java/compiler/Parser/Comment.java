package compiler.Parser;

import java.util.Objects;

/**
 * @author Crespin
 *
 */
public class Comment extends Statement{
	
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

}
