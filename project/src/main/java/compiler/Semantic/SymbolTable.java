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
    	//adding the built-in function 
    	//{"readInt", "readString", "writeInt", "readFloat", "writeFloat", "write", "writeln", "chr", "len", "floor", "free"};
    	
    	/*
    	//readInt
    	ArrayList<Param> param_ReadInt = new ArrayList<Param>();
    	param_ReadInt.add(new Param(new Type("int"),null)); //return Type
    	this.entries.put("readInt", param_ReadInt);
    	
    	//readString
    	ArrayList<Param> param_ReadString = new ArrayList<Param>();
    	param_ReadString.add(new Param(new Type("string"),null)); //return Type
    	this.entries.put("readString", param_ReadString);
    	
    	//readFloat
    	ArrayList<Param> param_ReadFloat = new ArrayList<Param>();
    	param_ReadFloat.add(new Param(new Type("float"),null)); //return Type
    	this.entries.put("readFloat", param_ReadFloat);
    	
    	//writeInt
    	ArrayList<Param> param_writeInt = new ArrayList<Param>();
    	param_writeInt.add(new Param(new Type("int"),"")); //argument
    	param_writeInt.add(new Param(new Type("void"),null)); //return Type
    	this.entries.put("writeInt", param_writeInt);
    	
    	//writeFloat
    	ArrayList<Param> param_writeFloat = new ArrayList<Param>();
    	param_writeFloat.add(new Param(new Type("float"),"")); //argument
    	param_writeFloat.add(new Param(new Type("void"),null)); //return Type
    	this.entries.put("writeFloat", param_writeFloat);
    	
    	
    	//write string
    	//ArrayList<Param> param_write = new ArrayList<Param>();
    	//param_write.add(new Param(new Type("string"),"")); //argument
    	//param_write.add(new Param(new Type("void"),null)); //return Type
    	//this.entries.put("write", param_write);
    	
    	//write nothing
    	ArrayList<Param> param_write = new ArrayList<Param>();
    	param_write.add(new Param(new Type("void"),null)); //return Type
    	this.entries.put("write", param_write);
    	
    	//writeln nothing
    	ArrayList<Param> param_writeln = new ArrayList<Param>();
    	param_writeln.add(new Param(new Type("void"),null)); //return Type
    	this.entries.put("writeln", param_writeln);
    	
    	//chr
    	ArrayList<Param> param_chr = new ArrayList<Param>();
    	param_chr.add(new Param(new Type("int"),"")); //argument
    	param_chr.add(new Param(new Type("string"),null)); //return Type
    	this.entries.put("chr", param_chr); 
    	
    	//len string (missing array)
    	ArrayList<Param> param_len = new ArrayList<Param>();
    	param_len.add(new Param(new Type("string"),"")); //argument
    	param_len.add(new Param(new Type("int"),null)); //return Type
    	this.entries.put("len", param_len);
    	
    	//floor
    	ArrayList<Param> param_floor = new ArrayList<Param>();
    	param_floor.add(new Param(new Type("float"),"")); //argument
    	param_floor.add(new Param(new Type("int"),null)); //return Type
    	this.entries.put("floor", param_floor);
    	
    	*/
    	
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
    	this.structures.putAll(other.structures);
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
    
    public ArrayList<Param> paramOfAFunction(String funcId){
    	return entries.get(funcId);
    }

	@Override
	public String toString() {
		return "SymbolTable [" + (entries != null ? "entries=" + entries + ", " : "")
				+ (structures != null ? "structures=" + structures + ", " : "")
				+ (scopes != null ? "scopes=" + scopes : "") + "]";
	}

}
