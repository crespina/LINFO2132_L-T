package compiler.Semantic;

import compiler.Parser.Type;
import compiler.Parser.Param;
import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    HashMap<String, ArrayList<Param>> entries = new HashMap<>();
    HashMap<String, ArrayList<Param>> structures = new HashMap<String, ArrayList<Param>>();
    HashMap<String,SymbolTable> scopes = new HashMap<String,SymbolTable>();
    HashMap<Integer,String> method_Return = new HashMap<Integer,String>();

    public SymbolTable() {
    }

    public HashMap<String, ArrayList<Param>> getTable() {
        return entries;
    }

    public void addEntry(String id, ArrayList<Param> types) {
    	entries.put(id, types);   
    }
    
    public void addStructure(String name, ArrayList<Param> types) {
    	structures.put(name, types);
    }

    public ArrayList<Param> get(String identifier) {
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
    
    public Boolean structContains(String identifier) {
        if(structures.containsKey(identifier)) {
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
    	this.method_Return.putAll(other.method_Return);
    }

    public void addReturn(int id, String functionName) {
        method_Return.put(id, functionName);
    }

    public HashMap<Integer,String> getReturnTable() {
        return method_Return;
    }

    public String getReturn(int id) {
        if(method_Return.containsKey(id)) {
            return method_Return.get(id);
        }
        else {
            return null;
        }
    }

	@Override
	public String toString() {
		return "SymbolTable [" + (entries != null ? "entries=" + entries + ", " : "")
				+ (structures != null ? "structures=" + structures + ", " : "")
				+ (scopes != null ? "scopes=" + scopes : "") + "]";
	}

}
