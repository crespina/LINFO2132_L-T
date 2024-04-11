package compiler.Semantic;

import java.util.ArrayList;
import compiler.Parser.Parser;
import compiler.Parser.ParserException;
import compiler.Parser.Statement;

public class SemanticAnalysis {

    Parser parser;
    ArrayList<Statement> statements;
    SymbolTableVisitor symbolVisitor = new SymbolTableVisitor();
    SemanticVisitor semanticVisitor = new SemanticVisitor();

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
            s.accept(symbolVisitor);
        }
    }

    public void doSemanticAnalysis() {

    }
}
