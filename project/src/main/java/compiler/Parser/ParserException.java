package compiler.Parser;

/**
 * @author A. Crespin & R. De Oliveira
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
