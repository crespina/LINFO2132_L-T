package compiler.Parser;

import java.util.ArrayList;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Method {
	
	String identifier;
	Type returnType;
	ArrayList<Param> parameters;
	ArrayList<Statement> body;
	
	
	/**
	 * @param identifier
	 * @param returnType
	 * @param parameters
	 * @param body
	 */
	public Method(String identifier, Type returnType, ArrayList<Param> parameters, ArrayList<Statement> body) {
		super();
		this.identifier = identifier;
		this.returnType = returnType;
		this.parameters = parameters;
		this.body = body;
	}
	
}
