package compiler.Lexer;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.PushbackReader;

public class Lexer {
	
	private List<Symbol> lexedInput;
    PushbackReader r;
    
    public Lexer(Reader input) {
    	this.lexedInput = new ArrayList<Symbol>();
    	this.r = new PushbackReader(input, 10);

        /* 
    	Symbol s = new Symbol("","");
    	try {
			while (input.read() != -1) {
				s = getNextSymbol(pbReader); 
				this.lexedInput.add(s);
			}
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}  finally {
			
			try {
                input.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
        */
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

            //Number
            
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
            
            //String 
            // Tout est reconnu comme string avec ca
            /* 
            else if ((c>='a' && c <= 'z') || (c>='A' && c <= 'Z')) {
                
                String s = "";
                
                while(true) {
                    s+=(char)c;
                    c = r.read();
                    if (!((c>='a' && c <= 'z') || (c>='A' && c <= 'Z'))) {
                        r.unread(c);
                        break;
                    }
                }
                
                return new Symbol("String", s);
            */
            
            // Comment 
            
            if (c == '/') {
                c = r.read();
                if (c == '/') {
                    String comment = "";
                    while ((int) c != 10) {
                        c = r.read();
                        comment += (char) c;
                    }
                    
                    return new Symbol("Comment", comment);
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
            
            // keywords : struct, final, int, float, String, bool, while/if/else/for readInt, readFloat, readString, writeInt, writeFloat, write, writeln, free, return , def, void
            
            
            // Keyword "struct"
            
            if (c == 's') {
                
                // Read the next 6 characters
                char[] buffer = new char[6];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 6 && buffer[0] == 't' && buffer[1] == 'r' && buffer[2] == 'u' && buffer[3] == 'c' && buffer[4] == 't' && buffer[5] == ' ') {
                    return new Symbol("KeywordStruct", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            // Keyword "final"
            
            if (c == 'f') {
                
                // Read the next 5 characters
                char[] buffer = new char[5];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 5 && buffer[0] == 'i' && buffer[1] == 'n' && buffer[2] == 'a' && buffer[3] == 'l' && buffer[4] == ' ') {
                    return new Symbol("KeywordFinal", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }

            // Keyword "int" 
            
            if (c == 'i') {
                
                // Read the next 3 characters
                char[] buffer = new char[3];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 3 && buffer[0] == 'n' && buffer[1] == 't' && buffer[2] == ' ') {
                    return new Symbol("KeywordInt", null);
                } else {
                    // "Unread" the characters
                    r.unread(buffer);
                }
            }
            
            //Keyword "float"
            
            if (c == 'f') {
                
                // Read the next 5 characters
                char[] buffer = new char[5];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 5 && buffer[0] == 'l' && buffer[1] == 'o' && buffer[2] == 'a' && buffer[3] == 't' && buffer[4] == ' ') {
                    return new Symbol("KeywordFloat", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "String"
            
            if (c == 'S') {
                
                // Read the next 6 characters
                char[] buffer = new char[6];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 6 && buffer[0] == 't' && buffer[1] == 'r' && buffer[2] == 'i' && buffer[3] == 'n' && buffer[4] == 'g' && buffer[5] == ' ') {
                    return new Symbol("KeywordString", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "bool"
            
            if (c == 'b') {
                
                // Read the next 4 characters
                char[] buffer = new char[4];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 4 && buffer[0] == 'o' && buffer[1] == 'o' && buffer[2] == 'l' && buffer[3] == ' ') {
                    return new Symbol("KeywordBool", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "while"
            
            if (c == 'w') {
                
                // Read the next 5 characters
                char[] buffer = new char[5];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 5 && buffer[0] == 'h' && buffer[1] == 'i' && buffer[2] == 'l' && buffer[3] == 'e' && buffer[4] == ' ') {
                    return new Symbol("KeywordWhile", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "if"
            
            if (c == 'i') {
                
                // Read the next 2 characters
                char[] buffer = new char[2];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 2 && buffer[0] == 'f' && buffer[1] == ' ') {
                    return new Symbol("KeywordIf", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "else"
            
            if (c == 'e') {
                
                // Read the next 4 characters
                char[] buffer = new char[4];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 4 && buffer[0] == 'l' && buffer[1] == 's' && buffer[2] == 'e' && buffer[3] == ' ') {
                    return new Symbol("KeywordElse", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "for"
            
            if (c == 'f') {
                
                // Read the next 3 characters
                char[] buffer = new char[3];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 3 && buffer[0] == 'o' && buffer[1] == 'r' && buffer[2] == ' ') {
                    return new Symbol("KeywordFor", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            // Keyword "void"
                
            if (c == 'v') {
                
                // Read the next 4 characters
                char[] buffer = new char[4];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 4 && buffer[0] == 'o' && buffer[1] == 'i' && buffer[2] == 'd' && buffer[3] == ' ') {
                    return new Symbol("KeywordVoid", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "def"
            
            if (c == 'd') {
                
                // Read the next 3 characters
                char[] buffer = new char[3];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 3 && buffer[0] == 'e' && buffer[1] == 'f' && buffer[2] == ' ') {
                    return new Symbol("KeywordDef", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "return"
            
            if (c == 'r') {
                
                // Read the next 6 characters
                char[] buffer = new char[6];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 6 && buffer[0] == 'e' && buffer[1] == 't' && buffer[2] == 'u' && buffer[3] == 'r' && buffer[4] == 'n' && buffer[5] == ' ') {
                    return new Symbol("KeywordReturn", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "free"
            
            if (c == 'f') {
                
                // Read the next 4 characters
                char[] buffer = new char[4];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 4 && buffer[0] == 'r' && buffer[1] == 'e' && buffer[2] == 'e' && buffer[3] == ' ') {
                    return new Symbol("KeywordFree", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "readInt"
            
            if (c == 'r') {
                
                // Read the next 7 characters
                char[] buffer = new char[7];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 7 && buffer[0] == 'e' && buffer[1] == 'a' && buffer[2] == 'd' && buffer[3] == 'I' && buffer[4] == 'n' && buffer[5] == 't' && buffer[6] == ' ') {
                    return new Symbol("IOProcedure", "readInt");
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "readString"
            
            if (c == 'r') {
                
                // Read the next 10 characters
                char[] buffer = new char[10];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 10 && buffer[0] == 'e' && buffer[1] == 'a' && buffer[2] == 'd' && buffer[3] == 'S' && buffer[4] == 't' && buffer[5] == 'r' && buffer[6] == 'i' && buffer[7] == 'n' && buffer[8] == 'g' && buffer[9] == ' ') {
                    return new Symbol("IOProcedure", "readString");
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "writeInt"
            
            if (c == 'w') {
                
                // Read the next 8 characters
                char[] buffer = new char[8];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 8 && buffer[0] == 'r' && buffer[1] == 'i' && buffer[2] == 't' && buffer[3] == 'e' && buffer[4] == 'I' && buffer[5] == 'n' && buffer[6] == 't' && buffer[7] == ' ') {
                    return new Symbol("IOProcedure", "writeInt");
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }       
            
            //Keyword "readFloat"
            
            if (c == 'r') {
                
                // Read the next 9 characters
                char[] buffer = new char[9];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 9 && buffer[0] == 'e' && buffer[1] == 'a' && buffer[2] == 'd' && buffer[3] == 'F' && buffer[4] == 'l' && buffer[5] == 'o' && buffer[6] == 'a'  && buffer[7] == 't' && buffer[8] == ' ') {
                    return new Symbol("IOProcedure", "readFloat");
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "writeFloat"
            
            if (c == 'w') {
                
                // Read the next 10 characters
                char[] buffer = new char[10];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 10 && buffer[0] == 'r' && buffer[1] == 'i' && buffer[2] == 't' && buffer[3] == 'e' && buffer[4] == 'F' && buffer[5] == 'l' && buffer[6] == 'o' && buffer[7] == 'a' && buffer[8] == 't' && buffer[9] == ' ') {
                    return new Symbol("IOProcedure", "writeFloat");
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "write"
            
            if (c == 'w') {
                
                // Read the next 5 characters
                char[] buffer = new char[5];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 5 && buffer[0] == 'r' && buffer[1] == 'i' && buffer[2] == 't' && buffer[3] == 'e' && buffer[4] == ' ') {
                    return new Symbol("IOProcedure", "write");
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "writeln"
            
            if (c == 'w') {
                
                // Read the next 7 characters
                char[] buffer = new char[7];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 7 && buffer[0] == 'r' && buffer[1] == 'i' && buffer[2] == 't' && buffer[3] == 'e' && buffer[4] == 'l' && buffer[5] == 'n' && buffer[6] == ' ') {
                    return new Symbol("IOProcedure", "writeln");
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            // Keyword "chr" 
            
            if (c == 'c') {
                
                // Read the next 3 characters
                char[] buffer = new char[3];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 3 && buffer[0] == 'h' && buffer[1] == 'r' && buffer[2] == ' ') {
                    return new Symbol("KeywordChr", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            // Keyword "len" 
            
            if (c == 'l') {
                
                // Read the next 3 characters
                char[] buffer = new char[3];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 3 && buffer[0] == 'e' && buffer[1] == 'n' && buffer[2] == ' ') {
                    return new Symbol("KeywordLen", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
            }
            
            //Keyword "floor"
            
            if (c == 'f') {
                
                // Read the next 5 characters
                char[] buffer = new char[5];
                int bytesRead = r.read(buffer);
                
                if (bytesRead == 5 && buffer[0] == 'l' && buffer[1] == 'o' && buffer[2] == 'o' && buffer[3] == 'r' && buffer[4] == ' ') {
                    return new Symbol("KeywordFloor", null);
                } else {
                    // "Unread" the characters
                    for (int i = bytesRead - 1; i >= 0; i--) {
                        r.unread(buffer[i]);
                    }
                }
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
        
		return new Symbol("IllegalToken", null);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
	
	// Built-in functions:
	
	
//  string chr(int)             turns the character (an int value) into a string
//  int len(string or array)    gives the length of a string or array
//  int floor(float)             returns the largest integer less than or equal the float value

}
