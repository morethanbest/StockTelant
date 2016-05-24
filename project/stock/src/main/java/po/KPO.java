package po;

public class KPO {
	private String code;
	private String date;//日期
	private String start;//开盘价
	private String end; //收盘价
	private String high; //最高价
	private String low; //最低价
	
	
	public KPO(String code,String date,String start,String end,String high,String low){
		this.code=code;
		this.date=date;
		this.start=start;
		this.end=end;
		this.high=high;
		this.low=low;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getDate() {
		return date;
	}
	public String getStart(){
		return this.start;
	}
	public String getEnd(){
		return this.end;
	}
	
	public String getHigh(){
		return this.high;
	}
	
	public String getLow(){
		return this.low;
	}
	
}
