package net.proselyte.springsecurityapp.calculation.MyException;

public class NotFoundFileExportException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "ВВедите название файла для экспорта";
	}

}
