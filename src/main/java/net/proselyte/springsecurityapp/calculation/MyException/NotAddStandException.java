package net.proselyte.springsecurityapp.calculation.MyException;

public class NotAddStandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String str = "Данный объект уже находится в стенде. Чтобы добавить повторно в стенд нужно вначале вывести из стенда объект.";

	public String toString() {
		return str;
	}

	public NotAddStandException() {

	}

	public NotAddStandException(String message) {
		str = message;
	}

}