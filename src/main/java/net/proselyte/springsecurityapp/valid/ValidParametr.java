package net.proselyte.springsecurityapp.valid;

public class ValidParametr {
	// 1..32 Проверка на корректность номера шлейфа
	public static boolean validParamnum(String str) {
		boolean result = str.matches("^(3[012]|[12][0-9]|[1-9])$");
		return result;
	}

	// Проверка номер группы 1..8
	public static boolean validNumGroup(String str) {
		boolean result = str.matches("^([1-8])");
		return result;
	}

//Проверка часов от 0 до 23
	public static boolean validHour(String str) {
		boolean result = str.matches("^(2[0123]|[1][0-9]|[0-9])$");
		return result;
	}

	public static boolean validPhone(String str) {
		// Проверка номера телефона в формате +xxxxxxxxxxxx где x число
		boolean result = str.matches("[+]?[0-9]{12}");
		return result;
	}

	public static boolean validCoord(String str) {
		// Проверка координат Допустимфй формат"12.789876 12.789876"
		boolean result = str.matches("[0-9]{2}?[.]?[0-9]{6}?[ ]?[0-9]{2}?[.]?[0-9]{6}");
		return result;
	}

	/**
	 * Цифры и буквы от A до F. Длина поля от 4 до 16 включительно
	 * 
	 * @param str номер объекта
	 * @return результат проверки вводимого поля
	 */
	public static boolean validAccountId(String str) {

		return str.matches("[a-fA-F0-9]{4,16}");

	}

}
