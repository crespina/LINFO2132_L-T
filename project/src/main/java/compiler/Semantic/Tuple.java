package compiler.Semantic;
import java.util.ArrayList;

import compiler.Parser.Type;

public class Tuple {
	
    ArrayList<Type> types;
    SymbolTable symbolTable;

    public Tuple(ArrayList<Type> types, SymbolTable symbolTable) {
        this.types = types;
        this.symbolTable = symbolTable;
    }

	@Override
	public String toString() {
		return "Tuple [" + (types != null ? "types=" + types + ", " : "")
				+ (symbolTable != null ? "symbolTable=" + symbolTable : "") + "]";
	}
    
    
}