package net.proselyte.springsecurityapp.calculation;

public class MyTime {
	public static int toSSfromMMSS(String time){
		String[] words = time.split(":");
		int result=Integer.parseInt(words[0])*60+Integer.parseInt(words[1]);
		return result;
	}
	public static boolean isNumeric(String str) {
		  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
		}

}
