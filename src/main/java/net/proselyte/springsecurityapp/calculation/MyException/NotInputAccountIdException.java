package net.proselyte.springsecurityapp.calculation.MyException;

public class NotInputAccountIdException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Введите номер объекта.";
	}
}