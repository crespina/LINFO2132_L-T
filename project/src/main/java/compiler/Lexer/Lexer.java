package compiler.Lexer;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.PushbackReader;

public class Lexer {
	
	private List<Symbol> lexedInput;
    PushbackReader r;
    String[] keywords = new String[]{"struct", "final", "int", "float", "String", "bool", "while", "if", "else",
                                    "for", "void", "def", "return", "free", "readInt", "readString", "writeInt",
                                    "readFloat", "writeFloat", "write", "writeln","chr", "len", "floor"};
    
    public Lexer(Reader input) {
    	this.lexedInput = new ArrayList<Symbol>();
    	this.r = new PushbackReader(input, 10);
    }   
    
    protected List<Symbol> getLexedInput() {
		return lexedInput;
	}

	public Symbol getNextSymbol(){
        
        try {
            int c = r.read();

            // ignore whitespaces and line return
            while (c==' ' || c== '\n' || c == '\t'){
                c=r.read();
            }

            //Numbers
            
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
                }
            }
            
            //{([])}
            
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
            
            // .
            
            if (c == '.') {
                return new Symbol("Dot", null);
            }      
            
            //Operations 

            if (c == '+') {
                return new Symbol("AddOperation", "+");
            }
            
            if (c == '-') {
                return new Symbol("MinusOperation", "-");
            }
            
            if (c == '*') {
                return new Symbol("MultOperation", "+");
            }
            
            if (c == '/') {
                return new Symbol("DivideOperation", "/");
            }
                    
            
            if (c == '%') {
                return new Symbol("Operation", "%");
            }
            
            if (c == '=') {
                c = r.read();       	
                if (c == '=') {
                    return new Symbol("EqualComparison", "==");
                }
                
                else {
                    r.unread(c);
                    return new Symbol("EqualOperation","=");
                }
                
            }
            
            if (c == '!') {
                c = r.read();       	
                if (c == '=') {
                    return new Symbol("DifferentComparison", "!=");
                }
                
                else {
                    
                    return new Symbol("Operation", "!");
                }
                
            }
            
            if (c == '>') {
                c = r.read();       	
                if (c == '=') {
                    return new Symbol("Operation", ">=");
                }
                
                else {
                    return new Symbol("Operation", ">");
                }
                
            }
            
            if (c == '<') {
                c = r.read();       	
                if (c == '=') {
                    return new Symbol("Operation", "<=");
                }
                
                else {
                    return new Symbol("Operation", "<");
                }
                
            }
            
            if (c == '&') {
                c = r.read();       	
                if (c == '&') {
                    return new Symbol("Operation", "&&");
                }
                
                else {
                    return new Symbol("IllegalToken", null);
                }
                
            }
            
            if (c == '|') {
                c = r.read();       	
                if (c == '|') {
                    return new Symbol("Operation", "||");
                }
                
                else {
                    return new Symbol("IllegalToken", null);
                }
                
            }
            
            // keywords : struct, final, int, float, String, bool, while/if/else/for readInt, readFloat, readString, writeInt, writeFloat, write, writeln, free, return , def, void
            
            if ((c>='a' && c <= 'z') || (c>='A' && c <= 'Z') || c == '_') {
                String s = "";
                while((c>='a' && c <= 'z') || (c>='A' && c <= 'Z') || c == '_') {
                    s+=(char)c;
                    c = r.read();
                }
                if (isKeyword(s)){
                    return new Symbol("Keyword"+s.toUpperCase(), null);
                }else {
                    return new Symbol("Identifier", s);
                }
            } 

            // String
            if (c=='"') {
                String s = "";
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
	
    public boolean isKeyword(String s){
        for (int i=0; i<keywords.length; i++){
            if(keywords[i].equals(s)){
                return true;
            }
        }
        return false;
    }
	// Built-in functions:
	
	
//  string chr(int)             turns the character (an int value) into a string
//  int len(string or array)    gives the length of a string or array
//  int floor(float)             returns the largest integer less than or equal the float value

}
