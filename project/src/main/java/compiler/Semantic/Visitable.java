package compiler.Semantic;

public interface Visitable {

    public void accept(Visitor visitor, SymbolTable ST) throws SemanticException;
}
