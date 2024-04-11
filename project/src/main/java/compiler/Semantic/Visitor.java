package compiler.Semantic;

import java.util.ArrayList;
import compiler.Parser.*;

public interface Visitor {

    ArrayList<Type> visit(ArrayAccess arrayAccess);
}
