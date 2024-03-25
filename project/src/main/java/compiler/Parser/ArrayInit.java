package compiler.Parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class ArrayInit extends Statement{
    
    String keyword;
    ArrayList<Statement> values;

    /**
	 * @param identifier
	 * @param values
	 */
    public ArrayInit(String keyword, ArrayList<Statement> values) {
		super();
        this.keyword = keyword;
        this.values = values;
	}

    public String toString() {
		return "ArrayInit : " + "keyword = " + keyword + ", values = " + values;
	}

	public boolean equals (Object o) {
		ArrayInit arr = (ArrayInit) o;
		return Objects.equals(this.keyword, arr.keyword) && Objects.equals(this.values, arr.values);
	}
}
