package vo;

public class FirmVO {
	String code;
	String date;

	String open;
	String  highest;
	String  lowest;
	public FirmVO(String code, String date, String open, String highest, String lowest) {
		super();
		this.code = code;
		this.date = date;
		this.open = open;
		this.highest = highest;
		this.lowest = lowest;
	}
	public String getCode() {
		return code;
	}
	public String getDate() {
		return date;
	}
	public String getOpen() {
		return open;
	}
	public String getHighest() {
		return highest;
	}
	public String getLowest() {
		return lowest;
	}
	
}
