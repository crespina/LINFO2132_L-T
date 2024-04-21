package compiler.Semantic;

import java.util.ArrayList;
import java.util.HashMap;

import compiler.Parser.*;
import compiler.Parser.ParserException;
import compiler.Parser.Statement;

public class SemanticAnalysis {

    Parser parser;
    ArrayList<Statement> statements;
    Visitor symbolVisitor = new SymbolTableVisitor();
    Visitor semanticVisitor = new SemanticVisitor();
    SymbolTable st = new SymbolTable(null);
    HashMap <String, ArrayList<Param>> function = new HashMap<>();

    public SemanticAnalysis(Parser parser) throws ParserException{
        this.parser = parser;
		try {
			this.statements = parser.getAST();
		} catch (ParserException e) {
			throw new ParserException("Problem during the parsing");
		}
    }

    public void setSymbolTable() {
        for (Statement s : statements) {
            try {
				s.accept(symbolVisitor, st, function);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public SymbolTable getSt() {
		return st;
	}

    public HashMap <String, ArrayList<Param>> getFT() {
        return function;
    }

	public void doSemanticAnalysis() {
        for (Statement s : statements) {
            try {
				s.accept(semanticVisitor, st, function);
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
