package compiler.Parser;

/**
 * @author Crespin
 *
 */
public class ParserException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param errorMessage
	 */
	public ParserException(String errorMessage) {
        super(errorMessage);
    }

}
