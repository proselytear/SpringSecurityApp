package net.proselyte.springsecurityapp.calculation.MyException;

public class NotInputGroupException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String str = "Выберите группу для конкретного шлейфа";

	public String toString() {
		return str;
	}

	public NotInputGroupException() {

	}

	public NotInputGroupException(String message) {
		str = message;
	}

}