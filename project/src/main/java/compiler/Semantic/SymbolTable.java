package compiler.Semantic;

import compiler.Parser.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    SymbolTable previous;
    HashMap<String, Tuple> entries = new HashMap<>();
    ArrayList<String> structures = new ArrayList<String>();

    public SymbolTable(SymbolTable previous) {
        this.previous = previous;
    }

    public HashMap<String, Tuple> getTable() {
        return entries;
    }

    public SymbolTable getPrevious() {
        return previous;
    }
    
    public void addEntry(String id, Tuple types) {
    	entries.put(id, types);   
    }
    
    public void addStructure(String name) {
    	structures.add(name);
    }

	@Override
	public String toString() {
		return "SymbolTable [" + (previous != null ? "previous=" + previous + ", " : "")
				+ (entries != null ? "entries=" + entries + ", " : "")
				+ (structures != null ? "structures=" + structures : "") + "]";
	}

	
    
    
}
