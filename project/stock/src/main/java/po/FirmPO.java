package po;

public class FirmPO {
	String code;
	String date;

	String open;
	String  highest;
	String  lowest;
	public FirmPO(String date,String code,String open,String highest,String lowest){
		this.code=code;
		this.date=date;
		this.open=open;
		this.highest=highest;
		this.lowest=lowest;
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




















