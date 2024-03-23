package compiler.Lexer;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.PushbackReader;

/**
 * @author A. Crespin & R. De Oliveira
 *
 */
public class Lexer {
	
	private List<Symbol> lexedInput;
    PushbackReader r; 
    String[] keywords = new String[]{"struct", "final", "int", "float", "string", "bool", "while", "if", "else",
                                    "for", "void", "def", "return", "free", "readInt", "readString", "writeInt",
                                    "readFloat", "writeFloat", "write", "writeln","chr", "len", "floor"};
    
    /**
     * @param input : {@link Reader} containing the String input that will be lexed character by character
     * 
     * lexedInput : {@link ArrayList} containing all the {@link Symbol} of the input once parsed
     * r : {@link PushbackReader} using the input {@link Reader} 
     */
    public Lexer(Reader input) {
    	this.lexedInput = new ArrayList<Symbol>(); 
    	this.r = new PushbackReader(input, 100); 
    }   
    
    /**
     * Getter for the lexedInput attribute
     * @return lexedInput : {@link ArrayList} containing all the {@link Symbol} of the input once parsed
	 * 
     */
    public List<Symbol> getLexedInput() {
		return lexedInput;
	}
    
    /**
     * @throws IOException
     * The lex() function iterates over the input until it's finished, transforming the input (a String) into a List of Symbols (lexedInput)
     */
    public void lex() throws IOException {
    	int c = r.read();
    	while (c != -1) {
    		r.unread(c);
    		Symbol s = getNextSymbol();
    		if (!s.isNull()) {
    			lexedInput.add(s);
    		} 		
    		c = r.read();
    	}
    }

	/**
	 * The getNextSymbol function uses the PushbackReader attribute r to read the input character by character. 
	 * It returns the next Symbol in the input.
	 * @return {@link Symbol}
	 */
	public Symbol getNextSymbol(){
        
        try {
            int c = r.read();

            // Ignore whitespaces, line return, carriage return
            while (c==' ' || c== '\n' || c == '\t' || c == 13){
                c=r.read();
            }

            // Numbers
            
            if (c >= '0' && c <= '9') {       	
                String s = ""+(char)c;    	
                
                while(true) {  
                    c = r.read();
                                
                    if (c < '0' || c > '9') {
                        r.unread(c);
                        break;
                    }
                    
                    s += (char)c; 
                }
                
                return new Symbol("Number", s);
            }
            
            
            // Comment 
            
            if (c == '/') {
                c = r.read();
                if (c == '/') {
                    String comment = "";
                    while (c != 10) {
                        c = r.read();
                        comment += (char) c;
                    }
                    
                    return new Symbol("Comment", comment);
                } else {
                	r.unread(c);
                	return new Symbol("Operation", "/");                   
                }
            }
            
            // { ( [ ] ) }
            
            if (c == '{') {
                return new Symbol("OpenCurlyBraket", null);
            }
            
            if (c == '(') {
                return new Symbol("OpenParenthesis", null);
            }
            
            if (c == '[') {
                return new Symbol("OpenSquareBraket", null);
            }
            
            if (c == '}') {
                return new Symbol("CloseCurlyBraket", null);
            }
            
            if (c == ')') {
                return new Symbol("CloseParenthesis", null);
            }
            
            if (c == ']') {
                return new Symbol("CloseSquareBraket", null);
            }
            
            // . and ; and ,
            
            if (c == '.') {
                return new Symbol("Dot", null);
            }  
            
            if (c == ';') {
                return new Symbol("Semicolon", null);
            }  
            
            if (c == ',') {
                return new Symbol("Comma", null);
            }   
            
            //Operations 

            if (c == '+') {
                return new Symbol("Operation", "+");
            }
            
            if (c == '-') {
                return new Symbol("Operation", "-");
            }
            
            if (c == '*') {
                return new Symbol("Operation", "*");
            }                               
            
            if (c == '%') {
                return new Symbol("Operation", "%");
            }
            
            if (c == '=') {
                c = r.read();       	
                if (c == '=') {
                    return new Symbol("Operation", "==");
                }
                
                else {
                    r.unread(c);
                    return new Symbol("Operation","=");
                }
                
            }
            
            if (c == '!') {
                c = r.read();       	
                if (c == '=') {
                    return new Symbol("Operation", "!=");
                }
                
                else {
                	r.unread(c);
                    return new Symbol("Operation", "!");
                }
                
            }
            
            if (c == '>') {
                c = r.read();       	
                if (c == '=') {
                    return new Symbol("Operation", ">=");
                }
                
                else {
                	r.unread(c);
                    return new Symbol("Operation", ">");
                }
                
            }
            
            if (c == '<') {
                c = r.read();       	
                if (c == '=') {
                    return new Symbol("Operation", "<=");
                }
                
                else {
                	r.unread(c);
                    return new Symbol("Operation", "<");
                }
                
            }
            
            if (c == '&') {
                c = r.read();       	
                if (c == '&') {
                    return new Symbol("Operation", "&&");
                }
                
                else {
                	r.unread(c);
                    return new Symbol("IllegalToken", null);
                }
                
            }
            
            if (c == '|') {
                c = r.read();       	
                if (c == '|') {
                    return new Symbol("Operation", "||");
                }
                
                else {
                	r.unread(c);
                    return new Symbol("IllegalToken", null);
                }
                
            }
            
            // Identifiers and Keywords 
            
            if ((c>='a' && c <= 'z') || (c>='A' && c <= 'Z') || c == '_') {
            	
                String s = "";
                
                while((c>='a' && c <= 'z') || (c>='A' && c <= 'Z') || c == '_') {
                    s += (char)c;
                    c = r.read();
                }   
                
                r.unread(c);  
                
                if (isKeyword(s)){           	
                    return new Symbol("Keyword"+s.toUpperCase(), null);                    
                }else {                	
                    return new Symbol("Identifier", s);
                }
            } 

            // String
            if (c=='"') {
            	
                String s = "";
                c = r.read();
                
                while (c!= '"') {
                    s+=(char)c;
                    c=r.read();
                }
                return new Symbol("String", s);
            }
            
		    return new Symbol("IllegalToken", null);

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
	
    /**
     * @param s : {@link String} to be compared to the keywords list
     * @return {@link Boolean} true if s is a keyword (appear in the keywords list), false if not 
     */
    public boolean isKeyword(String s){
        for (int i=0; i<keywords.length; i++){
            if(keywords[i].equals(s)){
                return true;
            }
        }
        return false;
    }
}
