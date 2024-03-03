package compiler.Lexer;

public class Symbol {
	
	private String token; 
	private String attribute;
	
	public Symbol(String token, String attribute) {
		super();
		this.token = token;
		this.attribute = attribute;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}	
	
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


