package compiler.Semantic;

import java.util.ArrayList;
import java.util.HashMap;
import compiler.Parser.*;

public interface Visitable {

    public void accept(Visitor visitor, SymbolTable ST, HashMap <String, ArrayList<Param>> func) throws SemanticException;
}
