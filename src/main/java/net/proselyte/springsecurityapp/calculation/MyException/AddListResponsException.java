package net.proselyte.springsecurityapp.calculation.MyException;

public class AddListResponsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String str;

	public String toString() {
		return str;
	}

	public AddListResponsException() {

	}

	public AddListResponsException(String message) {
		this.str = message;
	}

}