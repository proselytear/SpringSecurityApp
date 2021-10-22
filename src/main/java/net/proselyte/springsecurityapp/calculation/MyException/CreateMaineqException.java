package net.proselyte.springsecurityapp.calculation.MyException;

public class CreateMaineqException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String str;

	public String toString() {
		return str;
	}

	public CreateMaineqException() {

	}

	public CreateMaineqException(String message) {
		this.str = message;
	}

}