package compiler.Lexer;

public class Symbol {
	
	private String token; 
	private String attribute;
	
	public Symbol(String token, String attribute) {
		super();
		this.token = token;
		this.attribute = attribute;
	}

	protected String getToken() {
		return token;
	}

	protected void setToken(String token) {
		this.token = token;
	}

	protected String getAttribute() {
		return attribute;
	}

	protected void setAttribute(String attribute) {
		this.attribute = attribute;
	}	
	
	protected boolean isNull() {
		return (this.attribute == null && this.token == null);
	}
	
	
}


