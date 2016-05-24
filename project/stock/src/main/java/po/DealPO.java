package po;

public class DealPO {
	private String code;
	private String date;
	private String deal;
	public DealPO(String code,String date,String deal){
		this.date=date;
		this.deal=deal;
		this.code=code;
	}
	
	public String getDate(){
		return this.date;
		
	}
	
	public String getDeal(){
		return this.deal;
	}
	
	
	public String getCode(){
		return this.code;
	}

}
