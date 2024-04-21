package compiler.Parser;

import java.util.ArrayList;
import java.util.HashMap;

import compiler.Semantic.*;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public abstract class Statement {

    public abstract String toString();

    public abstract boolean equals(Object obj);

    public abstract void accept(TableVisitor visitor, SymbolTable symbolTable) throws SemanticException;
    
    public abstract Type acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable st) throws SemanticException;
}
