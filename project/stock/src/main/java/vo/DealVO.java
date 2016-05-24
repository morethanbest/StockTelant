package vo;

public class DealVO {
	private String date;
	private double deal;
	public DealVO(String date,double deal){
		this.date=date;
		this.deal=deal;
	}
	
	public String getDate(){
		return this.date;
		
	}
	
	public double getDeal(){
		return this.deal;
	}
}
