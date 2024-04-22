package compiler.Semantic;

import compiler.Parser.Type;
import compiler.Parser.Param;
import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    HashMap<String, ArrayList<Type>> entries = new HashMap<>();
    HashMap<String, ArrayList<Param>> structures = new HashMap<String, ArrayList<Param>>();
    HashMap<String,SymbolTable> scopes = new HashMap<String,SymbolTable>();

    public SymbolTable() {
    }

    public HashMap<String, ArrayList<Type>> getTable() {
        return entries;
    }

    public void addEntry(String id, ArrayList<Type> types) {
    	entries.put(id, types);   
    }
    
    public void addStructure(String name, ArrayList<Param> types) {
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
    
    public Boolean scopeContains(String identifier) {
    	return scopes.containsKey(identifier);
    }
    
    public SymbolTable getScopes(String identifier) {
    	if(scopes.containsKey(identifier)) {
            return scopes.get(identifier);
        }
        else {
            return null;
        }
    }
    
    public void addScope(String identifier, SymbolTable st) {
    	scopes.put(identifier,st);
    }
    
    public void addAll(SymbolTable other) {
    	this.entries.putAll(other.entries); //this will contain all from other
    }

	@Override
	public String toString() {
		return "SymbolTable [" + (entries != null ? "entries=" + entries + ", " : "")
				+ (structures != null ? "structures=" + structures + ", " : "")
				+ (scopes != null ? "scopes=" + scopes : "") + "]";
	}

}
