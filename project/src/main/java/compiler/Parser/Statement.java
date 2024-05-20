package compiler.Parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.objectweb.asm.MethodVisitor;

import compiler.Generator.GenVisitor;
import compiler.Semantic.*;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public abstract class Statement {

    public abstract String toString();

    public abstract boolean equals(Object obj);

    public abstract void accept(TableVisitor visitor, SymbolTable symbolTable) throws SemanticException;

    public abstract void accept(GenVisitor visitor, MethodVisitor mv) throws SemanticException;
    
    public abstract Type acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable st) throws SemanticException;
}
