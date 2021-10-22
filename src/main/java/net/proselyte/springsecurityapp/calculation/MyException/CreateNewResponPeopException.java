package net.proselyte.springsecurityapp.calculation.MyException;

public class CreateNewResponPeopException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String str;

	public String toString() {
		return str;
	}

	public CreateNewResponPeopException() {

	}

	public CreateNewResponPeopException(String message) {
		this.str = message;
	}

}