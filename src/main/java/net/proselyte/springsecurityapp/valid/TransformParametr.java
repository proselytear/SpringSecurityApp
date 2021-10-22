package net.proselyte.springsecurityapp.valid;

public class TransformParametr {
	public static String transformCoord(String str) {
		// Преобразуем координаты вводимые пользователем из формата "12.789876
		// 12.789876" в формат X2.789876Y12.789876
		String result = "";
		if (str.length() >= 19) {
			String coordX = str.substring(0, 9);
			String coordY = str.substring(10, 19);
			result = "X".concat(coordX).concat("Y").concat(coordY);
		}
		return result;
	}

}
