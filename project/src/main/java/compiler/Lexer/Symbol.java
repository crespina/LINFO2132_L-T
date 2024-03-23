package compiler.Lexer;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Symbol {
	
	public String token; 
	public String attribute;
	
	/**
	 * @param token : Token of the symbol. Tokens can be : "Number", "Comment", "OpenCurlyBraket", "OpenParenthesis", "OpenSquareBraket", "CloseCurlyBraket", "CloseParenthesis", "CloseSquareBraket", "Dot", "Semicolon", "Colon", "Operation", "Identifier", "String", "Keyword"+name of the keyword in uppercase, or "IllegalToken"
	 * @param attribute : Attribute of the symbol. Attributes allows to further discribes the symbol depending on the token
	 * 
	 * Symbols are generated using the lexer
	 */
	public Symbol(String token, String attribute) {
		super();
		this.token = token;
		this.attribute = attribute;
	}

	/**
	 * Getter for the attribute token.
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Setter for the attribute token
	 * @param token : new token for the Symbol
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Getter for the attribute 'attribute'
	 * @return attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * Setter for the attribute 'attribute'
	 * @param attribute : new 'attribute' for the symbol
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}	
	
	/**
	 * The method isNull return a Boolean indicating wether the Symbol is null. 
	 * @return {@link Boolean} true if the symbol is null, i.e. both token and attribute are null, false otherwise
	 */
	public boolean isNull() {
		return (this.attribute == null && this.token == null);
	}

	@Override
	public String toString() {
		if (this.attribute != null) {
			return "token=" + token + ", attribute=" + attribute;
		} else {
			return "token=" + token;
		}
		
	}
	
	
}


