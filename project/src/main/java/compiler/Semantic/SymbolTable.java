package compiler.Semantic;

import compiler.Parser.Statement;
import java.util.HashMap;

public class SymbolTable {
    SymbolTable previous;
    HashMap<String, Statement> entries = new HashMap<>();

    public SymbolTable(SymbolTable previous) {
        this.previous = previous;
    }

    public HashMap<String, Statement> getTable() {
        return entries;
    }

    public SymbolTable getPrevious() {
        return previous;
    }
}
