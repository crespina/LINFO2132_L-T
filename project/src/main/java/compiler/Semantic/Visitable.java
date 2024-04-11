package compiler.Semantic;

import java.util.ArrayList;
import compiler.Parser.Type;

public interface Visitable {

    public ArrayList<Type> accept(Visitor visitor) throws SemanticException;
}
