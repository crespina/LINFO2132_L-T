package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

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


    public Type getType() {
		return new Type("String");
	}
	@Override
	public void accept(Visitor visitor, SymbolTable ST, HashMap <String, ArrayList<Param>> funcT) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST, funcT);
	}


}
