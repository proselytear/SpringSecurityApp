package net.proselyte.springsecurityapp.calculation.MyException;

public class AddGroupCardException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String str;

	public String toString() {
		return str;
	}

	public AddGroupCardException() {

	}

	public AddGroupCardException(String message) {
		this.str = message;
	}

}