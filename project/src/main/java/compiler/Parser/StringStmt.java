package compiler.Parser;

import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class StringStmt extends Statement{

    String content;


    /**
	 * @param content
	 */
    public StringStmt(String content) {
        this.content = content;
    }


    public String toString() {
        return "String : " + content;
    }


    public boolean equals(Object o) {
		StringStmt s = (StringStmt) o;
		return Objects.equals(this.content, s.content);
    }


}
