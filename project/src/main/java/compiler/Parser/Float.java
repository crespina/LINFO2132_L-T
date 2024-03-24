package compiler.Parser;

public class Float {

	String nom;
	String denom;

	/**
	 * @param nom 
	 * @param denom 
	 */
	public Float(String nom, String denom) {
		super();
		this.nom = nom;
		this.denom = denom;
	}
	
	public String toString() {
		return "Float : " + "nom = " + nom + ", denom = " + denom + "\n";
	}
}
