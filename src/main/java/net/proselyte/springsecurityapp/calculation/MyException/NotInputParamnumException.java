package net.proselyte.springsecurityapp.calculation.MyException;

public class NotInputParamnumException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String str = "Выберите группу на объекте.";

	public String toString() {
		return str;
	}

	public NotInputParamnumException() {

	}

	public NotInputParamnumException(String message) {
		str = message;
	}

}