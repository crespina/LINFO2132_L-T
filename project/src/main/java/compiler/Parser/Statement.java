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

    public abstract Type getType();

    public abstract void accept(Visitor visitor, SymbolTable symbolTable) throws SemanticException;
}
