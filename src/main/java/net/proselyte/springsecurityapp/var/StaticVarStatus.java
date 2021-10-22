package net.proselyte.springsecurityapp.var;

public class StaticVarStatus {
	public static String statusGroupOchrana = "ПОД ОХРАНОЙ";
	public static String statusGroupWithoutOchrana = "БЕЗ ОХРАНЫ";
	public static String statusGroupAlarm = "ТРЕВОГА";
	public static String statusGroupOchranaPatrial = "ЧАСТИЧНАЯ ОХРАНА";
	public static String statusNorma = "Норма";
	public static String statusCrash = "Авария";
	// Статус обрабатываемых тревог
	public static int stNew = 1; // Новое
	public static int stAccepted = 2;// Принято
	public static int stGroupLeft = 3;// Выехала группа
	public static int stGroupArrived = 4;// Группа прибыла
	public static int stProcessed = 5;// Обработано
	public static int stGroupCancel = 6;// Группа отменена
	// Статус группы
	public static Long groupFree = 1L;// Группа 'свободна'
	public static Long groupRevision = 4L;// Группа 'осмотр объекта'
	public static Long groupSTO = 2L;// Группа 'На СТО'
	public static Long groupAZS = 3L;// Группа 'На Азс'
	public static Long groupInObject = 5L;// Группа 'На объекте'

}
