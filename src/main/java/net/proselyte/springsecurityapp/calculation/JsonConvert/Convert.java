package net.proselyte.springsecurityapp.calculation.JsonConvert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONObject;

/**
 * 
 * @author Nosenko Kateryna
 *
 */

public class Convert {
	/**
	 * получаем коллекцию уникальных значений значений
	 * 
	 * @param strJson входящий параметр JSON объекта в формате [[],["2"],["3"]]
	 * @return
	 */
	public static Set<String> toArrayStringUnique(String strJson) {
		Set<String> set;

		strJson = strJson.replace("[", "").replace("]", "").replace("\"", "");
		if (strJson.length() > 0) {
			if (strJson.charAt(0) == ',')
				strJson = strJson.substring(1, strJson.length());

			String[] arrayJson = strJson.split(",");
			// int[] array = Arrays.stream(arrayJson).mapToInt(Integer::parseInt).toArray();
			set = new HashSet<String>(Arrays.asList(arrayJson));
		} else
			set = new HashSet<String>();
		return set;
	}

	public static Set<String> toArrayIdUnique(String strJson) {
		Set<String> set;

		strJson = strJson.replace("\"", "");
		if (strJson.length() > 0) {

			String[] arrayJson = strJson.split(",");
			// int[] array = Arrays.stream(arrayJson).mapToInt(Integer::parseInt).toArray();
			set = new HashSet<String>(Arrays.asList(arrayJson));
		} else
			set = new HashSet<String>();
		return set;
	}

	public static String JsonToString(String strJson, JSONObject jsonObject) {
		Object obj = jsonObject.get(strJson);
		if (obj != null)
			return obj.toString().trim();
		else
			return "";
	}

}
