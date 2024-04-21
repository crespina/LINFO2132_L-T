package compiler.Semantic;

import compiler.Parser.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    SymbolTable previous;
    HashMap<String, ArrayList<Type>> entries = new HashMap<>();
    HashMap<String, ArrayList<Type>> structures = new HashMap<String, ArrayList<Type>>();

    public SymbolTable(SymbolTable previous) {
        this.previous = previous;
    }

    public HashMap<String, ArrayList<Type>> getTable() {
        return entries;
    }

    public SymbolTable getPrevious() {
        return previous;
    }
    
    public void addEntry(String id, ArrayList<Type> types) {
    	entries.put(id, types);   
    }
    
    public void addStructure(String name, ArrayList<Type> types) {
    	structures.put(name, types);
    }

    public ArrayList<Type> get(String identifier) {
        if(entries.containsKey(identifier)) {
            return entries.get(identifier);
        }
        else {
            return null;
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
