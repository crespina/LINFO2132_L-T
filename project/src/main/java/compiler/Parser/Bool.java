package compiler.Parser;

import compiler.Generator.GenVisitable;
import compiler.Generator.GenVisitor;
import compiler.Semantic.*;
import java.util.Objects;

import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Bool extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{

    
    String content;

    /**
	 * @param content
	 */
    public Bool(String content) {
        this.content = content;
    }
    
    public String getContent() {
    	return content;
    }


    public String toString() {
        return "boolean : " + content;
    }


    public boolean equals(Object o) {
		Bool b = (Bool) o;
		return Objects.equals(this.content, b.content);
    }

    public Type getType() {
		return new Type("bool");
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
