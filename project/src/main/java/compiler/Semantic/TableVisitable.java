package compiler.Semantic;

import java.util.ArrayList;
import java.util.HashMap;
import compiler.Parser.*;

public interface TableVisitable {

    public void accept(TableVisitor visitor, SymbolTable ST) throws SemanticException;
}
