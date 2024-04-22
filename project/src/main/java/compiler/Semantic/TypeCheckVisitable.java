package compiler.Semantic;

import compiler.Parser.Type;

public interface TypeCheckVisitable {

    public Type acceptTypeCheck(TypeCheckVisitor visitor, SymbolTable ST) throws SemanticException;
}
