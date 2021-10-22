package net.proselyte.springsecurityapp.calculation.MyException;

public class AddDeleteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String str;

	public String toString() {
		return str;
	}

	public AddDeleteException() {

	}

	public AddDeleteException(String message) {
		this.str = message;
	}

}