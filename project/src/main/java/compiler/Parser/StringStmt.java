package compiler.Parser;

import compiler.Semantic.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class StringStmt extends Statement implements Visitable{

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


	@Override
	public void accept(Visitor visitor, SymbolTable ST) {
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}


}
