package net.proselyte.springsecurityapp.calculation.MyException;

public class AddKardException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String str;

	public String toString() {
		return str;
	}

	public AddKardException() {

	}

	public AddKardException(String message) {
		this.str = message;
	}

}