package compiler.Semantic;

import compiler.Parser.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    SymbolTable previous;
    HashMap<String, Type> entries = new HashMap<>();
    ArrayList<String> structures = new ArrayList<String>();

    public SymbolTable(SymbolTable previous) {
        this.previous = previous;
    }

    public HashMap<String, Type> getTable() {
        return entries;
    }

    public SymbolTable getPrevious() {
        return previous;
    }
    
    public void addEntry(String id, Type type) {
    	entries.put(id, type);   
    }
    
    public void addStructure(String name) {
    	structures.add(name);
    }

    public Type get(String identifier) {
        if(entries.containsKey(identifier)) {
            return entries.get(identifier);
        }
        else {
            return new Type(null);
        }
    }

    public Boolean contains(String identifier) {
        if(entries.containsKey(identifier)) {
            return true;
        }
        else {
            return false;
        }
    }


	@Override
	public String toString() {
		return "SymbolTable [" + (previous != null ? "previous=" + previous + ", " : "")
				+ (entries != null ? "entries=" + entries + ", " : "")
				+ (structures != null ? "structures=" + structures : "") + "]";
	}

	
    
    
}
