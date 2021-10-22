package net.proselyte.springsecurityapp.model;

/**
 * параметры поиска для страницы objectevent
 * 
 * @author user
 *
 */
public class EventFound {
	String date;
	String date1;
	String evrangidStr;// Ранг события
	String keyword;// Описание события
	String keyword2;// Номер объекта
	Integer sizeColumn;// Отображать количество событий

	public EventFound(String date, String date1, String evrangidStr, String keyword, String keyword2,
			Integer sizeColumn) {
		super();
		this.date = date;
		this.date1 = date1;
		this.evrangidStr = evrangidStr;
		this.keyword = keyword;
		this.keyword2 = keyword2;
		this.sizeColumn = sizeColumn;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public Integer getSizeColumn() {
		return sizeColumn;
	}

	public void setSizeColumn(Integer sizeColumn) {
		this.sizeColumn = sizeColumn;
	}

	public String getEvrangidStr() {
		return evrangidStr;
	}

	public void setEvrangidStr(String evrangidStr) {
		this.evrangidStr = evrangidStr;
	}
}
