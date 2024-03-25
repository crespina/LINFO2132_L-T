package compiler.Parser;

import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Bool extends Statement {

    
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


}
