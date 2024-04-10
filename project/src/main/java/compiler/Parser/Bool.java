package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Bool extends Statement implements Visitable{

    
    String content;

    /**
	 * @param content
	 */
    public Bool(String content) {
        this.content = content;
    }


    public String toString() {
        return "boolean : " + content;
    }


    public boolean equals(Object o) {
		Bool b = (Bool) o;
		return Objects.equals(this.content, b.content);
    }


	@Override
	public ArrayList<Type> accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}


}
