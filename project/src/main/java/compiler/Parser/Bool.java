package compiler.Parser;

import compiler.Semantic.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Bool extends Statement implements TableVisitable, TypeCheckVisitable{

    
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

    public Type getType() {
		return new Type("Bool");
	}

	@Override
	public void accept(TableVisitor visitor, SymbolTable ST) throws SemanticException{
		// TODO Auto-generated method stub
		visitor.visit(this, ST);
	}


	@Override
	public void acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException {
		// TODO Auto-generated method stub
		visitor.TypeCheck(this, ST);
	}


}
