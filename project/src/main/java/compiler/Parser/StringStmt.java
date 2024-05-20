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
public class StringStmt extends Statement implements TableVisitable, TypeCheckVisitable, GenVisitable{

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
