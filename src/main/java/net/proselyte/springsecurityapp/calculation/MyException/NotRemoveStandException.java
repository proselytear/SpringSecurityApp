package net.proselyte.springsecurityapp.calculation.MyException;

public class NotRemoveStandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String str = "Данный объект или его часть не находится в стенде. Выведите из стенда ту часть объекта которая там находится.";

	public String toString() {
		return str;
	}

	public NotRemoveStandException() {

	}

	public NotRemoveStandException(String message) {
		str = message;
	}

}