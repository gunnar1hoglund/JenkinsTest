package core;

public class columnFullException extends Exception {

	/**
	 * This class adds a special column full exception
	 */
	private static final long serialVersionUID = 1L;

	public columnFullException() {
		super("That column is already full. Please pick another");
	}
}
