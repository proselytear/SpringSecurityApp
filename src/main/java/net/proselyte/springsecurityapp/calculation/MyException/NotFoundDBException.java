package net.proselyte.springsecurityapp.calculation.MyException;

public class NotFoundDBException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Данный объект не найден в базе данных.";
	}

}
