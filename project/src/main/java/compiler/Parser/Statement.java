package compiler.Parser;

import java.util.ArrayList;
import compiler.Semantic.*;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public abstract class Statement implements Visitable {

    public abstract String toString();

    public abstract boolean equals(Object obj);
    
    public abstract ArrayList<Type> accept(Visitor visitor);
}
